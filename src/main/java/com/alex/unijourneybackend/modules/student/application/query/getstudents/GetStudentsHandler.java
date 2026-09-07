package com.alex.unijourneybackend.modules.student.application.query.getstudents;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.student.port.StudentRepositoryPort;
import com.alex.unijourneybackend.modules.student.web.dto.response.StudentResponse;
import com.alex.unijourneybackend.modules.student.web.mapper.StudentMapper;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractRegistrationApplicationService;
import com.alex.unijourneybackend.modules.user.application.command.support.RegistrationResponseFactory;


@Component
public class GetStudentsHandler
        extends AbstractRegistrationApplicationService {

    private final StudentRepositoryPort port;
    private final StudentMapper mapper;

    public GetStudentsHandler(
        StudentRepositoryPort port,
        StudentMapper mapper,
        DomainEventPublisher publisher,
        RegistrationResponseFactory factory
    ) {
        super(publisher, factory);
        this.port = port;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public PageResult<StudentResponse> handle(GetStudentsQuery query) {

        PageQuery page = Objects.requireNonNull(query.pageQuery());

        return withDataAccessHandling(
            "Database error while fetching students.",
            () -> port.findAll(page).map(mapper::toResponse)
        );
    }


}

