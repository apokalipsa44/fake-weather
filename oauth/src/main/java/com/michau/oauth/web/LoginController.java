package com.michau.oauth.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/logged")
    public String getLoggedUser(){
        return "user logged in";
    }

    @GetMapping("/failed")
    public String loginFailed(){
        return "login failed";
    }

}
