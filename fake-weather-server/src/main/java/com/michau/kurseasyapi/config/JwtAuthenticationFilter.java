package com.michau.kurseasyapi.config;

import com.michau.kurseasyapi.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OAuth2LoginAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtTokenUtil;

    public JwtAuthenticationFilter(ClientRegistrationRepository clientRegistrationRepository,
                                   OAuth2AuthorizedClientService authorizedClientService,
                                   AuthenticationManager authenticationManager) {
        super(clientRegistrationRepository, authorizedClientService);
        this.authenticationManager = authenticationManager;
    }

    /* Trigger when we issue POST request to /login
    We also need to pass in {"username":"dan", "password":"dan123"} in the request body
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // Grab credentials and map them to login viewmodel
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        // Create login token
        OAuth2AuthenticationToken authenticationToken =
                new OAuth2AuthenticationToken(
                        principal,
                        authentication.getAuthorities(),
                        authentication.getName());

        // Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        final String jwt = jwtTokenUtil.generateToken(authentication);
        System.out.println("Token is..." + jwt);

        response.addHeader("Authorization", "Bearer " + jwt);
    }
}
