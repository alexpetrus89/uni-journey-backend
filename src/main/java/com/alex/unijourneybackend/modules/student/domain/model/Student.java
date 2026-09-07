package com.alex.unijourneybackend.modules.student.domain.model;


import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.alex.unijourneybackend.modules.degree_course.domain.model.DegreeCourse;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;
import com.alex.unijourneybackend.modules.study_plan.domain.model.StudyPlan;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.valueobject.Address;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "STUDENTS", schema = "people")
@Access(AccessType.FIELD)
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @Embedded
    private Register register;

    // owning side
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(
        name = "degree_course_id",
        foreignKey = @ForeignKey(name = "fk_student_degreeCourse")
    )
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private DegreeCourse degreeCourse;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private StudyPlan studyPlan;


    // =========================
    // Constructors
    // =========================
    protected Student() { }

    public Student(
        String username,
        String firstName,
        String lastName,
        LocalDate dob,
        FiscalCode fiscalCode,
        Address address,
        RoleType role,
        Register register,
        DegreeCourse degreeCourse
    ) {
        super(username, firstName, lastName, dob, fiscalCode, role, address);
        this.register = Objects.requireNonNull(register);
        this.degreeCourse = degreeCourse;
    }


    // =========================
    // Getters
    // =========================
    public Register getRegister() { return register; }
    public DegreeCourse getDegreeCourse() { return degreeCourse; }
    public StudyPlan getStudyPlan() { return studyPlan; }


    // =========================
    // Setters (domain command)
    // =========================
    public void setRegister(Register register) { this.register = register; }
    public void setDegreeCourse(DegreeCourse degreeCourse) { this.degreeCourse = degreeCourse; }
    public void setStudyPlan(StudyPlan studyPlan) { this.studyPlan = studyPlan; }


}