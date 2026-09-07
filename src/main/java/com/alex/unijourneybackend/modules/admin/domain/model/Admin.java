package com.alex.unijourneybackend.modules.admin.domain.model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;

import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;
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
@Table(name = "ADMINS", schema = "people")
@Access(AccessType.FIELD)
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @Embedded
    private AdminCode adminCode;


    // =========================
    // Constructors
    // =========================
    protected Admin() { }

    public Admin(
        String username,
        String firstName,
        String lastName,
        LocalDate dob,
        FiscalCode fiscalCode,
        RoleType role,
        Address address,
        AdminCode adminCode
    ) {
        super(username, firstName, lastName, dob, fiscalCode, role, address);
        this.adminCode = Objects.requireNonNull(adminCode);
    }


    // =========================
    // Getters
    // =========================
    public AdminCode getAdminCode() {
        return adminCode;
    }


}
