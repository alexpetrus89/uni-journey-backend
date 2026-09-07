package com.alex.unijourneybackend.modules.password.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.password.domain.PasswordResetToken;
import com.alex.unijourneybackend.modules.password.domain.port.PasswordResetTokenRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.model.User;

@Component
public class PasswordResetTokenAdapter implements PasswordResetTokenRepositoryPort {

    private final PasswordResetTokenRepository repository;

    public PasswordResetTokenAdapter(PasswordResetTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public Optional<PasswordResetToken> findByToken(String token) {
        return repository.findByToken(token);
    }

    @Override
    @SuppressWarnings("null")
    public List<PasswordResetToken> findAllByUserAndUsedFalse(User user) {
        return repository.findAllByUserAndUsedFalse(user);
    }

    @Override
    @SuppressWarnings("null")
    public PasswordResetToken create(PasswordResetToken token) {
        return repository.save(token);
    }


}
