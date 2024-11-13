package com.jackson.controller;

import com.jackson.dto.AddTaskDTO;
import com.jackson.dto.ResumeTaskDTO;
import com.jackson.dto.UpdateTaskDTO;
import com.jackson.entity.QuartzJob;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.QuartzJobService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/quartz")
public class QuartzJobController {

    @Resource
    private QuartzJobService quartzService;

    /**
     * 获取任务列表数据
     *
     * @param page
     * @param pageSize
     * @param jobName
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('timing:list')")
    public Result<PagingResult> getQuartzJobList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String jobName,
            @RequestParam(required = false) String begin,
            @RequestParam(required = false) String end) {
        LocalDateTime beginTime = null;
        LocalDateTime endTime = null;
        if (StringUtils.hasText(begin) & StringUtils.hasText(end)) {
            beginTime = LocalDateTime.parse(begin);
            endTime = LocalDateTime.parse(end);
        }
        return quartzService.getQuartzJobList(page, pageSize, jobName, beginTime, endTime);
    }

    /**
     * 根据id获取任务信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('timing:list')")
    public Result<QuartzJob> getQuartzJobById(@PathVariable Long id) {
        return quartzService.getQuartzJobById(id);
    }

    /**
     * 新增任务调度
     *
     * @param addTaskDTO
     * @throws Exception
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('timing:list')")
    public void addJob(@RequestBody AddTaskDTO addTaskDTO) throws Exception {
        quartzService.addJob(addTaskDTO);
    }

    /**
     * 修改任务调度
     *
     * @param updateTaskDTO
     * @return
     * @throws SchedulerException
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('timing:list')")
    public void updateJob(@RequestBody UpdateTaskDTO updateTaskDTO) throws SchedulerException {
        quartzService.updateJob(updateTaskDTO);
    }

    /**
     * 删除任务调度
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('timing:list')")
    public void deleteJob(@RequestBody List<ResumeTaskDTO> resumeTaskDTOList) throws SchedulerException {
        quartzService.deleteJob(resumeTaskDTOList);
    }

    /**
     * 暂停任务调度
     *
     * @param resumeTaskDTO
     * @throws SchedulerException
     */
    @PostMapping("/pause")
    @PreAuthorize("hasAuthority('timing:list')")
    public void pauseJob(@RequestBody ResumeTaskDTO resumeTaskDTO) throws SchedulerException {
        quartzService.pauseJob(resumeTaskDTO);
    }

    /**
     * 恢复任务调度
     *
     * @param resumeTaskDTO
     * @throws SchedulerException
     */
    @PostMapping("/resume")
    @PreAuthorize("hasAuthority('timing:list')")
    public void resumeJob(@RequestBody ResumeTaskDTO resumeTaskDTO) throws SchedulerException {
        quartzService.resumeJob(resumeTaskDTO);
    }

    /**
     * 导出任务数据
     * @param httpServletResponse
     */
    @GetMapping("/export")
    @PreAuthorize("hasAuthority('timing:list')")
    public void exportQuartzJobData(HttpServletResponse httpServletResponse) {
        quartzService.exportQuartzJobData(httpServletResponse);
    }
}