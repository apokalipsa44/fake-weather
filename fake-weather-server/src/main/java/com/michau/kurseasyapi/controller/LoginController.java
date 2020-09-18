package com.michau.kurseasyapi.controller;

import com.michau.kurseasyapi.service.AuthenticationResponse;
import com.michau.kurseasyapi.service.JwtUtil;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
//public class LoginController {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtTokenUtil;

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    private OAuth2AuthorizedClientService authorizedClientService;


//    @GetMapping(value = "/authenticate")
//    public ResponseEntity<?> createAuthenticationToken() throws Exception {
//        Authentication authentication =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();

//authenticationManager.authenticate(new OAuth2AuthenticationToken((OAuth2User) authentication.getPrincipal(),
//        null,null));

//        final String jwt = jwtTokenUtil.generateToken(authentication);
//        System.out.println("Token is..." + jwt);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }

//}
