package com.jackson.service;

import com.jackson.dto.UpdateRoleMenuDTO;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.vo.RoleAllVO;

import java.time.LocalDateTime;
import java.util.List;

public interface RoleService {
    Result<List<RoleAllVO>> getAllRoles();

    Result<PagingResult> getRoleWithPaging(Integer page, Integer pageSize, String nameOrDescription, LocalDateTime begin, LocalDateTime end);

    void updateRoleMenuList(UpdateRoleMenuDTO updateRoleMenuDTO);
}
