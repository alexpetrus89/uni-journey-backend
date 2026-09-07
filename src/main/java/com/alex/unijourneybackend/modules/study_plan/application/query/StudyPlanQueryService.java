package com.alex.unijourneybackend.modules.study_plan.application.query;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.course.port.CourseRepositoryPort;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.service.StudentResolver;
import com.alex.unijourneybackend.modules.study_plan.domain.model.StudyPlan;
import com.alex.unijourneybackend.modules.study_plan.port.StudyPlanRepositoryPort;
import com.alex.unijourneybackend.modules.study_plan.web.dto.StudyPlanDto;
import com.alex.unijourneybackend.modules.study_plan.web.mapper.StudyPlanMapper;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Service
public class StudyPlanQueryService {

    private static final String STUDY_PLAN_NOT_FOUND = "Study plan not found for student: ";
    private static final String ID_NOT_NULL = "id must not be null";

    private final StudyPlanRepositoryPort studyPlanPort;
    private final CourseRepositoryPort coursePort;
    private final StudentResolver resolver;

    public StudyPlanQueryService(
        StudyPlanRepositoryPort studyPlanPort,
        CourseRepositoryPort coursePort,
        StudentResolver resolver
    ) {
        this.studyPlanPort = studyPlanPort;
        this.coursePort = coursePort;
        this.resolver = resolver;
    }

    @Transactional(readOnly = true)
    public StudyPlanDto getStudyPlan(GetStudyPlanQuery query) {
    StudyPlan studyPlan = findStudyPlan(query.username());
    return StudyPlanMapper.toDto(studyPlan);
}

    @SuppressWarnings("null")
    @Transactional(readOnly = true)
    public List<StudyPlanDto.CourseDto> getAvailableCourses(GetAvailableCoursesQuery query) {
        StudyPlan studyPlan = findStudyPlan(query.username());

        return coursePort
            .findAll(PageQuery.unpaged())
            .content()
            .stream()
            .filter(c -> c.getDegreeCourse() != null &&
                            c.getDegreeCourse().getName().equals(query.degreeCourse()))
            .filter(c -> !studyPlan.containsCourse(c))
            .filter(c -> !c.isMandatoryCourse())
            .sorted(Comparator.comparing(Course::getYearOfStudy).thenComparing(Course::getName))
            .map(StudyPlanMapper::toCourseDto)
            .toList();
    }

    private StudyPlan findStudyPlan(String username) {
        Student student = resolver.resolveByUsername(username);
        UserId id = Objects.requireNonNull(student.getId(), ID_NOT_NULL);
        return studyPlanPort
            .findByStudentId(id)
            .orElseThrow(() -> new IllegalStateException(STUDY_PLAN_NOT_FOUND + username));
    }


}
