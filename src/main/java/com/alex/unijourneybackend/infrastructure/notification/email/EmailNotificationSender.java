package com.alex.unijourneybackend.infrastructure.notification.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.infrastructure.email.EmailService;
import com.alex.unijourneybackend.modules.notification.port.out.NotificationSender;

@Component
public class EmailNotificationSender implements NotificationSender {

    private final EmailService service;

    public EmailNotificationSender(EmailService service) {
        this.service = service;
    }

    /**
     * Sends an email to the specified recipient with the given subject and body.
     * @param String to
     * @param String subject
     * @param String body
     * @see SimpleMailMessage
     */
    @Override
    public void send(String username, String subject, String message) {
        service.sendEmail(username, subject, message);
    }


}
