package com.alex.unijourneybackend.auth.infrastructure.security.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UniJourneyAccessDeniedHandler implements AccessDeniedHandler {

    // logger
    private static final Logger logger =
        LoggerFactory.getLogger(UniJourneyAccessDeniedHandler.class);

    @Override
    public void handle(
        HttpServletRequest request,
        HttpServletResponse response,
        AccessDeniedException ex
    ) throws IOException, ServletException {

        // crea messaggio
        String error = String.format("Access denied for user: %s, URL: %s, reason: %s", request.getUserPrincipal(), request.getRequestURI(), ex.getMessage());
        // Log dell'evento
        logger.error(error);

        // Reindirizza alla pagina 403 personalizzata
        response.sendError(HttpServletResponse.SC_FORBIDDEN, error);
    }


}

