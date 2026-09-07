package com.alex.unijourneybackend.modules.user.domain.factory;


import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.port.out.PasswordEncoderPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.Address;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.PhoneNumber;

public abstract class AbstractUserRegistrationFactory<U extends User> {

    protected Address buildAddress(String street, String city, String country, String zip) {
        return new Address(street, city, country, zip);
    }

    protected FiscalCode buildFiscalCode(String fiscalCode) {
        return new FiscalCode(fiscalCode);
    }

    protected PhoneNumber buildPhone(String phone) {
        return new PhoneNumber(phone);
    }

    protected U initializeCredentials(
        U user,
        String phone,
        PasswordEncoderPort encoder,
        PasswordRuleEngine ruleEngine,
        String password
    ) {
        user.setPhone(buildPhone(phone));
        user.encodePassword(encoder, ruleEngine, password);
        return user;
    }


}
