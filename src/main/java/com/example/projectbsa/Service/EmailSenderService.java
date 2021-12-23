package com.example.projectbsa.Service;

import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendSimpleEmail(String toEmail, String body,String subject);
}
