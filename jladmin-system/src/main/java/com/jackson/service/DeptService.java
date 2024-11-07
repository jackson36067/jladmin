package com.jackson.service;

import com.jackson.entity.Dept;
import com.jackson.result.Result;
import com.jackson.vo.DeptVO;

import java.time.LocalDateTime;
import java.util.List;

public interface DeptService {
    Result<List<DeptVO>> getDeptList(String name, LocalDateTime begin, LocalDateTime end, Boolean enabled);

    public List<Long> getAllSubDeptIds(Long parentId);

    Result<Dept> getDeptById(Long id);
}
