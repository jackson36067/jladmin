package com.jackson.service;

import com.jackson.entity.Job;
import com.jackson.result.Result;
import com.jackson.vo.JobEnabledVO;

import java.util.List;

public interface JobService {
    Result<List<JobEnabledVO>> getEnabledJobList();

    Result<List<Job>> getJobByIds(List<Long> ids);
}
