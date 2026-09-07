package com.alex.unijourneybackend.modules.user.web.dto;


import java.time.LocalDate;

import com.alex.unijourneybackend.common.validation.annotation.ValidBirthDate;
import com.alex.unijourneybackend.common.validation.annotation.ValidFiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDto (

    // instance variables
    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 30, message = "Username must be between 4 and 30 characters")
    String username,

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    String firstName,

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    String lastName,

    @NotNull(message = "Date of birth is mandatory")
    @ValidBirthDate
    LocalDate dob,

    @NotBlank(message = "Fiscal code is mandatory")
    @ValidFiscalCode
    String fiscalCode,

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number format")
    String phone,

    @NotNull(message = "Role is mandatory")
    RoleType role,

    @NotNull(message = "Address is mandatory")
    @Valid
    AddressDto address
) {}

