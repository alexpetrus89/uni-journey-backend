package com.alex.unijourneybackend.modules.password.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.alex.unijourneybackend.modules.user.domain.model.User;

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
@Table(name = "PASSWORD_RESET_TOKENS", schema = "auth")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    @Column(name = "used", nullable = false, unique = true)
    private boolean used = false;

    // constructors
    public PasswordResetToken() {}

    public PasswordResetToken(String token, User user, int minutes) {
        this.token = token;
        this.user = user;
        this.expiryDate = LocalDateTime.now(ZoneId.systemDefault()).plusMinutes(minutes);
    }

    // getters
    public Long getId() { return id; }
    public String getToken() { return token; }
    public User getUser() { return user; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public boolean isUsed() { return used; }
    public boolean isExpired() { return LocalDateTime.now(ZoneId.systemDefault()).isAfter(expiryDate) || used; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setToken(String token) { this.token = token; }
    public void setUser(User user) { this.user = user; }
    public void setExpiryDate(LocalDateTime date) { this.expiryDate = date; }
    public void setUsed(boolean used) { this.used = used; }

}
