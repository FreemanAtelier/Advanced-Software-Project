package com.freemanatelier.freemanateliercustomstore.controller;

import com.freemanatelier.freemanateliercustomstore.model.User;
import com.freemanatelier.freemanateliercustomstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
