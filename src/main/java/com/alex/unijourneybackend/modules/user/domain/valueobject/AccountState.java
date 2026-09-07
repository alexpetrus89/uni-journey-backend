package com.alex.unijourneybackend.modules.user.domain.valueobject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountState implements Serializable {

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private boolean locked;

    @Column
    private LocalDate credentialsExpirationDate;

    protected AccountState() { }

    private AccountState(boolean enabled, boolean locked, LocalDate expirationDate) {
        this.enabled = enabled;
        this.locked = locked;
        this.credentialsExpirationDate = expirationDate;
    }

    public static AccountState active() {
        return new AccountState(true, false, null);
    }

    public AccountState lock() {
        return new AccountState(enabled, true, credentialsExpirationDate);
    }

    public AccountState unlock() {
        return new AccountState(enabled, false, credentialsExpirationDate);
    }

    public AccountState disable() {
        return new AccountState(false, locked, credentialsExpirationDate);
    }

    public AccountState withExpiration(LocalDate date) {
        return new AccountState(enabled, locked, date);
    }

    public boolean isEnabled() { return enabled; }
    public boolean isLocked() { return locked; }
    public boolean credentialsValid() {
        return credentialsExpirationDate == null || LocalDate.now(ZoneId.systemDefault()).isBefore(credentialsExpirationDate);
    }


}