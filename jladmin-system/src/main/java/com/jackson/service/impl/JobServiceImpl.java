package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.Repository.JobRepository;
import com.jackson.entity.Job;
import com.jackson.result.Result;
import com.jackson.service.JobService;
import com.jackson.vo.JobEnabledVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Resource
    private JobRepository jobRepository;

    /**
     * 获取所有激活的岗位集合
     *
     * @return
     */
    @Override
    public Result<List<JobEnabledVO>> getEnabledJobList() {
        List<JobEnabledVO> jobEnabledVOList = jobRepository
                .findAllByEnabled(true)
                .stream()
                .map(enabledJob -> BeanUtil.copyProperties(enabledJob, JobEnabledVO.class))
                .toList();
        return Result.success(jobEnabledVOList);
    }
}
