package com.alex.unijourneybackend.auth.infrastructure.security.handler;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.auth.application.service.RedirectLoginService;
import com.alex.unijourneybackend.auth.infrastructure.security.RoleConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * UniJourneyAuthenticationSuccessHandler
 * Manage standard login success behavior.
 */
@Component
public class UniJourneyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // instance variable
    private final RedirectLoginService redirectLoginService;

    // constructor
    public UniJourneyAuthenticationSuccessHandler(RedirectLoginService redirectLoginService) {
        this.redirectLoginService = redirectLoginService;
    }

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {

        String role = RoleConstants.extractHighestRole(authentication.getAuthorities());
        response.sendRedirect(redirectLoginService.resolveRedirectUrlByRole(role));
    }


}
