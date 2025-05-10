package com.freemanatelier.freemanateliercustomstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLoginController {

    @GetMapping("/adminpanel")
    public String adminLogin() {
        return "admin-login";  // Thymeleaf template: admin-login.html
    }
}
