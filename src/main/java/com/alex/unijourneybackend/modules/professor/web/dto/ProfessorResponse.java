package com.alex.unijourneybackend.modules.professor.web.dto;

import java.time.LocalDate;


public record ProfessorResponse(
    String id,
    String username,
    String firstName,
    String lastName,
    LocalDate dob,
    Integer age,
    String fiscalCode,
    String phone,
    String professorCode,
    String street,
    String city,
    String country,
    String zip
) {}

