package org.dre.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmailService {
//    @Inject
//
//    MailerClient mailerClient;
//
//    public void sendEmail(String to, String subject, String body) {
//        try {
//            MimeMessage message = new MimeMessage(mailerClient);
//            message.setFrom(new InternetAddress("your-outlook-email@domain.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//            message.setSubject(subject);
//            message.setText(body);
//            mailerClient.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Failed to send email", e);
//        }
//    }
}
