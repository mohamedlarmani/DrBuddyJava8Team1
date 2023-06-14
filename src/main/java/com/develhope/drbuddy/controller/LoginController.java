package com.develhope.drbuddy.controller;

import it.pasqualecavallo.studentsmaterial.authorization_framework.security.PublicEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @PublicEndpoint
    @GetMapping("/user/login")
    public String index() {
        return "login";
    }
}