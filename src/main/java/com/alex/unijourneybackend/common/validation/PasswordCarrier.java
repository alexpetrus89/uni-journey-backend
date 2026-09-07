package com.alex.unijourneybackend.common.validation;

import com.alex.unijourneybackend.common.validation.annotation.PasswordMatches;

@PasswordMatches
public interface PasswordCarrier {

    String getPassword();
    String getConfirm();

}
