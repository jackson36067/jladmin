package com.jackson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//EnableScheduling 开启定时任务
@EnableScheduling
public class JladminSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JladminSystemApplication.class, args);
    }

}
