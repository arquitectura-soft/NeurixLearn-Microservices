package org.learne.platform.notification_service.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private final String apiKey;
    private final String fromEmail;

    public EmailService(
            @Value("${SENDGRID_API_KEY}") String apiKey,
            @Value("${FROM_EMAIL}") String fromEmail
    ) {
        this.apiKey = apiKey;
        this.fromEmail = fromEmail;
    }

    public void sendUserCreatedEmail(String toEmail, String subject, String contentText) {
        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", contentText);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException ex) {
            System.err.println("❌ Error al enviar email: " + ex.getMessage());
        }
    }
}
