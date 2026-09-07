package com.alex.unijourneybackend.modules.admin.application.query.getadmins;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.infrastructure.events.DomainEventPublisher;
import com.alex.unijourneybackend.modules.admin.port.AdminRepositoryPort;
import com.alex.unijourneybackend.modules.admin.web.dto.AdminResponse;
import com.alex.unijourneybackend.modules.admin.web.mapper.AdminMapper;
import com.alex.unijourneybackend.modules.user.application.command.support.AbstractRegistrationApplicationService;
import com.alex.unijourneybackend.modules.user.application.command.support.RegistrationResponseFactory;

@Service
public class GetAdminsHandler
    extends AbstractRegistrationApplicationService {

    private final AdminRepositoryPort port;
    private final AdminMapper mapper;

    public GetAdminsHandler(
        AdminRepositoryPort port,
        AdminMapper mapper,
        DomainEventPublisher publisher,
        RegistrationResponseFactory factory
    ) {
        super(publisher, factory);
        this.port = port;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public PageResult<AdminResponse> handle(GetAdminsQuery query) {

        PageQuery page = Objects.requireNonNull(query.pageQuery());

        return withDataAccessHandling(
            "Database error while fetching admins.",
            () -> port.findAll(page).map(mapper::toResponse)
        );
    }


}
