package com.alex.unijourneybackend.modules.user.domain.valueobject;



import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;
import com.alex.unijourneybackend.modules.notification.domain.event.PasswordChangedEvent;
import com.alex.unijourneybackend.modules.password.domain.PasswordHistory;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordValidationResult;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.port.out.PasswordEncoderPort;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CREDENTIALS", schema = "auth")
public class Credentials implements Serializable {

    // =========================
    // Instance fields
    // =========================
    @Id
    private UserId userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String password;


    // =========================
    // Account state
    // =========================
    @Embedded
    private AccountState accountState  = AccountState.active();


    // =========================
    // Password history
    // =========================
    @OneToMany(
        mappedBy = "credentials",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private final List<PasswordHistory> passwordHistory = new ArrayList<>();


    // =========================
    // Login information
    // =========================
    @Column(name = "failed_login_attempts", nullable = false)
    private int failedLoginAttempts = 0;

    @Column(name = "last_failed_login")
    private Instant lastFailedLogin;


    // =========================
    // Constructor
    // =========================
    protected Credentials() { }

    public Credentials(User user, String password) {
        this.user = user;
        this.userId = user.getId();
        this.password = password;
        this.accountState = AccountState.active();
    }


    // =========================
    // Getters
    // =========================
    public UserId getUserId() { return userId; }
    public User getUser() { return user; }
    public String getPassword() { return password; }
    public AccountState getAccountState() { return accountState; }
    public List<PasswordHistory> getPasswordHistory() { return new ArrayList<>(passwordHistory); }
    public Instant getLastFailedLogin() { return lastFailedLogin; }
    public int getFailedLoginAttempts() { return failedLoginAttempts; }



    // =========================
    // Domain state queries
    // =========================
    public boolean isLocked() { return accountState.isLocked(); }
    public boolean isActive() { return accountState.isEnabled(); }
    public boolean hasValidCredentials() { return accountState.credentialsValid(); }


    // =========================
    // Password management
    // =========================
    public void changePassword(
        PasswordEncoderPort encoder,
        PasswordRuleEngine ruleEngine,
        String rawPassword
    ) {
        Objects.requireNonNull(encoder, "encoder must not be null");
        Objects.requireNonNull(rawPassword, "rawPassword must not be null");

        ensurePasswordNotReused(encoder, rawPassword);
        applyNewPassword(encoder, ruleEngine, rawPassword);
        registerEvent(new PasswordChangedEvent(user.getId(), user.getUsername(), "Password changed", "PASSWORD_CHANGED", Instant.now()));
    }


    public void encodePassword(
        PasswordEncoderPort encoder,
        PasswordRuleEngine ruleEngine,
        String rawPassword
    ) {
        applyNewPassword(encoder, ruleEngine, rawPassword);
    }


    // =========================
    // Login management
    // =========================
    public void registerFailedLogin() {
        failedLoginAttempts++;
        lastFailedLogin = Instant.now();
        if (failedLoginAttempts >= 5)
            accountState = accountState.lock();
    }

    public void unlock() {
        this.accountState = accountState.unlock();
        this.failedLoginAttempts = 0; // contextual reset
    }

    public void resetLoginAttempts() {
        failedLoginAttempts = 0;
    }


    // =========================
    // Domain events
    // =========================
    protected void registerEvent(DomainEvent event) {
        this.user.registerEvent(event);
    }


    // =========================
    // private methods
    // =========================
    private void applyNewPassword(PasswordEncoderPort encoder, PasswordRuleEngine ruleEngine, String rawPassword) {
        Objects.requireNonNull(rawPassword, "Raw password cannot be null");
        PasswordValidationResult result = ruleEngine.validate(rawPassword);
        if (!result.isValid()) throw new IllegalArgumentException(result.getMessage());
        String encoded = encoder.encode(rawPassword);
        passwordHistory.add(new PasswordHistory(this, encoded));
        this.password = encoded;
        this.accountState = this.accountState.withExpiration(LocalDate.now(ZoneId.systemDefault()).plusDays(90));
        enforceHistoryLimit(5);
    }

    private void ensurePasswordNotReused(PasswordEncoderPort encoder, String rawPassword) {
        Objects.requireNonNull(rawPassword, "Raw password cannot be null");
        for (PasswordHistory history : passwordHistory) {
            String encodedPassword = history.getEncodedPassword();
            Objects.requireNonNull(encodedPassword, "Encoded password cannot be null");
            if (encoder.matches(rawPassword, encodedPassword))
                throw new IllegalStateException("Password already used");
        }
    }


    @SuppressWarnings("null")
    private void enforceHistoryLimit(int limit) {
        if (passwordHistory.size() > limit) {
            //NOSONAR: lambda used instead of method reference to satisfy null-safety type checker
            passwordHistory.sort(Comparator.comparing(
                PasswordHistory::getChangedAt,
                Comparator.nullsLast(Comparator.naturalOrder())
            ));
            int excess = passwordHistory.size() - limit;
            passwordHistory.subList(0, excess).clear();
        }
    }


}
