package com.vastidev.email_sender.controllers;

import com.vastidev.email_sender.dtos.EmailDto;
import com.vastidev.email_sender.models.EmailModel;
import com.vastidev.email_sender.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sending-email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    @PostMapping
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
