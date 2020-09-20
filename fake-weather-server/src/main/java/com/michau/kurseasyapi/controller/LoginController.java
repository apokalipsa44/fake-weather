package com.michau.kurseasyapi.controller;

import com.michau.kurseasyapi.service.AuthenticationResponse;
import com.michau.kurseasyapi.service.JwtUtil;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    @GetMapping(value = "/gucio")
    public ResponseEntity<?> createAuthenticationToken() throws Exception {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

authenticationManager.authenticate(new OAuth2AuthenticationToken((OAuth2User) authentication.getPrincipal(),
        null,null));

        final String jwt = jwtTokenUtil.generateToken(authentication);
        System.out.println("Token is..." + jwt);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/maja")
    public String test2(Model model) {
        Object details = ((UsernamePasswordAuthenticationToken) ((OAuth2Authentication) ((SecurityContextImpl) SecurityContextHolder.getContext()).getAuthentication()).getUserAuthentication()).getDetails();
        String name = ((LinkedHashMap) details).values().toArray()[1].toString();
        String picture = ((LinkedHashMap) details).values().toArray()[4].toString();
        String email = ((LinkedHashMap) details).values().toArray()[5].toString();

        model.addAttribute("name", name);
        model.addAttribute("picture", picture);
        model.addAttribute("email", email);
        return "maja oauth2";
    }

    @GetMapping("/failed")
    private String failedLogin(){
        return "login failed";
    }
}
