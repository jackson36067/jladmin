package com.jackson.service;

import com.jackson.result.Result;
import com.jackson.vo.RoleAllVO;

import java.util.List;

public interface RoleService {
    Result<List<RoleAllVO>> getAllRoles();
}
