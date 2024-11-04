package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.jackson.Repository.*;
import com.jackson.constant.JwtConstant;
import com.jackson.constant.UserConstant;
import com.jackson.context.BaseContext;
import com.jackson.dto.UserDTO;
import com.jackson.dto.UpdateUserDTO;
import com.jackson.dto.UserLoginDTO;
import com.jackson.entity.*;
import com.jackson.exception.UserNotExistException;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.DeptService;
import com.jackson.service.UserService;
import com.jackson.util.DateTimeUtils;
import com.jackson.util.JwtUtils;
import com.jackson.vo.*;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

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

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public Result<UserLoginVO> login(UserLoginDTO userLoginDTO) {
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
        PagingResult pagingResult = new PagingResult(userVOList.size(), userVOList);
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

    @Transactional
    @Override
    public Result<Void> updateUser(Long id, UpdateUserDTO updateUserDTO) {
        // 根据修改参数是否存在
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
        if (StringUtils.hasText(username) & !user.getUsername().equals(updateUserDTO.getUsername())) {
            user.setUsername(username);
        }
        if (StringUtils.hasText(nickName) & !user.getNickName().equals(updateUserDTO.getNickName())) {
            user.setNickName(nickName);
        }
        if (StringUtils.hasText(phone) & !user.getPhone().equals(updateUserDTO.getPhone())) {
            user.setPhone(phone);
        }
        if (StringUtils.hasText(email) & !user.getEmail().equals(updateUserDTO.getEmail())) {
            user.setEmail(email);
        }
        if (deptId != null & !user.getDept().getId().equals(deptId)) {
            // 调用deptRepository修改该用户的对应部门的部门名称
            user.setDept(deptRepository.findById(deptId).get());
        }
        if (StringUtils.hasText(gender) & !user.getGender().equals(updateUserDTO.getGender())) {
            user.setGender(gender);
        }
        if (enabled != null & !user.getEnabled().equals(updateUserDTO.getEnabled())) {
            user.setEnabled(enabled);
        }
        // 修改角色
        Set<Role> roleSet = roleRepository.findAllByIdIn(roles);
        if (!UserServiceImpl.areSetsEqual(roleSet, user.getRoleSet())) {
            user.setRoleSet(roleSet);
        }
        // 修改岗位
        Set<Job> jobSet = jobRepository.findAllByIdIn(jobs);
        if (!UserServiceImpl.areSetsEqual(jobSet, user.getJobSet())) {
            user.setJobSet(jobSet);
        }
        Long currentId = BaseContext.getCurrentId();
        User updateUser = userRepository.findById(currentId).get();
        user.setUpdateBy(updateUser.getUsername());
        user.setUpdateTime(LocalDateTime.now());
        userRepository.save(user);
        return Result.success();
    }

    @Transactional
    @Override
    public Result<Void> saveUser(UserDTO userDTO) {
        // 设置dept
        Dept dept = deptRepository.findById(userDTO.getDeptId()).get();
        // 设置默认配置
        Long currentId = BaseContext.getCurrentId();
        User createUser = userRepository.findById(currentId).get();
        // 设置role
        Set<Role> roleSet = roleRepository.findAllByIdIn(userDTO.getRoles());
        // 设置job
        Set<Job> jobSet = jobRepository.findAllByIdIn(userDTO.getJobs());
        User user = BeanUtil.copyProperties(userDTO, User.class);
        // 新增操作
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setIsAdmin(true);
        user.setDept(dept);
        user.setRoleSet(roleSet);
        user.setJobSet(jobSet);
        user.setCreateBy(createUser.getUsername());
        user.setUpdateBy(createUser.getUsername());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setPwdResetTime(LocalDateTime.now());
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
                row.createCell(1).setCellValue(com.jackson.util.StringUtils.convertCollectionToString(userExportDataVO.getRoles()));
                row.createCell(2).setCellValue(userExportDataVO.getDeptName());
                row.createCell(3).setCellValue(com.jackson.util.StringUtils.convertCollectionToString(userExportDataVO.getJobs()));
                row.createCell(4).setCellValue(userExportDataVO.getEmail());
                row.createCell(5).setCellValue(userExportDataVO.getEnabled() ? "激活" : "禁用");
                row.createCell(6).setCellValue(userExportDataVO.getPhone());
                row.createCell(7).setCellValue(DateTimeUtils.formatLocalDateTime(userExportDataVO.getPwdResetTime()));
                row.createCell(8).setCellValue(DateTimeUtils.formatLocalDateTime(userExportDataVO.getCreateTime()));
                RowIndex++;
            }

            // 设置请求头,让浏览器下载该文件
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String((DateTimeUtils.formatLocalDateTime(LocalDateTime.now())+"用户数据").getBytes(), "ISO8859-1"));
            httpServletResponse.setCharacterEncoding("UTF-8");
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            excel.write(outputStream);
            // 释放资源
            outputStream.flush(); // 确保所有数据都被写入
            outputStream.close();
            excel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 比较两个set集合内容是否完全一致
     *
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> boolean areSetsEqual(Set<T> set1, Set<T> set2) {
        if (set1 == null || set2 == null) {
            return false; // 处理 null 的情况
        }
        return set1.size() == set2.size() && set1.containsAll(set2);
    }
}
