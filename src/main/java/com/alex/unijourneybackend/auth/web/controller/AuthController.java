package com.alex.unijourneybackend.auth.web.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.unijourneybackend.auth.application.service.LoginAttemptService;
import com.alex.unijourneybackend.auth.application.service.RedirectLoginService;
import com.alex.unijourneybackend.auth.infrastructure.security.RoleConstants;
import com.alex.unijourneybackend.auth.web.util.HttpRequestUtils;
import com.alex.unijourneybackend.modules.user.web.dto.request.LoginRequest;
import com.alex.unijourneybackend.modules.user.web.dto.response.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Tag(name = "auth", description = "Authentication endpoints")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final RedirectLoginService redirectService;
    private final LoginAttemptService attemptService;

    public AuthController(
        AuthenticationManager manager,
        RedirectLoginService redirectService,
        LoginAttemptService attemptService
    ) {
        this.manager = manager;
        this.redirectService = redirectService;
        this.attemptService = attemptService;
    }



    @Operation(summary = "Get current user info")
    @ApiResponse(responseCode = "200", description = "Authenticated")
    @ApiResponse(responseCode = "401", description = "Not authenticated")
    @GetMapping("/me")
    public ResponseEntity<LoginResponse> me(Authentication authentication) {

        if (isAnonymous(authentication))
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();

        String role = RoleConstants.extractHighestRole(authentication.getAuthorities());

        return ResponseEntity.ok(new LoginResponse(authentication.getName(), role, "Authenticated"));
    }


    @Operation(summary = "Redirect to role-based home page")
    @ApiResponse(responseCode = "302", description = "Redirect to role-based home page")
    @GetMapping("/profile")
    public void redirectProfile(
        Authentication authentication,
        HttpServletResponse response
    ) throws IOException {

        // Case: user not logged or anonymous
        if (isAnonymous(authentication)) {
            response.sendRedirect("/login");
            return;
        }

        // Case: user authenticated → get first role and redirect
        String role = RoleConstants.extractHighestRole(authentication.getAuthorities());
        response.sendRedirect(redirectService.resolveRedirectUrlByRole(role));
    }


    @Operation(summary = "Login with username and password")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @ApiResponse(responseCode = "401", description = "Invalid username or password")
    @ApiResponse(responseCode = "429", description = "Too many attempts")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest request,
        HttpServletRequest httpRequest
    ) {

        String ip = HttpRequestUtils.getClientIp(httpRequest);

        // blocca se IP ha superato i tentativi
        if (attemptService.isIpBlocked(ip))
            return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new LoginResponse(null, null, "Too many login attempts. Try again later."));

        try {
            UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.username(), request.password());

            Authentication authentication = manager.authenticate(token);

            saveSecurityContext(authentication, httpRequest);
            attemptService.onLoginSuccess(ip, request.username());

            String role = RoleConstants.extractHighestRole(authentication.getAuthorities());
            return ResponseEntity.ok(new LoginResponse(authentication.getName(), role, "Login successful"));

        } catch (BadCredentialsException _) {
            attemptService.onLoginFailure(ip, request.username());
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponse(null, null, "Invalid username or password"));
        } catch (CredentialsExpiredException _) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponse(null, null, "Credentials expired"));
        } catch (DisabledException _) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponse(null, null, "Account disabled"));
        } catch (LockedException _) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponse(null, null, "Account locked"));
        }
    }


    private void saveSecurityContext(Authentication authentication, HttpServletRequest request) {
        // Salva nel SecurityContext
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        // Persisti la sessione HTTP
        request.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
    }


    private boolean isAnonymous(Authentication authentication) {
        return authentication == null
            || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal());
    }


}


