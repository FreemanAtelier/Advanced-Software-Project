package com.freemanatelier.freemanateliercustomstore.controller;

import com.freemanatelier.freemanateliercustomstore.decorator.customs.*;
import com.freemanatelier.freemanateliercustomstore.factory.ShoeFactory;
import com.freemanatelier.freemanateliercustomstore.model.Shoe;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/customize")
public class CustomizeController {

    @GetMapping
    public String showShoeTypes(Model model) {
        List<String> shoeTypes = List.of("Dress", "Boot", "Loafer", "Monk", "Mule");
        List<String> customs = List.of("Oxford", "Derby", "Brogues", "Lace Up", "Double Strap", "Tassels", "Cap Toe", "Plain Toe", "Whole Cut");

        model.addAttribute("shoeTypes", shoeTypes);
        model.addAttribute("customOptions", customs);
        return "customize";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String shoeType,
                            @RequestParam(required = false) List<String> customs,
                            HttpSession session) {
        Shoe shoe = ShoeFactory.createShoe(shoeType);

        if (customs != null) {
            for (String custom : customs) {
                switch (custom) {
                    case "Oxford" -> shoe = new CustomOxford(shoe);
                    case "Derby" -> shoe = new CustomDerby(shoe);
                    case "Brogues" -> shoe = new CustomBrogues(shoe);
                    case "Lace Up" -> shoe = new CustomLaceUp(shoe);
                    case "Double Strap" -> shoe = new CustomDoubleStrap(shoe);
                    case "Tassels" -> shoe = new CustomTassels(shoe);
                    case "Cap Toe" -> shoe = new CustomCapToe(shoe);
                    case "Plain Toe" -> shoe = new CustomPlainStyle(shoe);
                }
            }
        }

        List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(shoe);
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }
}
