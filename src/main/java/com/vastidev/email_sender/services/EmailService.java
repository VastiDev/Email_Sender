package com.vastidev.email_sender.services;

import com.vastidev.email_sender.enums.StatusEmail;
import com.vastidev.email_sender.models.EmailModel;
import com.vastidev.email_sender.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final SesClient sesClient;

    public void sendEmail(EmailModel emailModel) {
        try{
            SendEmailRequest request = SendEmailRequest.builder()
                    .source(emailModel.getEmailFrom())
                    .destination(Destination.builder().toAddresses(emailModel.getEmailTo()).build())
                    .message(Message.builder()
                            .subject(Content.builder().data(emailModel.getSubject()).build())
                            .body(Body.builder().text(Content.builder().data(emailModel.getText()).build()).build())
                            .build())
                    .build();
            sesClient.sendEmail(request);

            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setStatusEmail(StatusEmail.SENT);

        }catch (SesException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
            System.err.println("Erro ao enviar o email: " + e.getMessage());
        }finally {
            emailRepository.save(emailModel);
        }

    }
}
