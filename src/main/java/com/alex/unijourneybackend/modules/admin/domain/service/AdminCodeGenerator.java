package com.alex.unijourneybackend.modules.admin.domain.service;


import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;


public interface AdminCodeGenerator {

    /**
     * Generates a new admin code.
     * @return the generated admin code
     */
    AdminCode generate();


}
