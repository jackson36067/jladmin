package com.jackson.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.jackson.Repository.*;
import com.jackson.annotation.CacheOnlineUserInfo;
import com.jackson.constant.*;
import com.jackson.context.BaseContext;
import com.jackson.dto.*;
import com.jackson.entity.*;
import com.jackson.exception.*;
import com.jackson.properties.AliOssProperty;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.DeptService;
import com.jackson.service.UserService;
import com.jackson.utils.*;
import com.jackson.vo.*;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private DeptRepository deptRepository;
    @Resource
    private DeptService deptService;
    @Resource
    private JobRepository jobRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private GeoIPUtils geoIPUtils;
    @Resource
    private LogRepository logRepository;
    @Resource
    private MailManagement mailManagement;
    @Resource
    private UserMessageRepository userMessageRepository;
    @Resource
    private AliOssUtils aliOssUtils;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @CacheOnlineUserInfo
    @Override
    public Result<UserLoginVO> login(UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {
        // 校验验证码
        if (!request.getHeader(UserConstant.CODE_KEY).equalsIgnoreCase(userLoginDTO.getCode())) {
            throw new CodeErrorException(UserConstant.CODE_ERROR);
        }
        // 根据前端传递的用户名密码生成UsernamePasswordAuthenticationToken,用于进一步校验逻辑
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        // 在springSecurity规则下, 自定义校验逻辑
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate != null && authenticate.isAuthenticated()) {
            //登录成功
            String username = userLoginDTO.getUsername();
            User user = userRepository.findUserByUsername(username);
            // 生成token
            Map<String, Object> map = new HashMap<>();
            map.put(JwtConstant.USER_ID, user.getId());
            map.put(JwtConstant.USERNAME, user.getUsername());
            String token = JwtUtils.genJwt(map);
            UserLoginVO userLoginVO = BeanUtil.copyProperties(user, UserLoginVO.class);
            userLoginVO.setToken(token);
            // 返回菜单设置
            List<Long> roleIdlist = roleRepository.findRoleIdsByUserId(user.getId());
            List<Menu> menuList = menuRepository.findAllByRoleIds(roleIdlist);
            // 获取所有一级菜单集合,一级菜单根据menuId分组
            List<MenuVO> menuVOList = menuList
                    .stream()
                    .filter(menu -> menu.getType() == 0)
                    .map(menu -> {
                        // 根据menuId查找pid(就是查找该菜单下的二级菜单)
                        List<Menu> subMenuList = menuRepository.findAllByPid(menu.getId());
                        // 获取一级菜单返回
                        MenuVO menuVO = BeanUtil.copyProperties(menu, MenuVO.class);
                        // 获取二级菜单vo返回
                        List<SubMenuVO> subMenuVOList = subMenuList.stream().map(subMenu -> BeanUtil.copyProperties(subMenu, SubMenuVO.class)).toList();
                        menuVO.setSubMenuVOList(subMenuVOList);
                        return menuVO;
                    }).toList();
            userLoginVO.setDeptName(user.getDept().getName());
            userLoginVO.setMenuVOList(menuVOList);
            return Result.success(userLoginVO);
        }
        throw new RuntimeException("账号不存在或密码出错");
    }


    /**
     * 根据条件分页展示用户数据
     *
     * @param pageSize        页码数
     * @param page            页码
     * @param usernameOrEmail 名称或者邮箱
     * @param begin           开始时间
     * @param end             结束时间
     * @param enabled         用户账号状态
     * @return Result<UserVO>
     */
    @Override
    public Result<PagingResult> getUserWithPaging(Integer pageSize, Integer page, String usernameOrEmail, LocalDateTime begin, LocalDateTime end, Boolean enabled) {
        // 条件添加器
        Specification<User> userSpecification = (root, query, cb) -> {
            //用于暂时存放查询条件,存放到查询条件List中
            ArrayList<Predicate> predicateList = new ArrayList<>();
            // 加上用户名或者邮箱条件
            if (StringUtils.hasText(usernameOrEmail)) {
                Predicate uoe = cb.or(cb.like(root.get("username"), "%" + usernameOrEmail + "%"), cb.like(root.get("email"), "%" + usernameOrEmail + "%"));
                predicateList.add(uoe);
            }
            // 加上创建时间范围条件
            if (begin != null & end != null) {
                Predicate createTime = cb.between(root.get("createTime"), begin, end);
                predicateList.add(createTime);
            }
            // 加上状态条件
            if (enabled != null) {
                Predicate enable = cb.equal(root.get("enabled"), enabled);
                predicateList.add(enable);
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            // 将所有条件连接起来
            return cb.and(predicateList.toArray(predicates));
        };
        // 排序器
        Sort sort = Sort.by(Sort.Direction.DESC, UserConstant.CREATE_TIME);
        // 分页器
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
        Page<User> userPage = userRepository.findAll(userSpecification, pageRequest);
        List<User> userPageContent = userPage.getContent();
        List<UserVO> userVOList = userPageContent.stream()
                .map(user -> {
                    UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
                    String deptName = deptRepository.findNameByUserId(user.getId());
                    userVO.setDeptName(deptName);
                    return userVO;
                })
                .toList();
        PagingResult pagingResult = new PagingResult(userPage.getTotalElements(), userVOList);
        return Result.success(pagingResult);
    }

    /**
     * 通过deptId获取所有用户信息
     *
     * @param deptId
     * @return
     */
    @Override
    public Result<List<UserVO>> getUserListByDeptId(Long deptId) {
        // 通过递归获取所有
        List<Long> subDeptIds = deptService.getAllSubDeptIds(deptId);
        List<User> userList = userRepository.findAllByDeptIdIn(subDeptIds);
        List<UserVO> userVOList = userList.stream().map(user -> BeanUtil.copyProperties(user, UserVO.class)).toList();
        return Result.success(userVOList);
    }

    /**
     * 修改用户
     *
     * @param id
     * @param updateUserDTO
     * @return
     */
    @Transactional
    @Override
    public Result<Void> updateUser(Long id, UpdateUserDTO updateUserDTO) {
        // 通过用户id获取用户信息
        User user = userRepository.findById(id).get();

        String username = updateUserDTO.getUsername();
        String nickName = updateUserDTO.getNickName();
        String phone = updateUserDTO.getPhone();
        String email = updateUserDTO.getEmail();
        Long deptId = updateUserDTO.getDeptId();
        String gender = updateUserDTO.getGender();
        Boolean enabled = updateUserDTO.getEnabled();
        List<Long> roles = updateUserDTO.getRoles();
        List<Long> jobs = updateUserDTO.getJobs();
        String avatarPath = updateUserDTO.getAvatarPath();
        // 防止只更新用户中心数据时将岗位与角色信息删除
        if (roles != null && jobs != null) {
            // 更新用户job以及role时,先清空内容,后续再添加
            user.setJobSet(null);
            user.setRoleSet(null);
            userRepository.saveAndFlush(user);
        }
        if (StringUtils.hasText(username) & !user.getUsername().equals(username)) {
            // 修改的用户名不能重复
            User user1 = userRepository.findUserByUsername(username);
            if (user1 != null) {
                throw new UsernameExistException(UserConstant.USERNAME_EXIST);
            }
            user.setUsername(username);
        }
        if (StringUtils.hasText(nickName) & !user.getNickName().equals(nickName)) {
            user.setNickName(nickName);
        }
        if (StringUtils.hasText(phone) & !user.getPhone().equals(phone)) {
            User user1 = userRepository.findByPhone(phone);
            if (user1 != null) {
                throw new PhoneExistException(UserConstant.PHONE_EXIST);
            }
            user.setPhone(phone);
        }
        if (StringUtils.hasText(email) & !user.getEmail().equals(email)) {
            User user1 = userRepository.findByEmail(email);
            if (user1 != null) {
                throw new EmailExistException(UserConstant.EMAIL_EXIST);
            }
            user.setEmail(email);
        }
        if (deptId != null & !user.getDept().getId().equals(deptId)) {
            // 调用deptRepository修改该用户的对应部门的部门名称
            user.setDept(deptRepository.findById(deptId).get());
        }
        if (StringUtils.hasText(gender) & !user.getGender().equals(gender)) {
            user.setGender(gender);
        }
        if (enabled != null & !user.getEnabled().equals(enabled)) {
            user.setEnabled(enabled);
        }
        // 修改头像
        if (StringUtils.hasText(avatarPath) & !user.getAvatarPath().equals(avatarPath)) {
            user.setAvatarPath(avatarPath);
        }

        // 修改角色
        if (roles != null) {
            Set<Role> roleSet = roleRepository.findAllByIdIn(roles);
            user.setRoleSet(roleSet);
        }

        // 修改岗位
        if (jobs != null) {
            Set<Job> jobSet = jobRepository.findAllByIdIn(jobs);
            user.setJobSet(jobSet);
        }

        userRepository.saveAndFlush(user);
        return Result.success();
    }

    /**
     * 新增用户
     *
     * @param userDTO
     * @return
     */
    @Transactional
    @Override
    public Result<Void> saveUser(UserDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        String username = user.getUsername();
        String phone = user.getPhone();
        String email = user.getEmail();
        User user1 = userRepository.findUserByUsername(username);
        User user2 = userRepository.findUserByUsername(phone);
        User user3 = userRepository.findUserByUsername(email);
        // 保证新增的用户名,电话号码以及邮箱不能重复
        if (user1 != null) {
            throw new PhoneExistException(UserConstant.PHONE_EXIST);
        }
        if (user2 != null) {
            throw new PhoneExistException(UserConstant.PHONE_EXIST);
        }
        if (user3 != null) {
            throw new EmailExistException(UserConstant.EMAIL_EXIST);
        }
        // 设置dept
        Dept dept = deptRepository.findById(userDTO.getDeptId()).get();
        // 设置role
        Set<Role> roleSet = roleRepository.findAllByIdIn(userDTO.getRoles());
        // 设置job
        Set<Job> jobSet = jobRepository.findAllByIdIn(userDTO.getJobs());
        // 新增操作
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setIsAdmin(true);
        user.setDept(dept);
        user.setRoleSet(roleSet);
        user.setJobSet(jobSet);
        userRepository.save(user);
        return Result.success();
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public Result<UserDTO> getUserInfoById(Long id) {
        User user = userRepository.findById(id).get();
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        userDTO.setDeptId(user.getDept().getId());
        // 根据用户id获取Role
        List<Long> roleIdList = roleRepository.findRoleIdsByUserId(id);
        userDTO.setRoles(roleIdList);
        List<Long> jobIdlist = user.getJobSet().stream().map(Job::getId).toList();
        userDTO.setJobs(jobIdlist);
        return Result.success(userDTO);
    }

    /**
     * 根据id删除用户, 支持多删
     *
     * @param ids
     * @return
     */
    @Override
    public Result<Void> deleteUserById(List<Long> ids) {
        userRepository.deleteAllByIdInBatch(ids);
        return Result.success();
    }

    /**
     * 导出用户数据
     *
     * @param httpServletResponse
     * @return
     */
    @Override
    public void exportUserData(HttpServletResponse httpServletResponse) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream("templates/用户数据.xlsx");
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("sheet1");
            List<UserExportDataVO> userExportDataVOList = userRepository.findAll().stream().map(user -> {
                UserExportDataVO userExportDataVO = BeanUtil.copyProperties(user, UserExportDataVO.class);
                userExportDataVO.setDeptName(user.getDept().getName());
                userExportDataVO.setRoles(
                        user.getRoleSet().stream().map(Role::getName).toList()
                );
                userExportDataVO.setJobs(
                        user.getJobSet().stream().map(Job::getName).toList()
                );
                return userExportDataVO;
            }).toList();
            int RowIndex = 1;
            for (UserExportDataVO userExportDataVO : userExportDataVOList) {
                XSSFRow row = sheet.createRow(RowIndex);
                row.createCell(0).setCellValue(userExportDataVO.getUsername());
                row.createCell(1).setCellValue(com.jackson.utils.StringUtils.convertCollectionToString(userExportDataVO.getRoles()));
                row.createCell(2).setCellValue(userExportDataVO.getDeptName());
                row.createCell(3).setCellValue(com.jackson.utils.StringUtils.convertCollectionToString(userExportDataVO.getJobs()));
                row.createCell(4).setCellValue(userExportDataVO.getEmail());
                row.createCell(5).setCellValue(userExportDataVO.getEnabled() ? "激活" : "禁用");
                row.createCell(6).setCellValue(userExportDataVO.getPhone());
                row.createCell(7).setCellValue(DateTimeUtils.formatLocalDateTime(userExportDataVO.getPwdResetTime()));
                row.createCell(8).setCellValue(DateTimeUtils.formatLocalDateTime(userExportDataVO.getCreateTime()));
                RowIndex++;
            }

            // 设置请求头,让浏览器下载该文件
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String((DateTimeUtils.formatLocalDateTime(LocalDateTime.now()) + "用户数据").getBytes(), "ISO8859-1"));
            httpServletResponse.setCharacterEncoding("UTF-8");
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            excel.write(outputStream);
            // 释放资源
            outputStream.flush(); // 确保所有数据都被写入
            outputStream.close();
            excel.close();
        } catch (IOException e) {
            throw new RuntimeException(UserConstant.INPUT_ERROR);
        }
    }

    /**
     * 生成验证码图片
     *
     * @param httpServletResponse
     */
    @Override
    public void generateCode(HttpServletResponse httpServletResponse) {
        // 使用糊涂包生成验证码图片
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(113, 36, 4, 30);
        String code = lineCaptcha.getCode();
        try {
            // 通过httpServletResponse返回验证码图片
            lineCaptcha.write(httpServletResponse.getOutputStream());
            // 将验证码保存到header中
            httpServletResponse.addHeader(UserConstant.CODE_KEY, code);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", UserConstant.CODE_KEY);
        } catch (IOException e) {
            throw new RuntimeException(UserConstant.GEN_CODE_ERROR);
        }
    }

    /**
     * 退出登录
     */
    @Override
    public void logout(HttpServletRequest request) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(name);
        // 退出后删除该用户的在线记录
        ZSetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForZSet();
        Set<String> members = stringStringSetOperations.range(RedisConstant.ONLINE_USER_KEY, 0, -1);// 获取所有成员
        if (members.size() != 0) {
            for (String member : members) {
                OnlineUser onlineUser = JSONUtil.toBean(member, OnlineUser.class);
                if (onlineUser.getUsername().equals(user.getUsername()) && onlineUser.getIpAddress().equals(geoIPUtils.getClientIp(request))) {
                    stringStringSetOperations.remove(RedisConstant.ONLINE_USER_KEY, JSONUtil.toJsonStr(onlineUser));
                }
            }
        }
        // 清理安全上下文
        SecurityContextHolder.clearContext();
        // 清除baseContext
        BaseContext.removeCurrentId();
    }

    /**
     * 获取在线用户列表
     *
     * @param page
     * @param pageSize
     * @param username
     * @return
     */
    @Override
    public Result<PagingResult> getOnlineUserPaging(Integer page, Integer pageSize, String username) {
        // 获取起始索引
        int start = (page - 1) * pageSize;
        // 使用opsForZSet实现分页
        ZSetOperations<String, String> stringStringZSetOperations = stringRedisTemplate.opsForZSet();
        Set<String> range = stringStringZSetOperations.reverseRangeByScore(RedisConstant.ONLINE_USER_KEY, 0, System.currentTimeMillis(), Long.parseLong(Integer.toString(start)), pageSize);
        List<OnlineUser> onlineUserList = range.stream().map(jsonStr -> JSONUtil.toBean(jsonStr, OnlineUser.class)).toList();
        if (StringUtils.hasText(username)) {
            String regex = ".*" + username + ".*";
            onlineUserList = onlineUserList.stream().filter(onlineUser -> onlineUser.getUsername().matches(regex)).toList();
        }
        PagingResult pagingResult = new PagingResult(stringStringZSetOperations.size(RedisConstant.ONLINE_USER_KEY), onlineUserList);
        return Result.success(pagingResult);
    }

    /**
     * 强制退出某个用户账号
     *
     * @param ipList
     */
    @Override
    public void ForcedWithdrawal(List<String> ipList) {
        // 强退实现逻辑,将强退的用户名加入到redis中,在过滤器中校验是否存在该登录者的ip,存在直接清空认证信息,强制退出到登录页面
        stringRedisTemplate.opsForSet().add(RedisConstant.FORCE_WITHDRAW_KEY, ipList.toArray(new String[0]));
        // 将拉黑的这个在线用户数据删除
        Set<String> onlineUserJsonList = stringRedisTemplate.opsForZSet().range(RedisConstant.ONLINE_USER_KEY, 0, -1);
        onlineUserJsonList.forEach(onlineUserJson -> {
            OnlineUser onlineUser = JSONUtil.toBean(onlineUserJson, OnlineUser.class);
            if (ipList.contains(onlineUser.getIpAddress())) {
                stringRedisTemplate.opsForZSet().remove(RedisConstant.ONLINE_USER_KEY, onlineUserJson);
            }
        });
    }

    /**
     * 导出在线用户数据
     *
     * @param response
     */
    @Override
    public void exportOnlineUserInfo(HttpServletResponse response) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream("templates/在线用户数据.xlsx");
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("sheet1");
            // 获取所有在线用户数据
            List<OnlineUserExportData> onlineUserExportDataList = stringRedisTemplate.opsForZSet().reverseRangeByScore(RedisConstant.ONLINE_USER_KEY, 0, System.currentTimeMillis())
                    .stream()
                    .map(onlineUserInfoJson -> {
                        OnlineUser onlineUser = JSONUtil.toBean(onlineUserInfoJson, OnlineUser.class);
                        return BeanUtil.copyProperties(onlineUser, OnlineUserExportData.class);
                    })
                    .toList();
            int RowIndex = 1;
            for (OnlineUserExportData onlineUserExportData : onlineUserExportDataList) {
                XSSFRow row = sheet.createRow(RowIndex);
                row.createCell(0).setCellValue(onlineUserExportData.getUsername());
                row.createCell(1).setCellValue(onlineUserExportData.getDeptName());
                row.createCell(2).setCellValue(onlineUserExportData.getIpAddress());
                row.createCell(3).setCellValue(onlineUserExportData.getLoginLocation());
                row.createCell(4).setCellValue(onlineUserExportData.getBrowser());
                row.createCell(5).setCellValue(DateTimeUtils.formatLocalDateTime(onlineUserExportData.getLoginTime()));
                RowIndex++;
            }

            // 设置请求头,让浏览器下载该文件
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((DateTimeUtils.formatLocalDateTime(LocalDateTime.now()) + "用户数据").getBytes(), "ISO8859-1"));
            response.setCharacterEncoding("UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            excel.write(outputStream);
            // 释放资源
            outputStream.flush(); // 确保所有数据都被写入
            outputStream.close();
            excel.close();
        } catch (IOException e) {
            throw new RuntimeException(UserConstant.INPUT_ERROR);
        }
    }

    /**
     * 根据用户名分页获取用户操作日志
     *
     * @param page
     * @param pageSize
     * @param username
     * @return
     */
    @Override
    public Result<PagingResult> getUserLog(Integer page, Integer pageSize, String username) {
        Specification<Log> logSpecification = (root, query, cb) -> {
            ArrayList<Predicate> predicateArrayList = new ArrayList<>();
            Predicate predicate = cb.equal(root.get(LogConstant.USERNAME), username);
            predicateArrayList.add(predicate);
            return cb.and(predicateArrayList.toArray(new Predicate[0]));
        };
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, LogConstant.CREATE_TIME));
        Page<Log> logRepositoryAll = logRepository.findAll(logSpecification, pageRequest);
        List<UserLogVO> userLogVOList = logRepositoryAll.getContent()
                .stream()
                .map(log -> BeanUtil.copyProperties(log, UserLogVO.class))
                .toList();
        PagingResult pagingResult = new PagingResult(logRepositoryAll.getTotalElements(), userLogVOList);
        return Result.success(pagingResult);
    }

    /**
     * 发送验证码到指定邮箱
     *
     * @param email
     */
    @Override
    public void sentEmailCode(String email) {
        String code = RandomUtil.randomNumbers(6);
        mailManagement.sendMessage(email, EmailConstant.EMAIL_CODE_SUBJECT, code);
        // 将验证码保存到redis中,并设置1分钟过期
        Long currentId = BaseContext.getCurrentId(); // 区分给不同用户发送的验证码
        stringRedisTemplate.opsForValue().set(RedisConstant.EMAIL_KEY_PREFIX + currentId, code, RedisConstant.CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    /**
     * 修改用户邮箱
     *
     * @param updateEmailDTO
     */
    @Override
    public void updateEmail(UpdateEmailDTO updateEmailDTO) {
        // 获取当前用户
        User user = userRepository.findById(BaseContext.getCurrentId()).get();
        Authentication authentication = judgePasswordAuthentication(user.getUsername(), updateEmailDTO.getPassword());
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new PasswordErrorException(UserConstant.PASSWORD_ERROR);
        }
        String cacheCode = stringRedisTemplate.opsForValue().get(RedisConstant.EMAIL_KEY_PREFIX + user.getId());
        if (!Objects.equals(cacheCode, updateEmailDTO.getVerifyCode())) {
            throw new CodeErrorException(UserConstant.CODE_ERROR);
        }
        user.setEmail(updateEmailDTO.getNewEmail());
        userRepository.saveAndFlush(user);
    }

    /**
     * 修改用户密码
     *
     * @param updatePasswordDTO
     */
    @Override
    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        // 获取当前用户
        User user = userRepository.findById(BaseContext.getCurrentId()).get();
        Authentication authentication = judgePasswordAuthentication(user.getUsername(), updatePasswordDTO.getUsedPassword());
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new PasswordErrorException(UserConstant.USED_PASSWORD_ERROR);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(updatePasswordDTO.getNewPassword());
        user.setPassword(encode);
        userRepository.saveAndFlush(user);
    }

    private Authentication judgePasswordAuthentication(String username, String password) {
        // 校验密码是否一致 (使用springSecurity框架校验)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @Override
    public Result<List<UserFriendVO>> getAllFriendUser(String username) {
        List<User> userList = userRepository.findAll();
        List<UserFriendVO> list = userList.stream().map(user -> BeanUtil.copyProperties(user, UserFriendVO.class)).toList();
        // 如果用户jackson,那么将其他所有用户返回,如果不是,只返回jackson一个用户 (其他用户联系管理员jackson)
        String userFriendArgsUsername = UserConstant.USER_FRIEND_ARGS_USERNAME;
        if (username.equals(userFriendArgsUsername)) {
            list = list.stream().filter(userFriendVO -> !userFriendVO.getUsername().equals(userFriendArgsUsername)).toList();
        } else {
            list = list.stream().filter(userFriendVO -> userFriendVO.getUsername().equals(userFriendArgsUsername)).toList();
        }
        return Result.success(list);
    }

    /**
     * 获取两个用户的聊天记录
     *
     * @param username
     * @param friendUsername
     * @return
     */
    @Override
    public Result<List<UserMessage>> getUsersMessage(String username, String friendUsername) {
        List<UserMessage> all = userMessageRepository.findAll(Sort.by(Sort.Direction.ASC, "sendTime"));
        // 过滤出两者的信息
        List<UserMessage> list = all
                .stream()
                .filter(userMessage ->
                        (
                                userMessage.getSender().equals(username) && userMessage.getRecipient().equals(friendUsername)) || (userMessage.getSender().equals(friendUsername) && userMessage.getRecipient().equals(username)
                        )
                ).toList();
        return Result.success(list);
    }

    /**
     * 上传头像至alioss
     *
     * @param image
     * @return
     */
    @Override
    public Result<String> uploadImage(MultipartFile image) {
        String upload = aliOssUtils.upload(image);
        return Result.success(upload);
    }
}
