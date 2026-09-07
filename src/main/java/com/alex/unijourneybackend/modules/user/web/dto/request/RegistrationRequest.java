package com.alex.unijourneybackend.modules.user.web.dto.request;

import java.time.LocalDate;

import com.alex.unijourneybackend.common.validation.PasswordCarrier;
import com.alex.unijourneybackend.common.validation.annotation.UniqueUsername;
import com.alex.unijourneybackend.common.validation.annotation.ValidBirthDate;
import com.alex.unijourneybackend.common.validation.annotation.ValidFiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    @UniqueUsername
    String username,

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    String password,

    @NotBlank(message = "Password confirmation is mandatory")
    @Size(min = 8, max = 30, message = "Password confirmation must be between 8 and 30 characters")
    String confirm,

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

    @NotBlank(message = "Street is mandatory")
    String street,

    @NotBlank(message = "City is mandatory")
    String city,

    @NotBlank(message = "Country is mandatory")
    String country,

    @NotBlank(message = "Zip code is mandatory")
    String zip,

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number format")
    String phone,

    @NotNull(message = "Role is mandatory")
    RoleType role,

    String degreeCourse,

    String studyPlan,

    String ordering
) implements PasswordCarrier{

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConfirm() {
        return confirm;
    }
}
