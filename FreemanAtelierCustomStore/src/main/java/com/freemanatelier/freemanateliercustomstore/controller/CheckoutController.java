package com.freemanatelier.freemanateliercustomstore.controller;

import com.freemanatelier.freemanateliercustomstore.model.Order;
import com.freemanatelier.freemanateliercustomstore.model.Shoe;
import com.freemanatelier.freemanateliercustomstore.observer.AdminNotificationObserver;
import com.freemanatelier.freemanateliercustomstore.observer.OrderNotifier;
import com.freemanatelier.freemanateliercustomstore.observer.UserNotificationObserver;
import com.freemanatelier.freemanateliercustomstore.repository.OrderRepository;
import com.freemanatelier.freemanateliercustomstore.service.EmailService;
import com.freemanatelier.freemanateliercustomstore.strategy.PaymentContext;
import com.freemanatelier.freemanateliercustomstore.strategy.PaymentStrategy;
import com.freemanatelier.freemanateliercustomstore.strategy.impl.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String checkoutPage(HttpSession session, Model model) {
        List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
        double total = cart != null ? cart.stream().mapToDouble(Shoe::getPrice).sum() : 0.0;

        model.addAttribute("totalAmount", total);
        model.addAttribute("paymentMethods", List.of("CreditCard", "PayPal", "GooglePay", "DummyPay"));
        return "checkout";
    }

    @PostMapping
    public String processOrder(@RequestParam("method") String method,
                               HttpSession session,
                               Model model,
                               Authentication authentication) {
        List<Shoe> cart = (List<Shoe>) session.getAttribute("cart");
        double total = cart != null ? cart.stream().mapToDouble(Shoe::getPrice).sum() : 0.0;

        // Applying payment strategy
        PaymentContext context = new PaymentContext();
        PaymentStrategy strategy = switch (method) {
            case "CreditCard" -> new CreditCardPayment();
            case "PayPal" -> new PaypalPayment();
            case "DummyPay" -> new DummyPay();
            default -> throw new IllegalArgumentException("Unsupported payment method");
        };
        context.setStrategy(strategy);
        String paymentResult = context.executePayment(total);

        // Get user email
        String userEmail = authentication != null ? authentication.getName() : "user@example.com";

        // Store order
        Order order = new Order();
        order.setUsername(userEmail);
        order.setTotalAmount(total);
        order.setPaymentMethod(method);
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);

        // Sends notifications via Observer pattern
        OrderNotifier notifier = new OrderNotifier();
        notifier.addObserver(new AdminNotificationObserver(emailService));
        notifier.addObserver(new UserNotificationObserver(emailService));
        notifier.notifyAllObservers(order);

        session.removeAttribute("cart");

        model.addAttribute("message", paymentResult);
        model.addAttribute("orderId", order.getId());
        return "order_success";
    }
}
