package com.alex.unijourneybackend.auth.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.FederatedUser;




public interface FederatedUserService {

    /**
     * Maps an OidcUser (Google) to a FederatedUser.
     * @param oidcUser the OidcUser from the provider
     * @param uds the UserDetailsService to load the DB user
     * @return FederatedUser
     */
    FederatedUser mapOidcUser(OidcUser oidcUser, UserDetailsService uds);


    /**
     * Maps an OAuth2User (GitHub) to a FederatedUser.
     * @param oauth2User the OAuth2User from the provider
     * @param userRequest the OAuth2UserRequest
     * @param uds the UserDetailsService to load the DB user
     * @return FederatedUser
     */
    FederatedUser mapGitHubUser(OAuth2User oauth2User, OAuth2UserRequest userRequest, UserDetailsService uds);


}
