package com.alex.unijourneybackend.infrastructure.email;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    /**
     * Sends an email to the specified recipient with the given subject and body.
     * @param String to
     * @param String subject
     * @param String body
     * @see SimpleMailMessage
     */
    void sendEmail(String to, String subject, String body);

}
