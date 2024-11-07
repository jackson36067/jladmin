package com.jackson.controller;

import com.jackson.entity.Dept;
import com.jackson.result.Result;
import com.jackson.service.DeptService;
import com.jackson.vo.DeptVO;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('dept:list')")
    public Result<List<DeptVO>> getDeptList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDateTime begin,
            @RequestParam(required = false) LocalDateTime end,
            @RequestParam(required = false) Boolean enabled
    ) {
        return deptService.getDeptList(name, begin, end, enabled);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('dept:list')")
    public Result<Dept> getDeptById(@PathVariable Long id) {
        return deptService.getDeptById(id);
    }
}
