package com.alex.unijourneybackend.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Component
public class StringToUserIdConverter implements Converter<String, UserId> {

    @Override
    public UserId convert(@NonNull String source) {
        if (source.isBlank())
            throw new IllegalArgumentException("UserId cannot be null or blank");

        try {
            return new UserId(source);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid UserId format: '%s'".formatted(source),ex);
        }
    }


}
