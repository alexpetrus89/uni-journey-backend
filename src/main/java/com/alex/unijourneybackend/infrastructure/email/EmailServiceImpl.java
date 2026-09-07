package com.alex.unijourneybackend.infrastructure.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    // instance variables
    private final JavaMailSender sender;

    // autowired - dependency injection - constructor
    public EmailServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }


    /**
     * Sends an email to the specified recipient with the given subject and body.
     * @param String to
     * @param String subject
     * @param String body
     * @see SimpleMailMessage
     */
    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        sender.send(mailMessage);
    }
}
