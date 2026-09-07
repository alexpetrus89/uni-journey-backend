package com.alex.unijourneybackend.common.validation.validator;

import java.util.regex.Pattern;

import com.alex.unijourneybackend.common.validation.annotation.ValidFullName;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FullNameValidator implements ConstraintValidator<ValidFullName, String> {

    // Classe di caratteri piatta: lettere Unicode + apostrofi + trattino
    private static final Pattern WORD_PATTERN = Pattern.compile( "^[\\p{L}'`\\-]+$");

    private static final int MAX_LENGTH = 100;
    private static final int MAX_WORDS = 10;
    private static final int MIN_WORD_LENGTH = 2;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return false;
        if (value.length() > MAX_LENGTH) return false;

        String[] words = value.trim().split("\\s+");

        if (words.length > MAX_WORDS) return false;

        for (String word : words) {
            if (word.length() < MIN_WORD_LENGTH) return false;
            if (!WORD_PATTERN.matcher(word).matches()) return false;
        }

        return true;
    }


}

