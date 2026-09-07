package com.alex.unijourneybackend.modules.admin.domain.valueobject;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record AdminCode(
    @Column(name = "admin_code", nullable = false, unique = true, length = 11)
    String value
) implements Serializable {

    public static final String PREFIX = "ADM";
    public static final Pattern FORMAT =
        Pattern.compile("^" + PREFIX + "[A-Z0-9]{8}$");

    public AdminCode {
        Assert.notNull(value, "admin code must not be null");
        Assert.isTrue(
            FORMAT.matcher(value).matches(),
            "admin code must match pattern ADMXXXXXXXX"
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
