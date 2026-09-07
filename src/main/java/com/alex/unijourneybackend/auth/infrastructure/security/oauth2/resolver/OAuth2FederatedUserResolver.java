package com.alex.unijourneybackend.auth.infrastructure.security.oauth2.resolver;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.CustomOAuth2User;
import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.FederatedUser;


@Component
public class OAuth2FederatedUserResolver implements FederatedUserResolver {

    /**
     * Verify if the principal is an instance of CustomOAuth2User
     * @param principal the principal object
     * @return true if supported, false otherwise
     */
    @Override
    public boolean supports(Object principal) {
        return principal instanceof CustomOAuth2User;
    }


    /**
     * Resolve the principal to a FederatedUser
     * @param principal the principal object
     * @return the resolved federated user
     */
    @Override
    public FederatedUser resolve(Object principal) {
        return (FederatedUser) principal;
    }


}
