package com.alex.unijourneybackend.modules.password.application.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.password.domain.PasswordResetToken;
import com.alex.unijourneybackend.modules.password.domain.port.PasswordResetTokenRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.model.User;

@Component
public class PasswordResetTokenWriter {

    private final PasswordResetTokenRepositoryPort port;

    public PasswordResetTokenWriter(PasswordResetTokenRepositoryPort port) {
        this.port = port;
    }

    @Transactional
    public void save(User user, String tokenValue, int expiryMinutes) {
        port.create(new PasswordResetToken(tokenValue, user, expiryMinutes));
    }


}
