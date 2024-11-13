package com.jackson.Logger;

import com.jackson.Repository.QuartzJobRepository;
import com.jackson.Repository.QuartzLogRepository;
import com.jackson.entity.QuartzJob;
import com.jackson.entity.QuartzLog;
import com.jackson.mail.MailManagement;
import jakarta.annotation.Resource;
import org.quartz.*;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class JobExecutionLogger extends JobListenerSupport {

    @Resource
    private QuartzLogRepository quartzLogRepository;
    @Resource
    private QuartzJobRepository quartzJobRepository;
    @Resource
    private MailManagement mailManagement;

    private static final Logger logger = Logger.getLogger(JobExecutionLogger.class.getName());

    @Override
    public String getName() {
        return "JobExecutionLogger";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        context.put("startTime", System.currentTimeMillis());
        logger.info("任务开始执行: " + context.getJobDetail().getKey());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        long startTime = (long) context.get("startTime");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        JobKey key = context.getJobDetail().getKey();
        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setJobName(key.getName());
        quartzLog.setTime(executionTime);
        Trigger trigger = context.getTrigger();
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String cronExpression = cronTrigger.getCronExpression();
            quartzLog.setCronExpression(cronExpression);
        }
        quartzLog.setCreateTime(LocalDateTime.now());
        quartzLog.setClassName(context.getJobDetail().getJobClass().toString());
        quartzLog.setisSuccess(true);
        logger.info("Job was executed: " + context.getJobDetail().getKey());
        if (jobException != null) {
            quartzLog.setExceptionDetail(jobException.getMessage());
            quartzLog.setisSuccess(false);
            // 任务执行失败,进行邮箱告警
            QuartzJob jobGroup = quartzJobRepository.findByJobNameAndJobGroup(key.getName(), key.getGroup());
            mailManagement.sendMessage(jobGroup.getEmail(), "任务执行异常,异常信息: " + jobException.getMessage());
            logger.warning("任务执行异常,异常信息: " + jobException.getMessage());
        }
        quartzLogRepository.save(quartzLog);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        // 执行被拒绝时的逻辑
        System.out.println("Job " + context.getJobDetail().getKey() + " 被拒绝执行");
    }
}
