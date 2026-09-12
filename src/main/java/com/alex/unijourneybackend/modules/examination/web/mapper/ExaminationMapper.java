package com.alex.unijourneybackend.modules.examination.web.mapper;


import com.alex.unijourneybackend.modules.examination.domain.model.Examination;
import com.alex.unijourneybackend.modules.examination.web.dto.response.ExaminationResponse;

public final class ExaminationMapper {

    private ExaminationMapper() {}

    /**
     * Il nome corso viene dallo snapshot dell'entità (storicamente accurato
     * anche se il corso viene rinominato in seguito); CFU e nome del corso
     * di laurea non sono snapshottati e vengono letti dall'associazione
     * live — va quindi chiamato dentro una transazione se il corso è LAZY.
     */
    public static ExaminationResponse toResponse(Examination examination) {
        if (examination == null) return null;

        return new ExaminationResponse(
            examination.getCourseNameSnapshot(),
            examination.getCourse() != null ? examination.getCourse().getCfu() : null,
            examination.getGrade(),
            examination.isWithHonors(),
            examination.getDate(),
            examination.getCourse() != null && examination.getCourse().getDegreeCourse() != null
                ? examination.getCourse().getDegreeCourse().getName()
                : null
        );
    }
}
