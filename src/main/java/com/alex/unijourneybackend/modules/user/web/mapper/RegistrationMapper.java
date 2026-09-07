package com.alex.unijourneybackend.modules.user.web.mapper;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.admin.application.command.register.RegisterAdminCommand;
import com.alex.unijourneybackend.modules.professor.application.command.register.RegisterProfessorCommand;
import com.alex.unijourneybackend.modules.student.application.command.register.RegisterStudentCommand;
import com.alex.unijourneybackend.modules.user.web.dto.request.RegistrationRequest;

@Component
public class RegistrationMapper {

    public RegisterAdminCommand toAdminCommand(RegistrationRequest r) {
        return new RegisterAdminCommand(
            r.username(),
            r.password(),
            r.confirm(),
            r.firstName(),
            r.lastName(),
            r.dob(),
            r.fiscalCode(),
            r.street(),
            r.city(),
            r.country(),
            r.zip(),
            r.phone(),
            r.role()
        );
    }


    public RegisterProfessorCommand toProfessorCommand(RegistrationRequest r) {
        return new RegisterProfessorCommand(
            r.username(),
            r.password(),
            r.confirm(),
            r.firstName(),
            r.lastName(),
            r.dob(),
            r.fiscalCode(),
            r.street(),
            r.city(),
            r.country(),
            r.zip(),
            r.phone(),
            r.role()
        );
    }


    public RegisterStudentCommand toStudentCommand(RegistrationRequest r) {
        return new RegisterStudentCommand(
            r.username(),
            r.password(),
            r.confirm(),
            r.firstName(),
            r.lastName(),
            r.dob(),
            r.fiscalCode(),
            r.street(),
            r.city(),
            r.country(),
            r.zip(),
            r.phone(),
            r.role(),
            r.degreeCourse(),
            r.studyPlan(),
            r.ordering()
        );
    }

}
