package com.alex.unijourneybackend.modules.user.domain.valueobject;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 5160319758849739473L;

    @Column(name = "street", nullable = false, length = 100, columnDefinition = "TEXT")
    private String street;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    protected Address() { } // solo per JPA

    public Address(String street, String city, String country, String zipCode) {
        this.street  = validateNotBlank(street,  "street");
        this.city    = validateNotBlank(city,     "city");
        this.country = validateNotBlank(country,  "country");
        this.zipCode = validateZipCode(zipCode);
    }


    // =========================
    // Getters
    // =========================

    public String getStreet()  { return street; }
    public String getCity()    { return city; }
    public String getCountry() { return country; }
    public String getZipCode() { return zipCode; }


    // =========================
    // Validation
    // =========================

    private static String validateNotBlank(String value, String fieldName) {
        Assert.hasText(value, fieldName + " must not be blank");
        return value.trim();
    }

    private static String validateZipCode(String value) {
        Assert.hasText(value, "zipCode must not be blank");
        Assert.isTrue(
            value.matches("\\d{5}"),
            "ZIP code must be a 5-digit number"
        );
        return value;
    }


    // =========================
    // Equality (by value, non by identity)
    // =========================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address other)) return false;
        return Objects.equals(street,  other.street)
            && Objects.equals(city,    other.city)
            && Objects.equals(country, other.country)
            && Objects.equals(zipCode, other.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, country, zipCode);
    }

    @Override
    public String toString() {
        return "%s, %s, %s %s".formatted(street, city, zipCode, country);
    }


}
