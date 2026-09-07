package com.alex.unijourneybackend.modules.admin.port;

import java.util.Optional;

import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.admin.domain.model.Admin;
import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


public interface AdminRepositoryPort {

    /**
     * Retrieves a page of admins.
     * @param query The pagination information.
     * @return A page of admins.
     */
    @NonNull
    PageResult<Admin> findAll(@NonNull PageQuery query);

    /**
     * Retrieves an admin by their ID.
     * @param id The admin ID.
     * @return The admin.
     */
    @NonNull
    Optional<Admin> findById(@NonNull UserId id);

    /**
     * Creates a new admin.
     * @param admin The admin to create.
     * @return The created admin.
     */
    @NonNull
    Admin create(@NonNull Admin admin);

    /**
     * Deletes an admin.
     * @param admin The admin to delete.
     */
    void delete(@NonNull Admin admin);

    /**
     * Soft-deletes an admin.
     * @param admin The admin to soft-delete.
     */
    void softDelete(@NonNull Admin admin);

    /**
     * Checks if an admin with the given admin code exists.
     * @param code The admin code to check.
     * @return true if an admin with the given admin code exists, false otherwise.
    */
    boolean existsByAdminCode(@NonNull AdminCode code);


}
