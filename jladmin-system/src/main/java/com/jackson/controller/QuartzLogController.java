package com.jackson.controller;

import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.QuartzLogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/quartz/log")
public class QuartzLogController {

    @Resource
    private QuartzLogService quartzLogService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('timing:list')")
    public Result<PagingResult> getQuartzLogPaging(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "6") Integer pageSize,
            @RequestParam(required = false) String jobName,
            @RequestParam(required = false) String begin,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) Boolean isSuccess
    ) {
        LocalDateTime beginTime = null;
        LocalDateTime endTime = null;
        if (StringUtils.hasText(begin) & StringUtils.hasText(end)) {
            beginTime = LocalDateTime.parse(begin);
            endTime = LocalDateTime.parse(end);
        }
        return quartzLogService.getQuartzLogPaging(page, pageSize, jobName, beginTime, endTime, isSuccess);
    }

    /**
     * 导出任务日志数据
     * @param httpServletResponse
     */
    @GetMapping("/export")
    @PreAuthorize("hasAuthority('timing:list')")
    public void exportQuartzLogData (HttpServletResponse httpServletResponse){
        quartzLogService.exportQuartzLogData(httpServletResponse);
    }
}
