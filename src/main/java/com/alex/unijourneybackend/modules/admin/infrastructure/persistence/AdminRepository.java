package com.alex.unijourneybackend.modules.admin.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.admin.domain.model.Admin;
import com.alex.unijourneybackend.modules.admin.domain.valueobject.AdminCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


public interface AdminRepository
    extends JpaRepository<Admin, UserId> {

    /**
     * Retrieves all admins user
     * @param pageable the pagination information
     * @return a page of admins, null otherwise
     */
    @Override
    @NonNull
    Page<Admin> findAll(@NonNull Pageable pageable);

    /**
     * Retrieves an admin by then user id
     * @param id the user id of the admin to retrieve
     * @return Optional<Admin> with the admin if found, or an empty
     *        Optional if no admin is found
     */
    @Override
    @NonNull
    Optional<Admin> findById(@NonNull UserId id);


    /**
     * Retrieves an admin by admin code
     * @param code the unique code of the professor to retrieve
     * @return Optional<Admin> with the admin if found, or an empty
     *        Optional if no admin is found
     * @see UniqueCode
     */
    Optional<Admin> findByAdminCode(AdminCode code);



    /**
     * Retrieves an admin by fiscal code
     * @param code the fiscal code of the admin to retrieve
     * @return Optional<admin> with the admin if found, or an empty
     *        Optional if no admin is found
     * @see FiscalCode
     */
    Optional<Admin> findByFiscalCode(FiscalCode code);


    /**
     * Retrieves an admin by name
     * @param name the name of the admin to retrieve
     * @return a list of admin with same fullname
     */
    List<Admin> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(
        String firstName,
        String lastName
    );



    /**
     * Checks if an admin exists by unique code
     * @param code the admin code of the admin
     * @return true if the admin exists, false otherwise
     * @see AdminCode
     */
    boolean existsByAdminCode(AdminCode code);


    /**
     * Checks if an admin exists by fiscal code
     * @param code the fiscal code of the admin
     * @return true if the admin exists, false otherwise
     * @see FiscalCode
     */
    boolean existsByFiscalCode(FiscalCode code);


    /**
     * Deletes an admin by admin code
     * @param code the admin code of the admin to delete
     * @see AdminCode
     */
    @Modifying
    void deleteByAdminCode(AdminCode code);


}
