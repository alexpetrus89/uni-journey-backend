package com.alex.unijourneybackend.modules.password.application.command.reset;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.password.domain.PasswordResetToken;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;
import com.alex.unijourneybackend.modules.password.domain.port.PasswordResetTokenRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.port.out.PasswordEncoderPort;

@Service
public class ResetPasswordHandler {

    private final PasswordResetTokenRepositoryPort port;
    private final PasswordEncoderPort encoder;
    private final PasswordRuleEngine ruleEngine;

    public ResetPasswordHandler(
        PasswordResetTokenRepositoryPort port,
        PasswordEncoderPort encoder,
        PasswordRuleEngine ruleEngine
    ) {
        this.port = port;
        this.encoder = encoder;
        this.ruleEngine = ruleEngine;
    }

    @Transactional
    public void handle(ResetPasswordCommand command) {

        String stringToken = Objects.requireNonNull(command.token());

        PasswordResetToken token = port
            .findByToken(stringToken)
            .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (token.isUsed() || token.isExpired())
            throw new IllegalArgumentException("Token expired or already used");

        User user = token.getUser();

        // domain
        user.changePassword(encoder, ruleEngine, command.newPassword());

        token.setUsed(true);

        invalidateOtherTokens(user);

        port.create(token);
    }

    private void invalidateOtherTokens(User user) {
        Objects.requireNonNull(user);
        port.findAllByUserAndUsedFalse(user).forEach(t -> t.setUsed(true));
    }


}
