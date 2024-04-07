package com.example.demo.mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivationEmail(String to, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Auth");
        /*
        <div>
            <h1>In order to activate your account smash the Link down below</h1>
            <a href=\"" + link + "\">" + link + "</a>
        </div>
        */
        message.setText(link);
        mailSender.send(message);
    }
}
