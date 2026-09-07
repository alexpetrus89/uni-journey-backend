package com.alex.unijourneybackend.modules.password.domain.port;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.password.domain.PasswordResetToken;
import com.alex.unijourneybackend.modules.user.domain.model.User;

public interface PasswordResetTokenRepositoryPort {

    @NonNull
    Optional<PasswordResetToken> findByToken(@NonNull String token);


    @NonNull
    List<PasswordResetToken> findAllByUserAndUsedFalse(@NonNull User user);

    @NonNull
    PasswordResetToken create(@NonNull PasswordResetToken token);

}
