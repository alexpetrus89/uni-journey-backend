package com.alex.unijourneybackend.modules.admin.web.dto;

import java.time.LocalDate;

public record AdminResponse(
    String id,
    String username,
    String role,
    String firstName,
    String lastName,
    LocalDate dob,
    Integer age,
    String fiscalCode,
    String phone,
    String adminCode,
    String street,
    String city,
    String country,
    String zip
) {}

