package com.michau.oauth.web;


import com.michau.oauth.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @GetMapping("/logged")
    public String getLoggedUser() {
//        UserDetails user = (UserDetails) getAuthentication().getPrincipal();
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();


//        authenticationManager.authenticate(new OAuth2AuthenticationToken((OAuth2User) loggedInUser.getPrincipal(),
//                null,null));

        final String jwt = jwtUtil.generateToken(loggedInUser);
        System.out.println("Token is..." + jwt);

        return username +" has just logged in";
    }

    @GetMapping("/failed")
    public String loginFailed(){
        return "login failed";
    }

}
