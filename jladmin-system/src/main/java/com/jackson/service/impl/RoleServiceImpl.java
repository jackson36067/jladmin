package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.Repository.RoleRepository;
import com.jackson.entity.Role;
import com.jackson.result.Result;
import com.jackson.service.RoleService;
import com.jackson.vo.RoleAllVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

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
}
