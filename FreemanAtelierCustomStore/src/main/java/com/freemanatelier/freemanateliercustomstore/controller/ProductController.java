package com.freemanatelier.freemanateliercustomstore.controller;

import com.freemanatelier.freemanateliercustomstore.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ShoeRepository shoeRepository;

    @GetMapping
    public String showProducts(Model model) {
        model.addAttribute("shoes", shoeRepository.findAll());
        return "customize";
    }

}
