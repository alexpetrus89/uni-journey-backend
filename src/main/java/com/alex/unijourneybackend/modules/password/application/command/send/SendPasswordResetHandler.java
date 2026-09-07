package com.alex.unijourneybackend.modules.password.application.command.send;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.infrastructure.email.EmailService;
import com.alex.unijourneybackend.modules.password.application.service.PasswordResetTokenWriter;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.infrastructure.security.CustomUserDetailsService;
import com.alex.unijourneybackend.modules.user.infrastructure.security.SecurityUserAdapter;

@Service
public class SendPasswordResetHandler {

    private static final int TOKEN_EXPIRY_MINUTES = 30;

    private final EmailService emailService;
    private final CustomUserDetailsService userService;
    private final PasswordResetTokenWriter tokenWriter;
    @Value("${app.frontend.base-url}") String frontendBaseUrl;

    public SendPasswordResetHandler(
        EmailService emailService,
        CustomUserDetailsService userService,
        PasswordResetTokenWriter tokenWriter
    ) {
        this.emailService = emailService;
        this.userService = userService;
        this.tokenWriter = tokenWriter;
    }


    public void handle(SendPasswordResetCommand command) {

        try {
            SecurityUserAdapter adapter = (SecurityUserAdapter) userService.loadUserByUsername(command.email());
            User user = adapter.getUser();
            String tokenValue = UUID.randomUUID().toString();

            // transazione reale su bean separato — niente self-invocation
            tokenWriter.save(user, tokenValue, TOKEN_EXPIRY_MINUTES);

            String link = frontendBaseUrl + "/reset-password?token=" + tokenValue;

            emailService.sendEmail(
                user.getUsername(),
                "Password reset - UniJourney",
                "Click here to reset your password: " + link
                + "\n\nThis link expires in " + TOKEN_EXPIRY_MINUTES + " minutes."
                + "\nIf you didn't request this, ignore this email."
            );
        } catch (UsernameNotFoundException _) {
            // Silenzioso: non riveliamo se l'email esiste o meno
        }
    }


}
