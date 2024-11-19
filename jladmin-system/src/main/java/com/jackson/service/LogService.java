package com.jackson.service;

import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

public interface LogService {
    Result<PagingResult> getLogWithPaging(Integer page, Integer pageSize, String location, LocalDateTime beginTime, LocalDateTime endTime);

    void exportLogInfo(HttpServletResponse response);
}
