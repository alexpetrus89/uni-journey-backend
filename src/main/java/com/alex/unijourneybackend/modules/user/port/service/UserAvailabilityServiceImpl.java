package com.alex.unijourneybackend.modules.user.port.service;

import java.util.Objects;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.modules.user.domain.port.out.UserRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.port.in.UserAvailabilityService;

import jakarta.persistence.PersistenceException;

@Service
public class UserAvailabilityServiceImpl implements UserAvailabilityService {

    private static final String USERNAME_CHECK_ERROR =
        "Database error while checking username availability.";
    private static final String FISCAL_CODE_CHECK_ERROR =
        "Database error while checking fiscal code availability.";

    private final UserRepositoryPort port;

    public UserAvailabilityServiceImpl(UserRepositoryPort port) {
        this.port = port;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        Objects.requireNonNull(username, "Username cannot be null");
        try {
            return port.existsByUsername(username);
        } catch (DataAccessException | PersistenceException exception) {
            throw new DataAccessServiceException(USERNAME_CHECK_ERROR, exception);
        }
    }

    @Override
    public boolean isFiscalCodeTaken(String fiscalCode) {
        Objects.requireNonNull(fiscalCode, "Fiscal code cannot be null");
        FiscalCode parsed = new FiscalCode(fiscalCode);

        try {
            return port.existsByFiscalCode(parsed);
        } catch (DataAccessException | PersistenceException exception) {
            throw new DataAccessServiceException(FISCAL_CODE_CHECK_ERROR, exception);
        }
    }


}