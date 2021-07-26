package com.andalloy.movie.catalog.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendConfirmCode(
        String to,
        String code
    ) {
        String message = "Hello, please, to confirm your email click the link below:\n"+
                "localhost:8080/registration/confirm/" + code;

        String subject = "Confirm your email - Movie Catalog";

        sendMail(to, subject, message);
    }

    public void sendMail(
            String to,
            String subject,
            String text
    ) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom("noreply@gmail.com");
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);

    }
}
