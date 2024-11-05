package com.jackson.controller;

import com.jackson.dto.UpdateRoleMenuDTO;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.RoleService;
import com.jackson.vo.RoleAllVO;
import jakarta.annotation.Resource;
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

    @PutMapping("/update/menu")
    @PreAuthorize("hasAuthority('roles:list')")
    public void updateRoleMenuList(@RequestBody UpdateRoleMenuDTO updateRoleMenuDTO) {
        roleService.updateRoleMenuList(updateRoleMenuDTO);
    }
}
