package com.alex.unijourneybackend.auth.domain.port.out;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuth2UserServiceProvider {

    /**
	 * Provides a custom OidcUserService bean for handling OIDC user information.
	 * This implementation is tailored for Google OAuth2 login.
	 * @param uds the UserDetailsService to retrieve user details
	 * @return OidcUserService that loads user details and maps them to the application's User entity
	 * @throws OAuth2AuthenticationException if the user is not registered in the system
	 */
    OidcUserService googleOidcUserService(UserDetailsService uds)
        throws OAuth2AuthenticationException;


    /**
	 * Provides a custom OAuth2UserService bean for handling OAuth2 user information.
	 * This implementation is tailored for GitHub OAuth2 login.
	 * @param uds the UserDetailsService to retrieve user details
	 * @return OAuth2UserService that loads user details and maps them to the application's User entity
	 * @throws OAuth2AuthenticationException if the user is not registered in the system
	 */
    OAuth2UserService<OAuth2UserRequest, OAuth2User> gitHubOAuth2UserService(UserDetailsService uds)
        throws OAuth2AuthenticationException;

}
