package com.michau.oauth.web;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Bean
    Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @GetMapping("/logged")
    public String getLoggedUser() {
        return getAuthentication().getName() +" has just logged in";
    }

    @GetMapping("/failed")
    public String loginFailed(){
        return "login failed";
    }

}
