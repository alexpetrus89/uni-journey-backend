package com.alex.unijourneybackend.modules.examination_appeal.web.dto;


import java.time.LocalDate;

/**
 * Voce del libretto dello studente. Sola lettura — mai bound da una request.
 */
public record ExaminationResponse(
    String courseName,
    Integer courseCfu,
    Integer grade,
    boolean withHonors,
    LocalDate date,
    String degreeCourseName
) {}
