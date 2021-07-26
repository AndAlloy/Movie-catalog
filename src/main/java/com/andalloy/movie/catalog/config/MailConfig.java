package com.andalloy.movie.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private final MailProperties properties;

    public MailConfig(MailProperties properties) {
        this.properties = properties;
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.gmail.com");
        sender.setPort(587);

        sender.setUsername(properties.getUsername());
        sender.setPassword(properties.getPassword());

        Properties properties = sender.getJavaMailProperties();

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        return sender;
    }
}
