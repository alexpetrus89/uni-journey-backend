package com.alex.unijourneybackend.modules.student.domain.valueobject;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Register(
    @Column(name = "register", nullable = false, unique = true, length = 6)
    String value
) implements Serializable {

    public static final Pattern FORMAT = Pattern.compile("^\\d{6}$");

    public Register {
        Assert.notNull(value, "register must not be null");
        Assert.isTrue(
            FORMAT.matcher(value).matches(),
            "register must be a string of exactly 6 digits"
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
