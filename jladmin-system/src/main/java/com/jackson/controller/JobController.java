package com.jackson.controller;

import com.jackson.entity.Job;
import com.jackson.result.Result;
import com.jackson.service.JobService;
import com.jackson.vo.JobEnabledVO;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/job")
public class JobController {

    @Resource
    private JobService jobService;

    /**
     * 获取所有激活的岗位的集合
     * @return
     */
    @GetMapping("/enabled/list")
    @PreAuthorize("hasAuthority('job:list')")
    public Result<List<JobEnabledVO>> getEnabledJobList() {
        return jobService.getEnabledJobList();
    }

    /**
     * 根据id集合获取所有岗位
     * @param ids
     * @return
     */
    @PostMapping("/ids")
    @PreAuthorize("hasAuthority('job:list')")
    public Result<List<Job>> getJobByIds(@RequestBody List<Long> ids){
        return jobService.getJobByIds(ids);
    }
}
