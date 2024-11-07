package com.jackson.service;

import com.jackson.dto.AddRoleDTO;
import com.jackson.dto.UpdateRoleDTO;
import com.jackson.dto.UpdateRoleMenuDTO;
import com.jackson.entity.Role;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.vo.RoleAllVO;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface RoleService {
    Result<List<RoleAllVO>> getAllRoles();

    Result<PagingResult> getRoleWithPaging(Integer page, Integer pageSize, String nameOrDescription, LocalDateTime begin, LocalDateTime end);

    void updateRoleMenuList(UpdateRoleMenuDTO updateRoleMenuDTO);

    void addRole(AddRoleDTO addRoleDTO);

    void updateRole(UpdateRoleDTO updateRoleDTO);

    Result<List<Role>> getRoleByIds(List<Long> ids);

    void exportRoleData(HttpServletResponse httpServletResponse);

    void deleteRoleByIds(List<Long> ids);
}
