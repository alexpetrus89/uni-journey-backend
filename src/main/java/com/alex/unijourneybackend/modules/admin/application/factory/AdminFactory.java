package com.alex.unijourneybackend.modules.admin.application.factory;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.admin.application.command.register.RegisterAdminCommand;
import com.alex.unijourneybackend.modules.admin.domain.model.Admin;
import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;
import com.alex.unijourneybackend.modules.user.domain.factory.AbstractUserRegistrationFactory;
import com.alex.unijourneybackend.modules.user.domain.port.out.PasswordEncoderPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

@Component
public class AdminFactory
    extends AbstractUserRegistrationFactory<Admin> {

    private final PasswordEncoderPort encoder;
    private final PasswordRuleEngine ruleEngine;

    public AdminFactory(PasswordEncoderPort encoder, PasswordRuleEngine ruleEngine) {
        this.encoder = encoder;
        this.ruleEngine = ruleEngine;
    }

    public Admin create(RegisterAdminCommand command, AdminCode adminCode) {
        Admin admin = new Admin(
            command.username(),
            command.firstName(),
            command.lastName(),
            command.dob(),
            buildFiscalCode(command.fiscalCode()),
            RoleType.ADMIN,
            buildAddress(command.street(), command.city(), command.country(), command.zip()),
            adminCode
        );

        return initializeCredentials(admin, command.phone(), encoder, ruleEngine, command.password());
    }


}
