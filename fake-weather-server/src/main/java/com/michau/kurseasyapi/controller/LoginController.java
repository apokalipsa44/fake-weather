package com.michau.kurseasyapi.controller;

import com.michau.kurseasyapi.config.LoginCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials){

    }

}

// https://nullpointerexception.pl/spring-security-uwierzytelnienie-przy-pomocy-jsona/

// https://nullpointerexception.pl/spring-security-i-json-web-token/