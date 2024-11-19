package com.jackson.controller;

import com.jackson.annotation.SysLog;
import com.jackson.constant.RoleConstant;
import com.jackson.dto.AddRoleDTO;
import com.jackson.dto.UpdateRoleDTO;
import com.jackson.dto.UpdateRoleMenuDTO;
import com.jackson.entity.Role;
import com.jackson.enumeration.SysLogType;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.RoleService;
import com.jackson.vo.RoleAllVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 获取所有角色名称集合
     *
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('roles:list')")
    public Result<List<RoleAllVO>> getAllRoleList() {
        return roleService.getAllRoles();
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
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('roles:list')")
    public Result<PagingResult> getRoleWithPaging(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String nameOrDescription,
            @RequestParam(required = false) String begin,
            @RequestParam(required = false) String end
    ) {
        LocalDateTime beginTime = null;
        LocalDateTime endTime = null;
        if (StringUtils.hasText(begin) & StringUtils.hasText(end)) {
            beginTime = LocalDateTime.parse(begin);
            endTime = LocalDateTime.parse(end);
        }
        return roleService.getRoleWithPaging(page, pageSize, nameOrDescription, beginTime, endTime);
    }

    /**
     * 修改角色对应菜单
     *
     * @param updateRoleMenuDTO
     */
    @PutMapping("/update/menu")
    @PreAuthorize("hasAuthority('roles:list')")
    @SysLog(value = RoleConstant.UPDATE_ROLE_MENU_LOG, type = SysLogType.UPDATE)
    public void updateRoleMenuList(@RequestBody UpdateRoleMenuDTO updateRoleMenuDTO) {
        roleService.updateRoleMenuList(updateRoleMenuDTO);
    }

    /**
     * 新增角色
     *
     * @param addRoleDTO
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('roles:list')")
    @SysLog(value = RoleConstant.ADD_ROLE_LOG, type = SysLogType.ADD)
    public void addRole(@RequestBody AddRoleDTO addRoleDTO) {
        roleService.addRole(addRoleDTO);
    }

    /**
     * 修改角色
     *
     * @param updateRoleDTO
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('roles:list')")
    @SysLog(value = RoleConstant.UPDATE_ROLE_LOG, type = SysLogType.UPDATE)
    public void updateRole(@RequestBody UpdateRoleDTO updateRoleDTO) {
        roleService.updateRole(updateRoleDTO);
    }


    /**
     * 导出用户数据
     *
     * @param ids
     * @return
     */
    @PostMapping("/ids")
    @PreAuthorize("hasAuthority('roles:list')")
    public Result<List<Role>> getRoleByIds(@RequestBody List<Long> ids) {
        return roleService.getRoleByIds(ids);
    }

    /**
     * 导出角色数据
     *
     * @param httpServletResponse
     */
    @PostMapping("/export")
    @PreAuthorize("hasAuthority('roles:list')")
    public void exportRoleData(HttpServletResponse httpServletResponse) {
        roleService.exportRoleData(httpServletResponse);

    }

    /**
     * 根据id删除批量删除角色
     *
     * @param ids
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('roles:list')")
    @SysLog(value = RoleConstant.DELETE_ROLE_LOG, type = SysLogType.DELETE)
    public void deleteRoleByIds(@RequestBody List<Long> ids) {
        roleService.deleteRoleByIds(ids);
    }
}
