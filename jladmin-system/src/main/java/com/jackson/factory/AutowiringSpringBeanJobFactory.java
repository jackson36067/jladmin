package com.jackson.factory;

import jakarta.annotation.Resource;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 自动注入quartz任务到spring环境工厂
 */
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory {
    @Resource
    private ApplicationContext applicationContext;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 创建job实例
        Object jobInstance = super.createJobInstance(bundle);
        // 将job实例注入到spring容器中
        applicationContext.getAutowireCapableBeanFactory().autowireBean(jobInstance);
        return jobInstance;
    }
}
 