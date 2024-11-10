package com.jackson.controller;

import com.jackson.dto.AddDeptDTO;
import com.jackson.dto.UpdateDeptDTO;
import com.jackson.entity.Dept;
import com.jackson.result.Result;
import com.jackson.service.DeptService;
import com.jackson.vo.DeptVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    /**
     * 获取部门列表
     *
     * @param name
     * @param begin
     * @param end
     * @param enabled
     * @return
     */
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

    /**
     * 根据id获取部门
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('dept:list')")
    public Result<Dept> getDeptById(@PathVariable Long id) {
        return deptService.getDeptById(id);
    }

    /**
     * 新增部门
     *
     * @param addDeptDTO
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('dept:list')")
    public void addDept(@RequestBody AddDeptDTO addDeptDTO) {
        deptService.addDept(addDeptDTO);
    }

    /**
     * 编辑部门
     *
     * @param updateDeptDTO
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('dept:list')")
    public void updateDept(@RequestBody UpdateDeptDTO updateDeptDTO) {
        deptService.updateDept(updateDeptDTO);
    }

    /**
     * 批量删除部门
     *
     * @param ids
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('dept:list')")
    public void deleteDeptByIds(@RequestBody List<Long> ids) {
        deptService.deleteDeptByIds(ids);
    }

    /**
     * 导出部门数据
     * @param httpServletResponse
     */
    @GetMapping("/export")
    @PreAuthorize("hasAuthority('dept:list')")
    public void exportDeptData(HttpServletResponse httpServletResponse) {
        deptService.exportDeptData(httpServletResponse);
    }
}
