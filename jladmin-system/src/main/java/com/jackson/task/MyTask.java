package com.jackson.task;

import com.jackson.Repository.QuartzLogRepository;
import com.jackson.entity.QuartzLog;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class MyTask implements Job {


    public MyTask() {
    }

    @Override
    public void execute(JobExecutionContext context) {
        // 执行的任务逻辑
        log.info("Executing job: {}", context.getJobDetail().getKey());
    }
}
