package com.jackson.utils;

import com.jackson.constant.EmailConstant;
import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailManagement {
    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送邮箱提醒有关任务操作
     *
     * @param to
     * @return
     */
    public void sendMessage(String to, String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(EmailConstant.EMAIL_FROM);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(EmailConstant.EMAIL_CODE_SUBJECT);
        simpleMailMessage.setText(code);
        mailSender.send(simpleMailMessage);
    }
}
