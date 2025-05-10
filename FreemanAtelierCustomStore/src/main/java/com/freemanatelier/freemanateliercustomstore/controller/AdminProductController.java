package com.freemanatelier.freemanateliercustomstore.controller;

import com.freemanatelier.freemanateliercustomstore.model.Order;
import com.freemanatelier.freemanateliercustomstore.model.ShoeEntity;
import com.freemanatelier.freemanateliercustomstore.observer.OrderNotifier;
import com.freemanatelier.freemanateliercustomstore.observer.OrderShippedNotifyObserver;
import com.freemanatelier.freemanateliercustomstore.repository.ShoeRepository;
import com.freemanatelier.freemanateliercustomstore.repository.OrderRepository;
import com.freemanatelier.freemanateliercustomstore.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminProductController {

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;


    // Admin dashboard - list shoes
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("shoes", shoeRepository.findAll());
        return "admin-products";
    }

    // Show add form
    @GetMapping("/admin/products/add")
    public String showAddForm(Model model) {
        model.addAttribute("shoe", new ShoeEntity());
        return "admin_add_product";
    }

    // Handle add
    @PostMapping("/admin/products/add")
    public String addProduct(@ModelAttribute ShoeEntity shoe) {
        shoeRepository.save(shoe);
        return "redirect:/admin";
    }

    // Show edit form
    @GetMapping("/admin/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<ShoeEntity> shoe = shoeRepository.findById(id);
        if (shoe.isPresent()) {
            model.addAttribute("shoe", shoe.get());
            return "admin_edit_product";
        } else {
            return "redirect:/admin";
        }
    }

    // Handle edit
    @PostMapping("/admin/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ShoeEntity shoe) {
        shoe.setId(id);
        shoeRepository.save(shoe);
        return "redirect:/admin";
    }

    // Handle delete
    @PostMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        shoeRepository.deleteById(id);
        return "redirect:/admin";
    }

    // View orders
    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin-orders";
    }

    @PostMapping("/admin/orders/{id}/ship")
    public String markOrderAsShipped(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus("SHIPPED");
            orderRepository.save(order);

            // Notify user
            OrderNotifier notifier = new OrderNotifier();
            notifier.addObserver(new OrderShippedNotifyObserver(emailService));
            notifier.notifyAllObservers(order);
        }

        return "redirect:/admin/orders";
    }


}
