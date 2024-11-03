package com.jackson.controller;

import com.jackson.result.Result;
import com.jackson.service.JobService;
import com.jackson.vo.JobEnabledVO;
import jakarta.annotation.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
