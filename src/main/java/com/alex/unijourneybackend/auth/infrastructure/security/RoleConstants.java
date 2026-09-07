package com.alex.unijourneybackend.auth.infrastructure.security;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public final class RoleConstants {

    private RoleConstants() {}

    public static final List<String> ROLE_PRIORITY = List.of(
        "ROLE_ADMIN",
        "ROLE_PROFESSOR",
        "ROLE_STUDENT"
    );

    @SuppressWarnings("null")
    public static String extractHighestRole(Collection<? extends GrantedAuthority> authorities) {
        return authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .filter(ROLE_PRIORITY::contains)
            .min(Comparator.comparingInt(ROLE_PRIORITY::indexOf))
            .orElse("ROLE_GUEST");
    }


}