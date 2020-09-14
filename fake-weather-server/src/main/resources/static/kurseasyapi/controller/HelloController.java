package com.michau.kurseasyapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("turbo")
    public String sayHello() {
        return "det the fackup";
    }

}
