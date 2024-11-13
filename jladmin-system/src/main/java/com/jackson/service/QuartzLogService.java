package com.jackson.service;

import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

public interface QuartzLogService {
    Result<PagingResult> getQuartzLogPaging(Integer page, Integer pageSize, String jobName, LocalDateTime begin, LocalDateTime end, Boolean isSuccess);

    void exportQuartzLogData(HttpServletResponse httpServletResponse);
}
