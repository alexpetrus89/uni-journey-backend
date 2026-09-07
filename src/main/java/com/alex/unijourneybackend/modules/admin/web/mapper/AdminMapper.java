package com.alex.unijourneybackend.modules.admin.web.mapper;



import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.admin.domain.model.Admin;
import com.alex.unijourneybackend.modules.admin.web.dto.AdminResponse;

@Component
public class AdminMapper {

    public AdminResponse toResponse(Admin admin) {
        return new AdminResponse(
            admin.getId().toString(),
            admin.getUsername(),
            admin.getRole().name(),
            admin.getFirstName(),
            admin.getLastName(),
            admin.getDob(),
            admin.getAge(),
            admin.getFiscalCode().fiscalCode(),
            admin.getPhone(),
            admin.getAdminCode().value(),
            admin.getAddress().getStreet(),
            admin.getAddress().getCity(),
            admin.getAddress().getCountry(),
            admin.getAddress().getZipCode()
        );
    }


}
