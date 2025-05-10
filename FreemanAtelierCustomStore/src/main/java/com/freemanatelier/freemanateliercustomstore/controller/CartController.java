package com.freemanatelier.freemanateliercustomstore.controller;

import com.freemanatelier.freemanateliercustomstore.model.Shoe;
import com.freemanatelier.freemanateliercustomstore.model.ShoeEntity;
import com.freemanatelier.freemanateliercustomstore.repository.ShoeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ShoeRepository shoeRepository;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        double total = cart.stream().mapToDouble(Shoe::getPrice).sum();

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        ShoeEntity shoe = shoeRepository.findById(id).orElse(null);
        if (shoe == null) return "redirect:/products";

        List<ShoeEntity> cart = (List<ShoeEntity>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        cart.add(shoe);
        session.setAttribute("cart", cart);

        return "redirect:/products";
    }

    @PostMapping("/remove/{index}")
    public String removeFromCart(@PathVariable int index, HttpSession session) {
        List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }
}
