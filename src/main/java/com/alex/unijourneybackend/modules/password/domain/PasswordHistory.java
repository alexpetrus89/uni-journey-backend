package com.alex.unijourneybackend.modules.password.domain;

import java.time.Instant;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.model.BaseEntity;
import com.alex.unijourneybackend.modules.user.domain.valueobject.Credentials;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PASSWORD_HISTORY", schema = "auth")
public class PasswordHistory extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false)
    private Instant changedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credentials", nullable = false)
    private Credentials credentials;   // ← era User user

    protected PasswordHistory() { }

    public PasswordHistory(Credentials credentials, String encodedPassword) {
        this.credentials = credentials;
        this.encodedPassword = encodedPassword;
        this.changedAt = Instant.now();
    }

    @Override
    public Long getId() { return id; }
    public Credentials getCredentials() { return credentials; }
    public String getEncodedPassword() { return encodedPassword; }
    @NonNull
    public Instant getChangedAt() {
        return Objects.requireNonNull(changedAt, "changedAt must not be null");
    }


}
