package com.alex.unijourneybackend.modules.admin.application.command.register;

import java.time.LocalDate;

import com.alex.unijourneybackend.common.validation.PasswordCarrier;
import com.alex.unijourneybackend.common.validation.annotation.UniqueFiscalCode;
import com.alex.unijourneybackend.common.validation.annotation.UniqueUsername;
import com.alex.unijourneybackend.common.validation.annotation.ValidBirthDate;
import com.alex.unijourneybackend.common.validation.annotation.ValidCountry;
import com.alex.unijourneybackend.common.validation.annotation.ValidFiscalCode;
import com.alex.unijourneybackend.common.validation.annotation.ValidPassword;
import com.alex.unijourneybackend.modules.user.application.bus.Command;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterAdminCommand(

    @NotBlank(message = "username is required")
    @Size(min = 4, max = 30, message = "username must be between 4 and 30 characters")
    @UniqueUsername
    String username,

    @NotBlank(message = "Password is required")
    @ValidPassword
    String password,

    @NotBlank(message = "Password confirmation is required")
    String confirm,

    @NotBlank(message = "First name is required")
    String firstName,

    @NotBlank(message = "Last name is required")
    String lastName,

    @NotNull(message = "Date of birth is required")
    @ValidBirthDate
    LocalDate dob,

    @NotBlank(message = "Fiscal code is required")
    @ValidFiscalCode
    @UniqueFiscalCode
    String fiscalCode,

    @NotBlank(message = "Street is required")
    String street,

    @NotBlank(message = "City is required")
    String city,

    @NotBlank(message = "Country is required")
    @ValidCountry
    String country,

    @NotBlank(message = "ZIP code is required")
    @Pattern(regexp = "\\d{5}", message = "ZIP code must be 5 digits")
    String zip,

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\+?\\d{9,15}", message = "Phone number is invalid")
    String phone,

    @NotNull(message = "Role is required")
    RoleType role
) implements Command<RegistrationResponse>, PasswordCarrier {

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConfirm() {
        return confirm;
    }
}

