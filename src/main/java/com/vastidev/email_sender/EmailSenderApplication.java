package com.vastidev.email_sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EmailSenderApplication {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Email Sender Api.";
    }
    public static void main(String[] args) {
        SpringApplication.run(EmailSenderApplication.class, args);
    }

}
