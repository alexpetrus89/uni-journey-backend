package com.alex.unijourneybackend.modules.professor.web.dto;



import com.alex.unijourneybackend.common.validation.annotation.ValidFiscalCode;
import com.alex.unijourneybackend.common.validation.annotation.ValidProfessorCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public record ProfessorDto (

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

    @NotBlank(message = "Fiscal code is mandatory")
    @ValidFiscalCode
    String fiscalCode,

    @NotBlank(message = "Professor code is mandatory")
    @ValidProfessorCode
    String professorCode

) {

    public String getFullName() {
        return firstName + " " + lastName;
    }


}
