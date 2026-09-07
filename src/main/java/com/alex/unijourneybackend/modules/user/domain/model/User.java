package com.alex.unijourneybackend.modules.user.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.alex.unijourneybackend.common.domain.exception.InvalidDomainStateException;
import com.alex.unijourneybackend.common.domain.model.SoftDeletableEntity;
import com.alex.unijourneybackend.common.events.domain.model.DomainEvent;
import com.alex.unijourneybackend.common.events.domain.model.HasDomainEvents;
import com.alex.unijourneybackend.modules.notification.domain.event.PasswordChangedEvent;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;
import com.alex.unijourneybackend.modules.user.domain.port.out.PasswordEncoderPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.Address;
import com.alex.unijourneybackend.modules.user.domain.valueobject.Credentials;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.Permission;
import com.alex.unijourneybackend.modules.user.domain.valueobject.PhoneNumber;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "USERS",
    schema = "auth",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_user_username", columnNames = "username")
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE auth.users SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public abstract class User extends SoftDeletableEntity<UserId>
    implements HasDomainEvents, Serializable {

    // =========================
    // Primary key
    // =========================
    @EmbeddedId
    private UserId id;


    // =========================
    // Credentials
    // =========================
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;


    // =========================
    // Personal information
    // =========================
    @Column(name = "first_name", nullable = false, unique = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, unique = false, length = 50)
    private String lastName;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Embedded
    private FiscalCode fiscalCode;

    @Embedded
    private PhoneNumber phoneNumber;


    // =========================
    // Role
    // =========================
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleType role;


    // =========================
    // Permissions
    // =========================
    public Set<Permission> getPermissions() { return role.permissions(); }


    // =========================
    // Address
    // =========================
    @Embedded
    private Address address;


    // =========================
    // Credentials
    // =========================
    @OneToOne(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,
        optional = false
    )
    private Credentials credentials;


    // =========================
    // Domain events
    // =========================
    @Transient
    private final List<DomainEvent> domainEvents = new ArrayList<>();


    // =========================
    // Constructors
    // =========================
    protected User() { } // for JPA

    protected User(
        String username,
        String firstName,
        String lastName,
        LocalDate dob,
        FiscalCode fiscalCode,
        RoleType role,
        Address address
    ) {
        this.id = UserId.newId();
        this.username = Objects.requireNonNull(username, "username must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.dob = Objects.requireNonNull(dob, "dob must not be null");
        this.fiscalCode = Objects.requireNonNull(fiscalCode, "fiscalCode must not be null");
        this.role = Objects.requireNonNull(role, "role must not be null");
        this.address = Objects.requireNonNull(address, "address must not be null");

        validateAdult(); // validazione input utente
    }


    public void changePassword(PasswordEncoderPort encoder, PasswordRuleEngine ruleEngine, String rawPassword) {
        credentials.changePassword(encoder, ruleEngine, rawPassword);
        registerEvent(new PasswordChangedEvent(id, username, "Password changed", "PASSWORD_CHANGED", Instant.now()));
    }

    public void encodePassword(PasswordEncoderPort encoder, PasswordRuleEngine ruleEngine, String rawPassword) {
        credentials.encodePassword(encoder, ruleEngine, rawPassword);
    }


    // =========================
    // Login management
    // =========================
    public void registerFailedLogin() { credentials.registerFailedLogin(); }
    public void unlock() { credentials.unlock(); }
    public void resetLoginAttempts() { credentials.resetLoginAttempts(); }


    // =========================
    // Domain state queries
    // =========================
    public boolean isLocked() { return credentials.isLocked(); }
    public boolean isActive() { return credentials.isActive(); }
    public boolean hasValidCredentials() { return credentials.hasValidCredentials(); }
    public Instant getLastFailedLogin() { return credentials.getLastFailedLogin(); }
    public int getFailedLoginAttempts() { return credentials.getFailedLoginAttempts(); }


    // =========================
    // Exposed for authentication infrastructure
    // =========================
    /**
     * Exposed exclusively for authentication infrastructure
     * (SecurityUserAdapter).
     * Do not use elsewhere.
     */
    public String getEncodedPasswordForAuth() { return credentials.getPassword(); }


    // =========================
    // Getters
    // =========================
    @Override
    public UserId getId() { return id; }
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDob() { return dob; }
    public FiscalCode getFiscalCode() { return fiscalCode; }
    public String getPhone() { return phoneNumber.getValue(); }
    public RoleType getRole() { return role; }
    public Address getAddress() { return address; }

    @Transient
    public int getAge() { return Period.between(dob, LocalDate.now(ZoneId.systemDefault())).getYears(); }


    // =========================
    // Deletable Management
    // =========================
    @Override
    public void markDeleted() {
        if(isDeleted()) return;
        super.markDeleted();
    }

    @Override
    public void markUndeleted() {
        if(!isDeleted()) return;
        super.markUndeleted();
    }


    // =========================
    // Setters
    // =========================
    public void setPhone(PhoneNumber phone) {
        this.phoneNumber = Objects.requireNonNull(phone, "phone must not be null");
    }

    public void setUsername(String username) {
        this.username = Objects.requireNonNull(username, "username must not be null");
    }

    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    }

    public void setDob(LocalDate dob) {
        this.dob = Objects.requireNonNull(dob, "dob must not be null");
        validateAdult();
    }

    public void setFiscalCode(FiscalCode fiscalCode) {
        this.fiscalCode = Objects.requireNonNull(fiscalCode, "fiscalCode must not be null");
    }

    public void setRole(RoleType role) {
        this.role = Objects.requireNonNull(role, "role must not be null");
    }

    public void setAddress(Address address) {
        this.address = Objects.requireNonNull(address, "address must not be null");
    }


    // =========================
    // Domain events
    // =========================
    public void registerEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    @Override
    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @Override
    public void clearEvents() {
        domainEvents.clear();
    }


    // =========================
    // Business rules
    // =========================
    @PostLoad
    @SuppressWarnings("unused")
    private void validateState() {
        if (dob != null && !isAdult()) {
            // oppure lancia una domain exception custom
            // che il tuo ExceptionHandler gestisce
            throw new InvalidDomainStateException("User [%s] has invalid age in persistent store".formatted(username));
        }
    }

    private void validateAdult() {
    if (!isAdult())
        throw new IllegalArgumentException("User [%s] must be adult".formatted(username));
    }

    private boolean isAdult() {
        return Period.between(dob, LocalDate.now(ZoneId.systemDefault())).getYears() >= 18;
    }


}
