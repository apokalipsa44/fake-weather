package com.michau.oauth.web;


import com.google.gson.Gson;
import com.michau.oauth.extractors.GithubPrincipalExtractor;
import com.michau.oauth.model.User;
import com.michau.oauth.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;




    @GetMapping("/logged")
    @ResponseBody
    public OidcUser getLoggedUser(@AuthenticationPrincipal OidcUser principal) {
        String userName = principal.getGivenName();
        String email= principal.getEmail();
        User user=new User(userName, email);
        String jwt = jwtUtil.generateToken(user);
        System.out.println(jwt);
        return principal;
    }

    @GetMapping("/failed")
    public String loginFailed() {
        return "login failed";
    }

}
