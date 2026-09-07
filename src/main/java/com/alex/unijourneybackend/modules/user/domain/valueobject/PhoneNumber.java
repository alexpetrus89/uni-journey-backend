package com.alex.unijourneybackend.modules.user.domain.valueobject;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PhoneNumber implements Serializable {

    private static final Pattern FORMAT = Pattern.compile("^\\+?\\d{7,15}$");

    @Column(name = "phone", nullable = false, length = 15)
    private String value;

    protected PhoneNumber() {} // ← costruttore no-arg per Hibernate

    public PhoneNumber(String value) {
        Assert.notNull(value, "phone must not be null");
        Assert.isTrue(FORMAT.matcher(value).matches(), "invalid phone format");
        this.value = value;
    }

    public String getValue() { return value; }
}
