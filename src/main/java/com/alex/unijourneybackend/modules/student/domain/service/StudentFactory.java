package com.alex.unijourneybackend.modules.student.domain.service;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.degree_course.domain.model.DegreeCourse;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;
import com.alex.unijourneybackend.modules.student.application.command.register.RegisterStudentCommand;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;
import com.alex.unijourneybackend.modules.study_plan.domain.model.StudyPlan;
import com.alex.unijourneybackend.modules.user.domain.factory.AbstractUserRegistrationFactory;
import com.alex.unijourneybackend.modules.user.domain.port.out.PasswordEncoderPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

@Component
public class StudentFactory
    extends AbstractUserRegistrationFactory<Student> {

    private final PasswordEncoderPort encoder;
    private final PasswordRuleEngine ruleEngine;

    public StudentFactory(PasswordEncoderPort encoder, PasswordRuleEngine ruleEngine) {
        this.encoder = encoder;
        this.ruleEngine = ruleEngine;
    }

    public Student create(RegisterStudentCommand command, DegreeCourse degreeCourse, String ordering, Register register) {
        Student student = new Student(
            command.username(),
            command.firstName(),
            command.lastName(),
            command.dob(),
            buildFiscalCode(command.fiscalCode()),
            buildAddress(command.street(), command.city(), command.country(), command.zip()),
            RoleType.STUDENT,
            register,
            degreeCourse
        );

        student = initializeCredentials(student, command.phone(), encoder, ruleEngine, command.password());

        StudyPlan studyPlan = new StudyPlan(
            student,
            ordering,
            new HashSet<>(degreeCourse.getCourses())
        );

        student.setStudyPlan(studyPlan);

        return student;
    }
}
