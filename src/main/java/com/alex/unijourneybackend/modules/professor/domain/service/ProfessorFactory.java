package com.alex.unijourneybackend.modules.professor.domain.service;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;
import com.alex.unijourneybackend.modules.professor.application.command.register.RegisterProfessorCommand;
import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.user.domain.factory.AbstractUserRegistrationFactory;
import com.alex.unijourneybackend.modules.user.domain.port.out.PasswordEncoderPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

@Component
public class ProfessorFactory
    extends AbstractUserRegistrationFactory<Professor> {

    private final PasswordEncoderPort encoder;
    private final PasswordRuleEngine ruleEngine;

    public ProfessorFactory(PasswordEncoderPort encoder, PasswordRuleEngine ruleEngine) {
        this.encoder = encoder;
        this.ruleEngine = ruleEngine;
    }

    public Professor create(RegisterProfessorCommand command, ProfessorCode code) {
        Professor professor = new Professor(
            command.username(),
            command.firstName(),
            command.lastName(),
            command.dob(),
            buildFiscalCode(command.fiscalCode()),
            RoleType.PROFESSOR,
            buildAddress(command.street(), command.city(), command.country(), command.zip()),
            code
        );

        return initializeCredentials(professor, command.phone(), encoder, ruleEngine, command.password());
    }

}