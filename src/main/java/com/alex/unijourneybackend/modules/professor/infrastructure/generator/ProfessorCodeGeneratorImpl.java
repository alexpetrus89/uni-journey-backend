package com.alex.unijourneybackend.modules.professor.infrastructure.generator;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.modules.professor.domain.service.ProfessorCodeGenerator;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;

@Service
public class ProfessorCodeGeneratorImpl implements ProfessorCodeGenerator {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    public ProfessorCode generate() {
        StringBuilder builder = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++)
            builder.append(ALPHABET.charAt(SECURE_RANDOM.nextInt(ALPHABET.length())));
        return new ProfessorCode(builder.toString());
    }


}
