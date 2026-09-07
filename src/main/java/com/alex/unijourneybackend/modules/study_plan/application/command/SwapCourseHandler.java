package com.alex.unijourneybackend.modules.study_plan.application.command;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.course.port.CourseRepositoryPort;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.service.StudentResolver;
import com.alex.unijourneybackend.modules.study_plan.application.support.AbstractUpdatedApplicationService;
import com.alex.unijourneybackend.modules.study_plan.domain.model.StudyPlan;
import com.alex.unijourneybackend.modules.study_plan.port.StudyPlanRepositoryPort;
import com.alex.unijourneybackend.modules.study_plan.web.dto.response.SwapCourseResponse;
import com.alex.unijourneybackend.modules.study_plan.web.mapper.StudyPlanMapper;
import com.alex.unijourneybackend.modules.user.application.bus.CommandHandler;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Component
public class SwapCourseHandler
    extends AbstractUpdatedApplicationService
    implements CommandHandler<SwapCourseCommand, SwapCourseResponse> {

    private static final String STUDY_PLAN_NOT_FOUND = "Study plan not found for student: ";
    private static final String ID_NOT_NULL = "id must not be null";

    private final StudyPlanRepositoryPort studyPlanPort;
    private final CourseRepositoryPort coursePort;
    private final StudentResolver resolver;

    public SwapCourseHandler(
        StudyPlanRepositoryPort studyPlanPort,
        CourseRepositoryPort coursePort,
        StudentResolver resolver,
        DomainEventPublisher publisher
    ) {
        super(publisher);
        this.studyPlanPort = studyPlanPort;
        this.coursePort = coursePort;
        this.resolver = resolver;
    }

    @Override
    @Transactional
    public SwapCourseResponse handle(SwapCourseCommand command) {
        Student student = resolver.resolveByUsername(command.username());
        UserId id = Objects.requireNonNull(student.getId(), ID_NOT_NULL);
        StudyPlan studyPlan = studyPlanPort
            .findByStudentId(id)
            .orElseThrow(() -> new IllegalStateException(STUDY_PLAN_NOT_FOUND + command.username()));

        Course courseToRemove = studyPlan
            .getCourses()
            .stream()
            .filter(c -> c.getName().equals(command.courseToRemove()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Course to remove not found in study plan: " + command.courseToRemove()));

        if (courseToRemove.isMandatoryCourse())
            throw new IllegalArgumentException("Course '" + courseToRemove.getName() + "' is mandatory and cannot be replaced.");

        Course courseToAdd = coursePort
            .findByNameAndDegreeCourseName(
                Objects.requireNonNull(command.courseToAdd()),
                Objects.requireNonNull(command.degreeCourseOfNewCourse()))
            .orElseThrow(() -> new IllegalArgumentException("Course to add not found: " + command.courseToAdd()));

        studyPlan.removeCourse(courseToRemove);
        studyPlan.addCourse(courseToAdd);

        StudyPlan saved = withDataAccessHandling(
            "Failed to persist study plan after course swap",
            () -> studyPlanPort.save(studyPlan)
        );

        publishStudyPlanUpdatedEvent(student, courseToRemove.getName(), courseToAdd.getName());

        return new SwapCourseResponse(StudyPlanMapper.toDto(saved));
    }


}
