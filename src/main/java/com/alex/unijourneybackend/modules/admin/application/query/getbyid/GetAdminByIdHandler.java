package com.alex.unijourneybackend.modules.admin.application.query.getbyid;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.unijourneybackend.modules.admin.domain.model.Admin;
import com.alex.unijourneybackend.modules.admin.port.AdminRepositoryPort;
import com.alex.unijourneybackend.modules.admin.web.dto.AdminResponse;
import com.alex.unijourneybackend.modules.admin.web.mapper.AdminMapper;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


@Service
public class GetAdminByIdHandler {

    private final AdminRepositoryPort port;
    private final AdminMapper mapper;

    public GetAdminByIdHandler(AdminRepositoryPort port, AdminMapper mapper) {
        this.port = port;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public AdminResponse handle(GetAdminByIdQuery query) {

        UserId id = query.id();
        Objects.requireNonNull(id, "id cannot be null");

        Admin admin = port
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("Admin not found"));

        return mapper.toResponse(admin);
    }


}
