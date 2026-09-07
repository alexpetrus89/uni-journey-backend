package com.alex.unijourneybackend.modules.admin.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.admin.domain.model.Admin;
import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;
import com.alex.unijourneybackend.modules.admin.port.AdminRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Component
public class AdminRepositoryAdapter implements AdminRepositoryPort {

    private final AdminRepository repository;

    public AdminRepositoryAdapter(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<Admin> findAll(PageQuery query) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size());

        Page<Admin> page = repository.findAll(pageRequest);

        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null") // null-safety garantita dal contratto dichiarato nel port
    public Optional<Admin> findById(UserId id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public Admin create(Admin admin) {
        return repository.save(admin);
    }

    @Override
    @SuppressWarnings("null")
    public void delete(Admin admin) {
        repository.delete(admin);
    }

    @Override
    @SuppressWarnings("null")
    public void softDelete(Admin admin) {
        Admin softDeletedAdmin = repository
            .findById(admin.getId())
            .orElseThrow(() -> new IllegalArgumentException("Admin with id '%s' not found.".formatted(admin.getId().toString())));

        softDeletedAdmin.markDeleted();
        repository.save(softDeletedAdmin);
    }

    @Override
    @SuppressWarnings("null")
    public boolean existsByAdminCode(AdminCode code) {
        return repository.existsByAdminCode(code);
    }


}
