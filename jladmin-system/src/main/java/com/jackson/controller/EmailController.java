package com.jackson.controller;

import com.jackson.dto.EmailDTO;
import com.jackson.utils.MailManagement;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/email")
public class EmailController {

    @Resource
    private MailManagement mailManagement;

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailDTO emailDTO) {
        mailManagement.sendMessage(emailDTO.getTo(),emailDTO.getSubject(),emailDTO.getText());
    }
}
