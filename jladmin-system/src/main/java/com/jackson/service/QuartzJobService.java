package com.jackson.service;

import com.jackson.dto.AddTaskDTO;
import com.jackson.dto.TaskDTO;
import com.jackson.dto.UpdateTaskDTO;
import com.jackson.entity.QuartzJob;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.quartz.SchedulerException;

import java.time.LocalDateTime;
import java.util.List;

public interface QuartzJobService {
    void addJob(AddTaskDTO addTaskDTO) throws Exception;

    void updateJob(UpdateTaskDTO updateTaskDTO) throws SchedulerException;

    void deleteJob( List<TaskDTO> taskDTOList) throws SchedulerException;

    void pauseJob(TaskDTO taskDTO) throws SchedulerException;

    void resumeJob(TaskDTO taskDTO) throws SchedulerException;

    Result<PagingResult> getQuartzJobList(Integer page, Integer pageSize, String jobName, LocalDateTime beginTime, LocalDateTime endTime);

    Result<QuartzJob> getQuartzJobById(Long id);

    void exportQuartzJobData(HttpServletResponse httpServletResponse);
}
