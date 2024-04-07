package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

// https://dev.to/vatana7/how-to-send-email-in-springboot-springboot-tutorial-4jjm

@Configuration
public class MailConfig {
    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String protocol;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String tlsEnable;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private Integer port;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host); // "smtp.gmail.com"
        mailSender.setPort(port); // 587

        mailSender.setUsername(email); // "your@gmail.com"
        mailSender.setPassword(password); // "copiedPassword"

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol); // "smtp"
        props.put("mail.smtp.auth", auth); // "true"
        props.put("mail.smtp.starttls.enable", tlsEnable); // "true"
        props.put("mail.debug", "true");

        return mailSender;
    }
}