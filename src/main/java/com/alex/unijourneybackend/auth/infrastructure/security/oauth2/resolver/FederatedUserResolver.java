package com.alex.unijourneybackend.auth.infrastructure.security.oauth2.resolver;

import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.FederatedUser;

/**
 * FederatedUserResolver
 * Interfaccia per risolvere un utente federato
 * da un oggetto principal.
 */
public interface FederatedUserResolver {

    /**
     * Checks if the given principal object is supported by this resolver.
     * @param principal the principal object
     * @return true if supported, false otherwise
     */
    boolean supports(Object principal);

    /**
     * Resolves a federated user from the given principal object.
     * @param principal the principal object
     * @return the resolved federated user
     */
    FederatedUser resolve(Object principal);


}
