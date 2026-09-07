package com.alex.unijourneybackend.modules.password.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.unijourneybackend.modules.password.domain.PasswordResetToken;
import com.alex.unijourneybackend.modules.user.domain.model.User;

import jakarta.persistence.PersistenceException;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    /**
     * Find a password reset token by its token string.
     * @param token the token string
     * @return Optional<PasswordResetToken>
     * @throws PersistenceException persistence error
     */
    Optional<PasswordResetToken> findByToken(String token);


    /**
     * Find all password reset tokens for a specific user that have not been used.
     * @param user the user
     * @return List<PasswordResetToken>
     */
    List<PasswordResetToken> findAllByUserAndUsedFalse(User user);

}
