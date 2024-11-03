package com.jackson.controller;

import com.jackson.result.Result;
import com.jackson.service.RoleService;
import com.jackson.vo.RoleAllVO;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 获取所有角色名称集合
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('roles:list')")
    public Result<List<RoleAllVO>> getAllRoleList() {
        return roleService.getAllRoles();
    }
}
