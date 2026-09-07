package com.alex.unijourneybackend.common.validation;

import com.alex.unijourneybackend.common.validation.annotation.ValidYearOfStudy;


@ValidYearOfStudy
public interface CourseValidatable {

    /**
     * @return the year of study associated with the degree course
     */
    Integer getYearOfStudy();

    /**
     * @return the name of the degree course
     */
    String getDegreeCourseName();


}

