package com.alex.unijourneybackend.modules.professor.application.query.getadmins;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.professor.port.ProfessorRepositoryPort;
import com.alex.unijourneybackend.modules.professor.web.dto.ProfessorResponse;
import com.alex.unijourneybackend.modules.professor.web.mapper.ProfessorMapper;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractRegistrationApplicationService;
import com.alex.unijourneybackend.modules.user.application.command.support.RegistrationResponseFactory;

@Component
public class GetProfessorsHandler
        extends AbstractRegistrationApplicationService {

    private final ProfessorRepositoryPort port;
    private final ProfessorMapper mapper;

    public GetProfessorsHandler(
        ProfessorRepositoryPort port,
        ProfessorMapper mapper,
        DomainEventPublisher publisher,
        RegistrationResponseFactory factory
    ) {
        super(publisher, factory);
        this.port = port;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public PageResult<ProfessorResponse> handle(GetProfessorsQuery query) {

        PageQuery page = Objects.requireNonNull(query.pageQuery());

        return withDataAccessHandling(
            "Database error while fetching professors.",
            () -> port.findAll(page).map(mapper::toResponse)
        );
    }
}
