package com.alex.unijourneybackend.modules.examination.web.dto.response;


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
