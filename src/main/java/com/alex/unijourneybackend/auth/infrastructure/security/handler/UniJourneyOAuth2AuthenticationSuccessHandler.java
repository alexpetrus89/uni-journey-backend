package com.alex.unijourneybackend.auth.infrastructure.security.handler;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.auth.application.service.RedirectLoginService;
import com.alex.unijourneybackend.auth.infrastructure.security.RoleConstants;
import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.resolver.FederatedUserResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * UniJourneyOAuth2AuthenticationSuccessHandler
 * Manage OAuth2 login success behavior.
 */

/**
 * Step by Step: Login con Google o GitHub
 *
 * Google (OIDC) flow:
 * L’utente clicca “Login with Google”.
 * Spring Security reindirizza a Google con OIDC request.
 * Google autentica l’utente e restituisce ID token + user info.
 * OidcUserService.loadUser() viene chiamato.
 * Carica OidcUser da Google.
 * Recupera email → carica UserDetails dal DB.
 * Crea CustomOidcUser (DB + provider).
 * Passa al UniJourneyOAuth2AuthenticationSuccessHandler.
 * Handler:
 * Risolve il FederatedUser.
 * Ricrea Authentication con UserDetails reale.
 * Aggiorna SecurityContext.
 * Redirect basato su ruolo.
 *
 *
 * GitHub (OAuth2) flow:
 * L’utente clicca “Login with GitHub”.
 * Spring Security reindirizza a GitHub con OAuth2 request.
 * GitHub autentica l’utente e restituisce access token + user info.
 * OAuth2UserService.loadUser() viene chiamato.
 * Recupera email dal provider o fallback API.
 * Carica UserDetails dal DB con quell’email.
 * Crea CustomOAuth2User (DB + provider).
 * Passa al UniJourneyOAuth2AuthenticationSuccessHandler.
 * Handler:
 * Risolve il FederatedUser.
 * Ricrea Authentication con UserDetails reale.
 * Aggiorna SecurityContext.
 * Redirect basato su ruolo.
 */
@Component
public class UniJourneyOAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // instance variables
    private final RedirectLoginService redirectLoginService;
    private final List<FederatedUserResolver> resolvers;

    public UniJourneyOAuth2AuthenticationSuccessHandler(
        RedirectLoginService redirectLoginService,
        List<FederatedUserResolver> resolvers
    ) {
        this.redirectLoginService = redirectLoginService;
        this.resolvers = resolvers;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        // Recupera il FederatedUser
        Object principal = authentication.getPrincipal();

        // Risolve il FederatedUser
        UserDetails userDetails = resolvers
            .stream()
            .filter(r -> r.supports(principal))
            .findFirst()
            .map(r -> r.resolve(principal))
            .orElseThrow(() -> new IllegalStateException("Unsupported principal: " + principal.getClass()))
            .getUserDetails();

        // Ricrea il token con l’utente DB
        Authentication authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        String role = RoleConstants.extractHighestRole(userDetails.getAuthorities());
        response.sendRedirect(redirectLoginService.resolveRedirectUrlByRole(role));
    }


}

