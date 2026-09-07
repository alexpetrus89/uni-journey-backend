package com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * FederatedUser
 * Interfaccia che rappresenta un utente federato,
 * cioè proveniente da un provider OAuth2/OIDC.
 * Fornisce metodi per ottenere i dettagli dell'utente
 * e le sue autorità.
 */
public interface FederatedUser {

    /**
     * Gets the UserDetails associated with this federated user.
     * @return the UserDetails
     */
    UserDetails getUserDetails();

    /**
     * Gets the authorities associated with this federated user.
     * @return the collection of GrantedAuthority
     */
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserDetails().getAuthorities();
    }

    /**
     * Gets the username of this federated user.
     * @return the username
     */
    default String getUsername() {
        return getUserDetails().getUsername();
    }


}
