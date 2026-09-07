package com.alex.unijourneybackend.modules.user.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDto(

    @NotBlank(message = "Street is mandatory")
    String street,

    @NotBlank(message = "City is mandatory")
    String city,

    @NotBlank(message = "Country is mandatory")
    String country,

    @Pattern(regexp = "\\d{5}", message = "ZIP code must be a 5-digit number")
    @NotBlank(message = "ZIP code is mandatory")
    String zip
) {}
