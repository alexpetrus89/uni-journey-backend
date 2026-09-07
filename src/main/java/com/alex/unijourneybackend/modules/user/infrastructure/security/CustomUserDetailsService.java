package com.alex.unijourneybackend.modules.user.infrastructure.security;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.port.out.UserRepositoryPort;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort port;

    public CustomUserDetailsService(UserRepositoryPort port) {
        this.port = port;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Objects.requireNonNull(username, "Username cannot be null");
        User user = port
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new SecurityUserAdapter(user);
    }


}
