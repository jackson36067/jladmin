package com.jackson.controller;

import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.LogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 分页展示操作日志
     * @param page
     * @param pageSize
     * @param location
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/list")
    public Result<PagingResult> getLogWithPaging(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "9") Integer pageSize,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String begin,
            @RequestParam(required = false) String end
    ) {
        LocalDateTime beginTime = null;
        LocalDateTime endTime = null;
        if (StringUtils.hasText(begin) & StringUtils.hasText(end)) {
            beginTime = LocalDateTime.parse(begin);
            endTime = LocalDateTime.parse(end);
        }
        return logService.getLogWithPaging(page, pageSize, location, beginTime, endTime);
    }

    /**
     * 导出日志数据
     * @param response
     */
    @GetMapping("/export")
    public void exportLogInfo(HttpServletResponse response){
        logService.exportLogInfo(response);
    }
}
