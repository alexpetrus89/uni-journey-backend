package com.alex.unijourneybackend.auth.infrastructure.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    /**
     * Password encoder for web security. Uses bcrypt algorithm.
     * @return PasswordEncoder
     */
    @Bean
    @SuppressWarnings("unused")
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
