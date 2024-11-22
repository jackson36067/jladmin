package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.jackson.Repository.MenuRepository;
import com.jackson.Repository.RoleRepository;
import com.jackson.Repository.UserRepository;
import com.jackson.constant.RoleConstant;
import com.jackson.dto.AddRoleDTO;
import com.jackson.dto.UpdateRoleDTO;
import com.jackson.dto.UpdateRoleMenuDTO;
import com.jackson.entity.Menu;
import com.jackson.entity.Role;
import com.jackson.entity.User;
import com.jackson.exception.RoleHasUserException;
import com.jackson.exception.RoleNameExistException;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.RoleService;
import com.jackson.utils.DateTimeUtils;
import com.jackson.vo.RoleAllVO;
import com.jackson.vo.RoleExportDataVO;
import com.jackson.vo.RoleVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private UserRepository userRepository;

    /**
     * 获取所有角色名称集合
     *
     * @return
     */
    @Override
    public Result<List<RoleAllVO>> getAllRoles() {
        List<RoleAllVO> roleAllVOList = roleRepository
                .findAll()
                .stream()
                .map(role -> BeanUtil.copyProperties(role, RoleAllVO.class))
                .toList();
        return Result.success(roleAllVOList);
    }

    /**
     * 根据条件分页获取角色数据
     *
     * @param page
     * @param pageSize
     * @param nameOrDescription
     * @param begin
     * @param end
     * @return
     */
    @Override
    public Result<PagingResult> getRoleWithPaging(Integer page, Integer pageSize, String nameOrDescription, LocalDateTime begin, LocalDateTime end) {
        Specification<Role> roleSpecification = (root, query, cb) -> {
            //用于暂时存放查询条件,存放到查询条件List中
            ArrayList<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.hasText(nameOrDescription)) {
                Predicate nod = cb.or(cb.like(root.get("name"), "%" + nameOrDescription + "%"), cb.like(root.get("description"), "%" + nameOrDescription + "%"));
                predicateList.add(nod);
            }
            if (begin != null & end != null) {
                Predicate createTime = cb.between(root.get("createTime"), begin, end);
                predicateList.add(createTime);
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            // 将所有条件连接起来
            return cb.and(predicateList.toArray(predicates));
        };
        // 排序器
        Sort sort = Sort.by(Sort.Direction.DESC, RoleConstant.CREATE_TIME);
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
        Page<Role> rolePage = roleRepository.findAll(roleSpecification, pageRequest);
        List<RoleVO> roleVOList = rolePage.getContent()
                .stream()
                .map(role -> {
                    RoleVO roleVO = BeanUtil.copyProperties(role, RoleVO.class);
                    // 返回角色时返回菜单,用户获取菜单
                    ArrayList<Long> list = new ArrayList<>();
                    list.add(role.getId());
                    List<Long> menuIdList = menuRepository.findAllByRoleIds(list).stream().map(Menu::getId).toList();
                    roleVO.setMenuIdList(menuIdList);
                    return roleVO;
                })
                .toList();
        PagingResult pagingResult = new PagingResult(rolePage.getTotalElements(), roleVOList);
        return Result.success(pagingResult);
    }

    /**
     * 更新角色菜单
     *
     * @param updateRoleMenuDTO
     */
    @Override
    public void updateRoleMenuList(UpdateRoleMenuDTO updateRoleMenuDTO) {
        Role role = roleRepository.findById(updateRoleMenuDTO.getId()).get();
        role.setMenuSet(null);
        // 先清空该角色的菜单
        roleRepository.save(role);
        // 在保存新的
        role.setMenuSet(menuRepository.findAllByIdIn(updateRoleMenuDTO.getMenuIdList()));
        roleRepository.save(role);
    }

    /**
     * 新增角色
     *
     * @param addRoleDTO
     */
    @Override
    public void addRole(AddRoleDTO addRoleDTO) {
        Role role = BeanUtil.copyProperties(addRoleDTO, Role.class);
        // 判断dataScope中指定的是全部,本级还是自定义
        String dataScope = addRoleDTO.getDataScope();
        doPermission(role, dataScope);
        if (dataScope.equals(RoleConstant.CUSTOMIZE_PERMISSION)) {
            // 获取自定义中传输的菜单id集合
            List<Long> permissionList = addRoleDTO.getPermissionList();
            Set<Menu> menuSet = menuRepository.findAllByIdIn(permissionList);
            role.setMenuSet(menuSet);
        }
        // 保存用户信息
        roleRepository.save(role);
    }


    /**
     * 修改角色
     *
     * @param updateRoleDTO
     */
    @Transactional
    @Override
    public void updateRole(UpdateRoleDTO updateRoleDTO) {
        Role role = roleRepository.findById(updateRoleDTO.getId()).get();
        // 先清空数据
        role.setMenuSet(null);
        roleRepository.saveAndFlush(role);

        String name = updateRoleDTO.getName();
        Integer level = updateRoleDTO.getLevel();
        String dataScope = updateRoleDTO.getDataScope();
        String description = updateRoleDTO.getDescription();
        if (StringUtils.hasText(name) && !role.getName().equals(name)) {
            if (roleRepository.findByName(name) != null) {
                throw new RoleNameExistException(RoleConstant.ROLE_NAME_EXIST);
            }
            role.setName(name);
        }
        if (level != null && !Objects.equals(role.getLevel(), level)) {
            role.setLevel(level);
        }
        if (StringUtils.hasText(dataScope) & !role.getDataScope().equals(dataScope)) {
            role.setDataScope(dataScope);
        }
        if (StringUtils.hasText(description) && !role.getDescription().equals(description)) {
            role.setDescription(description);
        }
        // 判断dataScope中指定的是全部,本级还是自定义
        doPermission(role, dataScope);
        if (dataScope.equals(RoleConstant.CUSTOMIZE_PERMISSION)) {
            // 获取自定义中传输的菜单id集合
            Set<Menu> menuSet = menuRepository.findAllByIdIn(updateRoleDTO.getPermissionList());
            role.setMenuSet(new HashSet<>(menuSet));
        }
        roleRepository.saveAndFlush(role);
    }

    @Override
    public Result<List<Role>> getRoleByIds(List<Long> ids) {
        Set<Role> roleSet = roleRepository.findAllByIdIn(ids);
        ArrayList<Role> roles = new ArrayList<>(roleSet);
        return Result.success(roles);
    }

    /**
     * 导出角色数据
     *
     * @param httpServletResponse
     */
    @Override
    public void exportRoleData(HttpServletResponse httpServletResponse) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream("templates/角色数据.xlsx");
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("sheet1");
            List<RoleExportDataVO> roleExportDataVOList = roleRepository.findAll()
                    .stream()
                    .map(role -> BeanUtil.copyProperties(role, RoleExportDataVO.class))
                    .toList();
            int RowIndex = 1;
            for (RoleExportDataVO roleExportDataVO : roleExportDataVOList) {
                XSSFRow row = sheet.createRow(RowIndex);
                row.createCell(0).setCellValue(roleExportDataVO.getName());
                row.createCell(1).setCellValue(roleExportDataVO.getLevel().toString());
                row.createCell(2).setCellValue(roleExportDataVO.getDescription());
                row.createCell(3).setCellValue(DateTimeUtils.formatLocalDateTime(roleExportDataVO.getCreateTime()));
                RowIndex++;
            }
            // 设置请求头,让浏览器下载该文件
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            String fileName = URLEncoder.encode(DateTimeUtils.formatLocalDateTime(LocalDateTime.now()) + "角色数据.xls", "UTF-8");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            httpServletResponse.setCharacterEncoding("UTF-8");
            // 防止浏览器缓存导致的文件损坏问题
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setHeader("Pragma", "no-cache");
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
     * 根据id批量删除角色
     *
     * @param ids
     */
    @Override
    public void deleteRoleByIds(List<Long> ids) {
        // 删除角色前判断是否有用户属于该角色,有的话就不允许删除
        List<User> userList = userRepository.findByRoleIds(ids);
        if (!userList.isEmpty()) {
            throw new RoleHasUserException(RoleConstant.ROLE_HAS_USER);
        }
        roleRepository.deleteAllByIdInBatch(ids);
    }

    private void doPermission(Role role, String dataScope) {
        if (dataScope.equals(RoleConstant.ALL_PERMISSION)) {
            // 获取超级管理员中的权限
            Role superRole = roleRepository.findByName(RoleConstant.SUPER_ADMIN);
            ArrayList<Long> list = new ArrayList<>();
            list.add(superRole.getId());
            List<Menu> superRoleMenuList = menuRepository.findAllByRoleIds(list);
            role.setMenuSet(new HashSet<>(superRoleMenuList));
        }
        if (dataScope.equals(RoleConstant.SAME_LEVEL_PERMISSION)) {
            Role commonRole = roleRepository.findByName(RoleConstant.COMMON_USER);
            ArrayList<Long> list = new ArrayList<>();
            list.add(commonRole.getId());
            List<Menu> commonRoleMenuList = menuRepository.findAllByRoleIds(list);
            // 获取普通用户中的权限
            role.setMenuSet(new HashSet<>(commonRoleMenuList));
        }
    }
}
