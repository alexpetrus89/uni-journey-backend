package com.alex.unijourneybackend.modules.student.domain.service;


import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;

public interface RegisterGenerator {

    /**
     * Generate a new register.
     * @return the generated register
     */
    Register generate();


}
