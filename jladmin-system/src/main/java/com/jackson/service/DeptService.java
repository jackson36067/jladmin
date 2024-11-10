package com.jackson.service;

import com.jackson.dto.AddDeptDTO;
import com.jackson.dto.UpdateDeptDTO;
import com.jackson.entity.Dept;
import com.jackson.result.Result;
import com.jackson.vo.DeptVO;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface DeptService {
    Result<List<DeptVO>> getDeptList(String name, LocalDateTime begin, LocalDateTime end, Boolean enabled);

    public List<Long> getAllSubDeptIds(Long parentId);

    Result<Dept> getDeptById(Long id);

    void addDept(AddDeptDTO addDeptDTO);

    void updateDept(UpdateDeptDTO updateDeptDTO);

    void deleteDeptByIds(List<Long> ids);

    void exportDeptData(HttpServletResponse httpServletResponse);
}
