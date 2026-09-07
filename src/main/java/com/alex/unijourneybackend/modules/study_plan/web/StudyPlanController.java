package com.alex.unijourneybackend.modules.study_plan.web;

import java.security.Principal;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alex.unijourneybackend.modules.study_plan.application.command.SwapCourseCommand;
import com.alex.unijourneybackend.modules.study_plan.application.query.GetAvailableCoursesQuery;
import com.alex.unijourneybackend.modules.study_plan.application.query.GetStudyPlanQuery;
import com.alex.unijourneybackend.modules.study_plan.application.query.StudyPlanQueryService;
import com.alex.unijourneybackend.modules.study_plan.web.dto.StudyPlanDto;
import com.alex.unijourneybackend.modules.study_plan.web.dto.SwapCoursesRequest;
import com.alex.unijourneybackend.modules.user.application.bus.CommandBus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/study-plan")
public class StudyPlanController {

    private final StudyPlanQueryService queryService;
    private final CommandBus commandBus;

    public StudyPlanController(StudyPlanQueryService queryService, CommandBus commandBus) {
        this.queryService = queryService;
        this.commandBus = commandBus;
    }


    /**
     * GET /api/v1/study-plan
     * Restituisce il piano di studi dello studente autenticato.
     */
    @GetMapping
    public ResponseEntity<StudyPlanDto> getStudyPlan(Principal principal) {
        return ResponseEntity.ok(queryService.getStudyPlan(new GetStudyPlanQuery(principal.getName())));
    }


    /**
     * GET /api/v1/study-plan/courses?degreeCourse=X
     * Restituisce i corsi disponibili per un dato corso di laurea,
     * esclusi quelli già nel piano dello studente.
     */
    @GetMapping("/courses")
    @Cacheable("courses")
    public ResponseEntity<List<StudyPlanDto.CourseDto>> getAvailableCourses(
        @RequestParam String degreeCourse,
        Principal principal
    ) {
        return ResponseEntity.ok(queryService.getAvailableCourses(new GetAvailableCoursesQuery(principal.getName(), degreeCourse)));
    }


    /**
     * PUT /api/v1/study-plan/swap
     * Sostituisce un corso nel piano dello studente autenticato.
     */
    @PutMapping("/swap")
    public ResponseEntity<StudyPlanDto> swapCourse(
        @Valid @RequestBody SwapCoursesRequest request,
        Principal principal
    ) {
        var command = new SwapCourseCommand(
            principal.getName(),
            request.courseToRemove(),
            request.degreeCourseOfOldCourse(),
            request.courseToAdd(),
            request.degreeCourseOfNewCourse()
        );
        StudyPlanDto result = commandBus.dispatch(command).studyPlan();
        return ResponseEntity.ok(result);
    }

}