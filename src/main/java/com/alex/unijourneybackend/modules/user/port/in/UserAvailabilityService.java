package com.alex.unijourneybackend.modules.user.port.in;

import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;

public interface UserAvailabilityService {

    /**
     * Check if the username is already in use by other users
     * @param username
     * @return true if the username is already in use, false otherwise
     * @throws DataAccessServiceException if there is an error accessing the database.
     */
    boolean isUsernameTaken(String username);

    /**
     * Check if the fiscal code is already in use by other users
     * @param fiscalCode
     * @return true if the fiscal code is already in use, false otherwise
     * @throws DataAccessServiceException if there is an error accessing the database.
     */
    boolean isFiscalCodeTaken(String fiscalCode);

}