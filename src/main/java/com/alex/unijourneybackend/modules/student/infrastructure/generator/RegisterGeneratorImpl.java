package com.alex.unijourneybackend.modules.student.infrastructure.generator;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.modules.student.domain.service.RegisterGenerator;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;

@Service
public class RegisterGeneratorImpl implements RegisterGenerator {

    private static final int REGISTER_LENGTH = 6;
    private static final int MAX_VALUE = 1_000_000;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    public Register generate() {
        int value = SECURE_RANDOM.nextInt(MAX_VALUE);
        return new Register(String.format("%0" + REGISTER_LENGTH + "d", value));
    }


}
