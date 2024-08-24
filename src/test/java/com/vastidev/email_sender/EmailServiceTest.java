package com.vastidev.email_sender;

import com.vastidev.email_sender.enums.StatusEmail;
import com.vastidev.email_sender.models.EmailModel;
import com.vastidev.email_sender.repositories.EmailRepository;
import com.vastidev.email_sender.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private SesClient sesClient;

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailService emailService;

    private EmailModel emailModel;

    @BeforeEach
    void setup(){
        emailModel = new EmailModel();
        emailModel.setEmailFrom("from@gmail.com");
        emailModel.setEmailTo("to@gmail.com");
        emailModel.setSubject("Test subject");
        emailModel.setText("Test body");
    }

    @Test
    public void testSendEmailSuccess(){

        emailService.sendEmail(emailModel);

        Mockito.verify(sesClient, Mockito.times(1)).sendEmail(Mockito.any(SendEmailRequest.class));
        Mockito.verify(emailRepository, Mockito.times(1)).save(emailModel);
        assertThat(emailModel.getStatusEmail()).isEqualTo(StatusEmail.SENT);
    }

    @Test
    public void testSendEmailFailure() {
        Mockito.doThrow(SesException.class).when(sesClient).sendEmail(Mockito.any(SendEmailRequest.class));

        emailService.sendEmail(emailModel);

        Mockito.verify(sesClient, Mockito.times(1)).sendEmail(Mockito.any(SendEmailRequest.class));
        Mockito.verify(emailRepository, Mockito.times(1)).save(emailModel);
        assertThat(emailModel.getStatusEmail()).isEqualTo(StatusEmail.ERROR);
    }

    @Test
    public void testListenToUserRegistrationTopic(){
        String email = "to@gmail.com";

        ArgumentCaptor<EmailModel> emailModelArgumentCaptor = ArgumentCaptor.forClass(EmailModel.class);

        emailService.listenToUserRegistrationTopic(email);

        Mockito.verify(emailRepository).save(emailModelArgumentCaptor.capture());
        EmailModel capturedEmailModel = emailModelArgumentCaptor.getValue();

        assertThat(capturedEmailModel.getEmailTo()).isEqualTo(email);
        assertThat(capturedEmailModel.getEmailFrom()).isEqualTo("vastiane1@gmail.com");
        assertThat(capturedEmailModel.getSubject()).isEqualTo("Welcome to TaskManager!");
        assertThat(capturedEmailModel.getText()).isEqualTo("Hello and welcome to TaskManager! We're excited to have you on board. Feel free to explore and manage your tasks efficiently. If you have any questions, don't hesitate to reach out!");

    }
}
