package com.alex.unijourneybackend.auth.infrastructure.security.oauth2.resolver;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.CustomOidcUser;
import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.FederatedUser;



@Component
public class OidcFederatedUserResolver implements FederatedUserResolver {


    /**
     * Verify if the principal is an instance of CustomOidcUser
     * @param principal the principal object
     * @return true if the principal is an instance of CustomOidcUser, false otherwise
     */
    @Override
    public boolean supports(Object principal) {
        return principal instanceof CustomOidcUser;
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
