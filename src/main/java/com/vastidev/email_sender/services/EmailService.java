package com.vastidev.email_sender.services;

import com.vastidev.email_sender.enums.StatusEmail;
import com.vastidev.email_sender.models.EmailModel;
import com.vastidev.email_sender.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final EmailRepository emailRepository;
    private final SesClient sesClient;

    @KafkaListener(topics = "user_registration_topic", groupId = "email-group")
    public void listenToUserRegistrationTopic(String email) {
        logger.info("Received message from Kafka: {}", email);

        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTo(email);

        emailModel.setEmailFrom("vastiane1@gmail.com");
        emailModel.setSubject("Welcome to TaskManager!");
        emailModel.setText("Hello and welcome to TaskManager! We're excited to have you on board. Feel free to explore and manage your tasks efficiently. If you have any questions, don't hesitate to reach out!");

        logger.info("EmailModel created: {}", emailModel);

        sendEmail(emailModel);
    }

    public void sendEmail(EmailModel emailModel) {
        try {
            String emailFrom = emailModel.getEmailFrom().trim();
            logger.info("Sending email from: {} to: {}", emailFrom, emailModel.getEmailTo());

            SendEmailRequest request = SendEmailRequest.builder()
                    .source(emailFrom)
                    .destination(Destination.builder().toAddresses(emailModel.getEmailTo()).build())
                    .message(Message.builder()
                            .subject(Content.builder().data(emailModel.getSubject()).build())
                            .body(Body.builder().text(Content.builder().data(emailModel.getText()).build()).build())
                            .build())
                    .build();

            logger.info("SendEmailRequest built: {}", request);

            sesClient.sendEmail(request);
            logger.info("Email sent successfully!");

            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setStatusEmail(StatusEmail.SENT);

        } catch (SesException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
            logger.error("Erro ao enviar o email: {}", e.getMessage());
        } finally {
            emailRepository.save(emailModel);
            logger.info("EmailModel saved to the database: {}", emailModel);
        }
    }
}