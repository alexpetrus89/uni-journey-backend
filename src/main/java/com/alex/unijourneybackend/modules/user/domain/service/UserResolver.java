package com.alex.unijourneybackend.modules.user.domain.service;

import java.util.Objects;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.port.out.UserRepositoryPort;
import com.alex.unijourneybackend.modules.user.infrastructure.security.CustomUserDetailsService;
import com.alex.unijourneybackend.modules.user.infrastructure.security.SecurityUserAdapter;

@Component
public class UserResolver {

    private final UserRepositoryPort port;
    private final CustomUserDetailsService service;

    public UserResolver(UserRepositoryPort port, CustomUserDetailsService service) {
        this.port = port;
        this.service = service;
    }

    /**
     * Risolve uno User tramite lookup diretto sul DB (nessun SecurityContext coinvolto).
     * Usalo in event handler / job in background, dove non c'è una request autenticata.
     */
    public User resolveByUsername(String username) {
        Objects.requireNonNull(username, "Username cannot be null");
        return port
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    /**
     * Risolve uno User dal Principal name di una request autenticata.
     */
    public User resolveFromPrincipalName(String principalName) {
        Objects.requireNonNull(principalName, "Principal name cannot be null");
        var adapter = (SecurityUserAdapter) service.loadUserByUsername(principalName);
        return adapter.getUser();
    }
}
