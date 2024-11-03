package com.jackson.service;

import com.jackson.result.Result;
import com.jackson.vo.JobEnabledVO;

import java.util.List;

public interface JobService {
    Result<List<JobEnabledVO>> getEnabledJobList();
}
