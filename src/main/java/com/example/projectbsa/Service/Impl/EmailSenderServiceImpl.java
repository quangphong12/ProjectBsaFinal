package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    JavaMailSender javaMailSender;
    public void sendSimpleEmail(String toEmail, String body,String subject){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("ngoquangphong1212@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
        System.out.println("Mail Send ...");
    }
}
