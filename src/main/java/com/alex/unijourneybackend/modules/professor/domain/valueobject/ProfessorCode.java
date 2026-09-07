package com.alex.unijourneybackend.modules.professor.domain.valueobject;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record ProfessorCode(
    @Column(name = "professor_code", nullable = false, unique = true, length = 8)
    String value
) implements Serializable {

    public static final Pattern FORMAT = Pattern.compile("^[A-Z0-9]{8}$");

    public ProfessorCode {
        Assert.notNull(value, "professor code must not be null");
        Assert.isTrue(
            FORMAT.matcher(value).matches(),
            "professor code must be a string of exactly 8 uppercase letters or digits"
        );
    }

    public static boolean isValid(String value) {
        return value != null && FORMAT.matcher(value).matches();
    }

    @Override
    public String toString() {
        return value;
    }


}
