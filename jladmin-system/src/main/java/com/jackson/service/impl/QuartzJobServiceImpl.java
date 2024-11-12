package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.Repository.QuartzJobRepository;
import com.jackson.constant.TaskConstant;
import com.jackson.constant.UserConstant;
import com.jackson.dto.AddTaskDTO;
import com.jackson.dto.ResumeTaskDTO;
import com.jackson.dto.UpdateTaskDTO;
import com.jackson.entity.QuartzJob;
import com.jackson.exception.JobClassNotFoundException;
import com.jackson.exception.JobNameExistException;
import com.jackson.mail.MailManagement;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.QuartzJobService;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private Scheduler scheduler;
    @Resource
    private QuartzJobRepository quartzJobRepository;
    @Resource
    private MailManagement mailManagement;

    // 添加任务
    public void addJob(AddTaskDTO addTaskDTO) throws Exception {
        String className = addTaskDTO.getClassName();
        String jobName = addTaskDTO.getJobName();
        // 判断任务名称是否已经存在
        if (quartzJobRepository.findByJobName(jobName) != null) {
            throw new JobNameExistException(TaskConstant.JOB_NAME_EXIST);
        }
        String jobGroup = addTaskDTO.getJobGroup();
        String cronExpression = addTaskDTO.getCronExpression();
        // 拼接完整的类路径
        String fullClassName = TaskConstant.BASE_PACKAGE + className;
        // 使用反射加载类
        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(fullClassName);
        // 创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, jobGroup)
                .build();
        // 配置 CronTrigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, jobGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        // 调度任务
        scheduler.scheduleJob(jobDetail, trigger);
        // 如果任务状态为停用,那么先设置停用 (true: 启用 false: 暂停)
        if (addTaskDTO.getisPause()) {
            scheduler.pauseJob(new JobKey(jobName, jobGroup));
        }
        // 新增任务
        QuartzJob quartzJob = BeanUtil.copyProperties(addTaskDTO, QuartzJob.class);
        quartzJobRepository.save(quartzJob);
    }

    // 修改任务
    @Transactional
    public void updateJob(UpdateTaskDTO updateTaskDTO) throws SchedulerException {
        String jobGroup = updateTaskDTO.getJobGroup();
        String jobName = updateTaskDTO.getJobName();
        String cronExpression = updateTaskDTO.getCronExpression();
        String className = updateTaskDTO.getClassName();
        String email = updateTaskDTO.getEmail();
        String description = updateTaskDTO.getDescription();
        Boolean pause = updateTaskDTO.getisPause();
        String personInCharge = updateTaskDTO.getPersonInCharge();
        QuartzJob quartzJob = quartzJobRepository.findById(updateTaskDTO.getId()).get();
        // 如果任务名称或者任务分组改变或者执行类,那么就删除之前的任务,重新创建一个新的任务
        if (!quartzJob.getJobName().equals(jobName) || !quartzJob.getJobGroup().equals(jobGroup) || !quartzJob.getClassName().equals(className)) {
            JobKey oldJobKey = new JobKey(quartzJob.getJobName(), quartzJob.getJobGroup());
            // 判断任务是否存在
            if (!scheduler.checkExists(oldJobKey)) {
                throw new SchedulerException("Job does not exist: " + oldJobKey);
            }
            // 获取旧任务的 JobDetail
            // JobDetail oldJobDetail = scheduler.getJobDetail(oldJobKey);
            // 删除旧任务
            scheduler.deleteJob(oldJobKey);
            // 新增任务
            try {
                // 拼接完整的类路径
                String fullClassName = TaskConstant.BASE_PACKAGE + className;
                // 使用反射加载类
                Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(fullClassName);
                // 创建新的 JobDetail 和 Trigger
                JobDetail newJobDetail = JobBuilder.newJob(jobClass)
                        .withIdentity(jobName, jobGroup)
                        .build();
                CronTrigger newTrigger = TriggerBuilder.newTrigger()
                        .withIdentity(jobName, jobGroup) // 使用新任务名和任务组创建触发器
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                        .build();
                // 调度新任务
                scheduler.scheduleJob(newJobDetail, newTrigger);
            } catch (ClassNotFoundException e) {
                throw new JobClassNotFoundException(e.getMessage());
            }

        }
        // 当cron表达式改变时再改变cron表达式,重新调度任务
        if (!cronExpression.equals(quartzJob.getCronExpression())) {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            // 获取当前的触发器
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger != null) {
                // 使用 Cron 表达式创建 CronScheduleBuilder
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
                // 重新生成触发器
                CronTrigger newTrigger = trigger.getTriggerBuilder()
                        .withSchedule(scheduleBuilder)
                        .build();
                // 重新调度任务
                scheduler.rescheduleJob(triggerKey, newTrigger);
            } else {
                throw new SchedulerException("触发器不存在");
            }
            // 更新cron表达式
            quartzJob.setCronExpression(cronExpression);
        }
        // 判断状态是否改变 (改变那么就要判断是暂停还是恢复了)
        if (pause != quartzJob.getisPause()) {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            if (pause) {
                scheduler.pauseJob(jobKey);
            } else {
                scheduler.resumeJob(jobKey);
            }
            quartzJob.setisPause(pause);
        }
        // 更新任务数据库
        if (!jobName.equals(quartzJob.getJobName())) {
            quartzJob.setJobName(jobName);
        }
        if (!jobGroup.equals(quartzJob.getJobGroup())) {
            quartzJob.setJobGroup(jobGroup);
        }
        if (!className.equals(quartzJob.getClassName())) {
            quartzJob.setClassName(className);
        }
        if (!email.equals(quartzJob.getEmail())) {
            quartzJob.setEmail(email);
        }
        if (!description.equals(quartzJob.getDescription())) {
            quartzJob.setDescription(description);
        }

        if (!personInCharge.equals(quartzJob.getPersonInCharge())) {
            quartzJob.setPersonInCharge(personInCharge);
        }
        // 更新任务调度
        quartzJobRepository.saveAndFlush(quartzJob);
    }

    // 删除任务
    public void deleteJob(List<ResumeTaskDTO> resumeTaskDTOList) throws SchedulerException {
        resumeTaskDTOList.forEach(resumeTaskDTO -> {
            JobKey jobKey = new JobKey(resumeTaskDTO.getJobName(), resumeTaskDTO.getJobGroup());
            try {
                boolean exists = scheduler.checkExists(jobKey);
                if (exists) {
                    scheduler.deleteJob(jobKey);
                }
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        });
        // 删除自定义数据库中的数据
        List<Long> idList = resumeTaskDTOList.stream().map(ResumeTaskDTO::getId).toList();
        quartzJobRepository.deleteAllByIdInBatch(idList);
    }

    // 暂停任务
    public void pauseJob(ResumeTaskDTO resumeTaskDTO) throws SchedulerException {
        scheduler.pauseJob(new JobKey(resumeTaskDTO.getJobName(), resumeTaskDTO.getJobGroup()));
        Long id = resumeTaskDTO.getId();
        QuartzJob quartzJob = quartzJobRepository.findById(id).get();
        quartzJob.setisPause(true);
        quartzJobRepository.saveAndFlush(quartzJob);
    }

    // 恢复任务
    public void resumeJob(ResumeTaskDTO resumeTaskDTO) throws SchedulerException {
        // 确保任务存在
        JobKey jobKey = new JobKey(resumeTaskDTO.getJobName(), resumeTaskDTO.getJobGroup());
        if (!scheduler.checkExists(jobKey)) {
            throw new SchedulerException("Job does not exist: " + jobKey);
        }
        scheduler.resumeJob(jobKey);
        Long id = resumeTaskDTO.getId();
        QuartzJob quartzJob = quartzJobRepository.findById(id).get();
        quartzJob.setisPause(false);
        quartzJobRepository.saveAndFlush(quartzJob);
    }

    // 查看任务日志
    public List<? extends Trigger> getJobLog(String jobName, String jobGroup) throws SchedulerException {
        return scheduler.getTriggersOfJob(new JobKey(jobName, jobGroup));
    }

    /**
     * 根据条件获取任务列表
     *
     * @param jobName
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public Result<PagingResult> getQuartzJobList(Integer page, Integer pageSize, String jobName, LocalDateTime beginTime, LocalDateTime endTime) {
        Specification<QuartzJob> quartzJobSpecification = (root, query, cb) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(jobName)) {
                Predicate predicate = cb.like(root.get("jobName"), "%" + jobName + "%");
                predicates.add(predicate);
            }
            if (beginTime != null & endTime != null) {
                Predicate createTime = cb.between(root.get("createTime"), beginTime, endTime);
                predicates.add(createTime);
            }
            Predicate[] predicates1 = new Predicate[predicates.size()];
            return cb.and(predicates.toArray(predicates1));
        };
        // 排序器
        Sort sort = Sort.by(Sort.Direction.DESC, UserConstant.CREATE_TIME);
        // 分页器
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
        Page<QuartzJob> quartzJobRepositoryAll = quartzJobRepository.findAll(quartzJobSpecification, pageRequest);
        List<QuartzJob> content = quartzJobRepositoryAll.getContent();
        PagingResult pagingResult = new PagingResult(content.size(), content);
        return Result.success(pagingResult);
    }

    /**
     * 根据id获取任务信息
     *
     * @param id
     * @return
     */
    @Override
    public Result<QuartzJob> getQuartzJobById(Long id) {
        return Result.success(quartzJobRepository.findById(id).get());
    }
}
