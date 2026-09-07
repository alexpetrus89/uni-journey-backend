package com.alex.unijourneybackend.auth.infrastructure.security.oauth2.provider;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.auth.application.service.FederatedUserService;
import com.alex.unijourneybackend.auth.domain.port.out.OAuth2UserServiceProvider;




@Service
public class OAuth2UserServiceProviderImpl implements OAuth2UserServiceProvider {

    // instance variables
    private final FederatedUserService federatedUserService;

    // constructor
    public OAuth2UserServiceProviderImpl(FederatedUserService federatedUserService) {
        this.federatedUserService = federatedUserService;
    }


    @Override
    public OidcUserService googleOidcUserService(UserDetailsService uds)
        throws OAuth2AuthenticationException
    {
        return new OidcUserService() {
            @Override
            public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
                OidcUser oidcUser = super.loadUser(userRequest);
                return (OidcUser) federatedUserService.mapOidcUser(oidcUser, uds);
            }
        };
    }


    @Override
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> gitHubOAuth2UserService(UserDetailsService uds)
        throws OAuth2AuthenticationException
    {
        return userRequest -> {
            OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
            return (OAuth2User) federatedUserService.mapGitHubUser(oauth2User, userRequest, uds);
        };
    }


}

