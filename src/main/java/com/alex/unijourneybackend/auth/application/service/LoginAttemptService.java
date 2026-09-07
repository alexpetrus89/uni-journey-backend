package com.alex.unijourneybackend.auth.application.service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.modules.user.domain.port.out.UserRepositoryPort;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 5;
    private static final int BLOCK_DURATION_MINUTES = 30;

    private final UserRepositoryPort port;

    public LoginAttemptService(UserRepositoryPort port) {
        this.port = port;
    }

    // cache in memoria: IP → numero tentativi + timestamp
    private final Cache<String, AttemptRecord> attemptCache = Caffeine.newBuilder()
        .expireAfterWrite(BLOCK_DURATION_MINUTES, TimeUnit.MINUTES)
        .maximumSize(10_000)
        .build();

    public void onLoginFailure(String ip, String username) {
        Objects.requireNonNull(username);
        // 1. incrementa tentativi per IP
        AttemptRecord attRecord = attemptCache.get(ip, k -> new AttemptRecord());
        attRecord.increment();
        attemptCache.put(ip, attRecord);

        // 2. se username esiste, blocca anche l'account
        port.findByUsername(username).ifPresent(user -> {
            user.registerFailedLogin();
            port.create(user);
        });
    }

    public boolean isIpBlocked(String ip) {
        AttemptRecord attRecord = attemptCache.getIfPresent(ip);
        return attRecord != null && attRecord.getCount() >= MAX_ATTEMPTS;
    }

    public void onLoginSuccess(String ip, String username) {
        Objects.requireNonNull(username);
        attemptCache.invalidate(ip); // reset IP
        port.findByUsername(username).ifPresent(user -> {
            user.resetLoginAttempts();
            port.create(user);
        });
    }

    record AttemptRecord(int count) {
        AttemptRecord() { this(0); }
        AttemptRecord increment() { return new AttemptRecord(count + 1); }
        int getCount() { return count; }
    }


}
