package com.alex.unijourneybackend.modules.admin.infrastructure.generator;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.modules.admin.domain.service.AdminCodeGenerator;
import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;

@Service
public class AdminCodeGeneratorIml implements AdminCodeGenerator {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SUFFIX_LENGTH = 8;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    public AdminCode generate() {
        StringBuilder suffix = new StringBuilder(SUFFIX_LENGTH);
        for (int i = 0; i < SUFFIX_LENGTH; i++)
            suffix.append(ALPHABET.charAt(SECURE_RANDOM.nextInt(ALPHABET.length())));
        return new AdminCode(AdminCode.PREFIX + suffix);
    }


}
