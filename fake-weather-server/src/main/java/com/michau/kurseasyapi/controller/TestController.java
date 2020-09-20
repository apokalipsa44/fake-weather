package com.michau.kurseasyapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test")
    private String googleHello(){
        return "hello gringo from google ";
    }
}
