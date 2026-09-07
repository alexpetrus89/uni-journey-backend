package com.alex.unijourneybackend.auth.application.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RedirectLoginServiceImpl implements RedirectLoginService {

    private final String frontendBaseUrl;

    public RedirectLoginServiceImpl(@Value("${app.frontend.base-url}") String frontendBaseUrl) {
        this.frontendBaseUrl = frontendBaseUrl;
    }

    /**
     * Redirects the user to the appropriate home page based on their role.
     * @param role the role of the user
     *              (e.g., "ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN")
     * @param response the HttpServletResponse object
     * @throws IOException if an input or output exception occurs
     */
    @Override
    public String resolveRedirectUrlByRole(String role) {
        return switch (role) {
            case "ROLE_ADMIN" -> frontendBaseUrl + "/user_admin/admin-home";
            case "ROLE_STUDENT" -> frontendBaseUrl + "/user_student/student-home";
            case "ROLE_PROFESSOR" -> frontendBaseUrl + "/user_professor/professor-home";
            default -> frontendBaseUrl + "/login";
        };
    }


}
