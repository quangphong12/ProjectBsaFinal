package com.example.projectbsa;

import com.example.projectbsa.Service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProjectBsaApplication {

    @Autowired
    EmailSenderService emailSenderService;
    public static void main(String[] args) {
        SpringApplication.run(ProjectBsaApplication.class, args);
    }


}
