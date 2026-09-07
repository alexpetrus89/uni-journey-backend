package com.alex.unijourneybackend.modules.professor.domain.model;

import java.time.LocalDate;
import java.util.Objects;

import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.valueobject.Address;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROFESSORS", schema = "people")
@Access(AccessType.FIELD)
@PrimaryKeyJoinColumn(name = "id")
public class Professor extends User {

    // instance variables
    @Embedded
    private ProfessorCode professorCode;

    protected Professor() { }

    public Professor(
        String username,
        String firstName,
        String lastName,
        LocalDate dob,
        FiscalCode fiscalCode,
        RoleType role,
        Address address,
        ProfessorCode professorCode
    ) {
        super(username, firstName, lastName, dob, fiscalCode, role, address);
        this.professorCode = Objects.requireNonNull(professorCode);
    }

    public ProfessorCode getProfessorCode() {
        return professorCode;
    }


}


