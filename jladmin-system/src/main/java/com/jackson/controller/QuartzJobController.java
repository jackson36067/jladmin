package com.jackson.controller;

import com.jackson.annotation.SysLog;
import com.jackson.constant.TaskConstant;
import com.jackson.dto.AddTaskDTO;
import com.jackson.dto.TaskDTO;
import com.jackson.dto.UpdateTaskDTO;
import com.jackson.entity.QuartzJob;
import com.jackson.enumeration.SysLogType;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.QuartzJobService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.quartz.SchedulerException;
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
    @SysLog(value = TaskConstant.ADD_JOB_LOG, type = SysLogType.ADD)
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
    @SysLog(value = TaskConstant.UPDATE_JOB_LOG, type = SysLogType.UPDATE)
    public void updateJob(@RequestBody UpdateTaskDTO updateTaskDTO) throws SchedulerException {
        quartzService.updateJob(updateTaskDTO);
    }

    /**
     * 删除任务调度
     *
     * @param taskDTOList
     * @return
     * @throws SchedulerException
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('timing:list')")
    @SysLog(value = TaskConstant.DELETE_JOB_LOG, type = SysLogType.DELETE)
    public void deleteJob(@RequestBody List<TaskDTO> taskDTOList) throws SchedulerException {
        quartzService.deleteJob(taskDTOList);
    }

    /**
     * 暂停任务调度
     *
     * @param taskDTO
     * @throws SchedulerException
     */
    @PostMapping("/pause")
    @PreAuthorize("hasAuthority('timing:list')")
    @SysLog(value = TaskConstant.PAUSE_JOB_LOG, type = SysLogType.PAUSE)
    public void pauseJob(@RequestBody TaskDTO taskDTO) throws SchedulerException {
        quartzService.pauseJob(taskDTO);
    }

    /**
     * 恢复任务调度
     *
     * @param taskDTO
     * @throws SchedulerException
     */
    @PostMapping("/resume")
    @PreAuthorize("hasAuthority('timing:list')")
    @SysLog(value = TaskConstant.RESUME_JOB_LOG, type = SysLogType.RESUME)
    public void resumeJob(@RequestBody TaskDTO taskDTO) throws SchedulerException {
        quartzService.resumeJob(taskDTO);
    }

    /**
     * 导出任务数据
     *
     * @param httpServletResponse
     */
    @GetMapping("/export")
    @PreAuthorize("hasAuthority('timing:list')")
    public void exportQuartzJobData(HttpServletResponse httpServletResponse) {
        quartzService.exportQuartzJobData(httpServletResponse);
    }
}