package com.jackson.config;

import com.jackson.properties.AliOssProperty;
import com.jackson.utils.AliOssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OssConfig {

    @Bean
    @ConditionalOnMissingBean //当IOC容器中有bean时,不会再去创建他的对象
    public AliOssUtils aliOssUtils(AliOssProperty aliOssProperty) {
        log.info("开始创建AliOSSUtils对象");
        return new AliOssUtils(aliOssProperty.getEndpoint()
                , aliOssProperty.getAccessKeyId()
                , aliOssProperty.getAccessKeySecret()
                , aliOssProperty.getBucketName()
        );

    }
}
