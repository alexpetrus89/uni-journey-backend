package com.alex.unijourneybackend.auth.infrastructure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import com.alex.unijourneybackend.auth.domain.port.out.OAuth2UserServiceProvider;
import com.alex.unijourneybackend.auth.infrastructure.security.handler.UniJourneyOAuth2AuthenticationSuccessHandler;
import com.alex.unijourneybackend.infrastructure.web.registry.UniJourneyPathsRegistry;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class UniJourneyWebSecurityConfig {

	// constants
	private static final String ADMIN = "ADMIN";
	private static final String STUDENT = "STUDENT";
	private static final String PROFESSOR = "PROFESSOR";
	private static final String LOGIN_PAGE = "/api/v1/auth/login";
	private static final String LOGOUT_PAGE = "/api/v1/auth/logout";

	// instance variables
	private final UniJourneyPathsRegistry registry;
	private final OAuth2UserServiceProvider provider;
	private final UniJourneyOAuth2AuthenticationSuccessHandler handler;


	public UniJourneyWebSecurityConfig(
		UniJourneyPathsRegistry registry,
		OAuth2UserServiceProvider provider,
		UniJourneyOAuth2AuthenticationSuccessHandler handler
	) {
		this.registry = registry;
		this.provider = provider;
		this.handler = handler;
	}


	/**
	 * Provides an AuthenticationManager bean for managing authentication.
	 * @param authConfig the AuthenticationConfiguration bean
	 * @return AuthenticationManager that uses the provided UserDetailsService
	 */
    @Bean
	@SuppressWarnings("unused")
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


	/**
	 * Configures the security filter chain for the application.
	 * @param http the HttpSecurity object
	 * @return SecurityFilterChain for the application
	 * @throws Exception if an error occurs
	 */
    @Bean
	@SuppressWarnings({"unused", "java:S4502", "java:S3372", "java:S2092"})
	SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService uds)
		throws Exception
	{
		return http
			.authorizeHttpRequests(requests -> requests
				.requestMatchers(registry.getPathsByRoleMappings().publicUrlsArray()).permitAll()
				.requestMatchers(registry.getPathsByRoleMappings().adminUrlsArray()).hasRole(ADMIN)
				.requestMatchers(registry.getPathsByRoleMappings().studentUrlsArray()).hasAnyRole(STUDENT, ADMIN)
				.requestMatchers(registry.getPathsByRoleMappings().professorUrlsArray()).hasAnyRole(PROFESSOR, ADMIN)
				.requestMatchers("/api/v1/user/update").hasAnyRole(STUDENT, PROFESSOR, ADMIN)
				.anyRequest()
				.authenticated()
			)

			// 🔐 FORM LOGIN (SPA)
			.formLogin(form -> form.disable())

			// 🔐 OAUTH2 (redirect obbligatorio)
			.oauth2Login(oauth2 -> oauth2
				.loginPage(LOGIN_PAGE)
				.userInfoEndpoint(userInfo -> userInfo
					.oidcUserService(provider.googleOidcUserService(uds))
					.userService(provider.gitHubOAuth2UserService(uds))
				)
				.successHandler(handler)
			)

			// 🚪 LOGOUT
			.logout(logout -> logout
				.logoutUrl(LOGOUT_PAGE)
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessHandler((req, res, auth) -> res.setStatus(200))
			)

			// 🛡️ CSRF (cookie-based SPA)
			.csrf(csrf -> csrf
				// HttpOnly=false is intentional: Angular SPA needs to read XSRF-TOKEN
    			// via JavaScript to inject it as X-XSRF-TOKEN request header (RFC standard).
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
				// Keep CSRF protection enabled for all normal HTTP requests.
				// Only disable it for the WebSocket handshake endpoint because the standard
				// CSRF token exchange is not compatible with WebSocket upgrades.
				.ignoringRequestMatchers(registry.getWebSocketUrl())
			)


			// 🚫 NO REDIRECT HTML
			.exceptionHandling(ex -> ex.authenticationEntryPoint((req, res, e) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .accessDeniedHandler((req, res, e) -> res.sendError(HttpServletResponse.SC_FORBIDDEN)))

			.build();
	}


}




