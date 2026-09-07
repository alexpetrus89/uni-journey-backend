package com.alex.unijourneybackend.modules.user.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.port.out.UserRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository repository;

    public UserRepositoryAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<User> findAllByRole(RoleType role, PageQuery query) {
        List<User> filtered = repository.findAll()
            .stream()
            .filter(user -> role == null || user.getRole() == role)
            .toList();

        int fromIndex = query.page() * query.size();
        int toIndex = Math.min(fromIndex + query.size(), filtered.size());

        List<User> pageContent = fromIndex >= filtered.size()
            ? List.of()
            : filtered.subList(fromIndex, toIndex);

        return new PageResult<>(pageContent, filtered.size());
    }

    @Override
    @SuppressWarnings("null")
    public User create(User user) {
        return repository.save(user);
    }


    @Override
    @SuppressWarnings("null")
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    @SuppressWarnings("null")
    public boolean existsByFiscalCode(FiscalCode fiscalCode) {
        return repository.existsByFiscalCode(fiscalCode);
    }


}