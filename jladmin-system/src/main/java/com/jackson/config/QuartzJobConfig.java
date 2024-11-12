package com.jackson.config;

import com.jackson.Logger.JobExecutionLogger;
import com.jackson.factory.AutowiringSpringBeanJobFactory;
import jakarta.annotation.Resource;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

@Configuration
public class QuartzJobConfig {

    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private DataSource dataSource;
    @Resource
    private JobExecutionLogger jobExecutionLogger;

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * 配置数据库持久化
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);  // 使用数据库作为存储
        factory.setJobFactory(springBeanJobFactory());
        factory.setOverwriteExistingJobs(false);  // 避免每次启动时覆盖现有的任务
        factory.setStartupDelay(10);  // 延迟启动，确保数据源已经初始化完成
        factory.setApplicationContextSchedulerContextKey("applicationContext");  // 设置 Spring 上下文
        factory.setWaitForJobsToCompleteOnShutdown(true);  // 关闭时等待任务完成
        return factory;
    }

    /**
     * 配置任务日志并返回调度器实例
     */
    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.getListenerManager().addJobListener(jobExecutionLogger);
        return scheduler;
    }
}
