package com.alex.unijourneybackend.auth.application.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.CustomOAuth2User;
import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.CustomOidcUser;
import com.alex.unijourneybackend.auth.infrastructure.security.oauth2.user.FederatedUser;
import com.alex.unijourneybackend.common.infrastructure.exception.GitHubEmailFetchException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
public class FederatedUserServiceImpl implements FederatedUserService {

    // logger
    private final Logger logger = LoggerFactory.getLogger(FederatedUserServiceImpl.class);

    // instance variable
    private final HttpClient httpClient;

    public FederatedUserServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public FederatedUser mapOidcUser(OidcUser oidcUser, UserDetailsService uds) {
        UserDetails userDetails = uds.loadUserByUsername(oidcUser.getEmail());
        return new CustomOidcUser(userDetails, oidcUser.getIdToken(), oidcUser.getUserInfo());
    }


    @Override
    public FederatedUser mapGitHubUser(OAuth2User oauth2User, OAuth2UserRequest userRequest, UserDetailsService uds) {
        String email = resolveGitHubEmail(oauth2User, userRequest);
        UserDetails userDetails = uds.loadUserByUsername(email);
        return new CustomOAuth2User(userDetails, oauth2User);
    }


    /**
	 * Resolves the email address of the authenticated GitHub user.
	 * It first attempts to retrieve the email from the OAuth2User attributes.
	 * If not found, it falls back to making a request to the GitHub API to fetch the primary email.
	 * @param oauth2User the OAuth2User object
	 * @param userRequest the OAuth2UserRequest object
	 * @return the email address of the authenticated GitHub user
	 */
    private String resolveGitHubEmail(OAuth2User oauth2User, OAuth2UserRequest userRequest) {
        String email = oauth2User.getAttribute("email");
        if (email != null && !email.isBlank()) return email;

        return fetchPrimaryEmailFromGitHub(userRequest.getAccessToken().getTokenValue());
    }


    /**
	 * Fetches the primary email address of the authenticated user from GitHub using the provided access token.
	 * @param token the OAuth2 access token
	 * @return the primary email address, or null if not found
	 */
    private String fetchPrimaryEmailFromGitHub(String token) {
        try {
            HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.github.com/user/emails"))
                .header("Authorization", "token " + token)
                .header("Accept", "application/vnd.github.v3+json")
                .GET()
                .build();

            HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error("GitHub API returned status: {}", response.statusCode());
                throw new GitHubEmailFetchException("GitHub API returned unexpected status: " + response.statusCode());
            }

            JSONArray emails = (JSONArray) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(response.body());

            for (Object obj : emails) {
                JSONObject emailObj = (JSONObject) obj;
                if (Boolean.TRUE.equals(emailObj.get("primary")))
                    return (String) emailObj.get("email");
            }

            // ✅ nessun null silenzioso
            throw new GitHubEmailFetchException("No primary email found in GitHub account");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new GitHubEmailFetchException("Thread interrupted while fetching email from GitHub", e);
        } catch (IOException | net.minidev.json.parser.ParseException e) {
            throw new GitHubEmailFetchException("Failed to fetch email from GitHub", e);
        }
    }


}

