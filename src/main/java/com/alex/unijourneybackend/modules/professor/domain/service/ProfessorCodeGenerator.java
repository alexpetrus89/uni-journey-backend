package com.alex.unijourneybackend.modules.professor.domain.service;


import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;

public interface ProfessorCodeGenerator {

    /**
     * Generate a new professor code.
     * @return the generate professor code.
     */
    ProfessorCode generate();


}
