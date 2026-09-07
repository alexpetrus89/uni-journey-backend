package com.alex.unijourneybackend.modules.user.domain.port.out;

import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.RoleType;

public interface UserRepositoryPort {

    /**
     * find user by his username
     * @param username
     * @return
     */
    @NonNull
    Optional<User> findByUsername(@NonNull String username);

    /**
     * Retrieves a page of users with a role.
     * @param role the users role
     * @param query The pagination information.
     * @return A page of users.
     */
    @NonNull
    PageResult<User> findAllByRole(@Nullable RoleType role, @NonNull PageQuery query);

    /**
     * create new user
     * @param user
     * @return user
     */
    @NonNull
    public User create(@NonNull User user);

    /**
     * Check if a user with the given username exists
     * @param username
     * @return
     */
    boolean existsByUsername(@NonNull String username);

    /**
     * Check if a user with the given fiscal code exists
     * @param fiscalCode
     * @return
     */
    boolean existsByFiscalCode(@NonNull FiscalCode fiscalCode);

}
