package com.alex.unijourneybackend.infrastructure.security.password;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.modules.user.domain.port.out.PasswordEncoderPort;

@Service
public class PasswordEncoderAdapter implements PasswordEncoderPort {

    private final PasswordEncoder encoder;

    public PasswordEncoderAdapter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    @SuppressWarnings("null")
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    @SuppressWarnings("null")
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }


}
