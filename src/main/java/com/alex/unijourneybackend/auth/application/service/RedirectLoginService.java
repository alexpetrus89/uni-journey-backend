package com.alex.unijourneybackend.auth.application.service;


public interface RedirectLoginService {

    /**
     * Redirects the user to the appropriate home page based on their role.
     * @param role the role of the user
     *              (e.g., "ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN")
     * @return the URL to which the user should be redirected
     */
    String resolveRedirectUrlByRole(String role);


}
