package com.freemanatelier.freemanateliercustomstore.observer;

import com.freemanatelier.freemanateliercustomstore.model.Order;
import com.freemanatelier.freemanateliercustomstore.service.EmailService;

public class AdminNotificationObserver implements OrderObserver {
    private final EmailService emailService;
    private final String adminEmail = "okocharlesogbonnia@gmail.com";

    public AdminNotificationObserver(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void notify(Order order) {
        String subject = "New Order Placed: #" + order.getId();
        String body = "User " + order.getUsername() + " has placed an order. Total: £" + order.getTotalAmount();
        emailService.sendEmail(adminEmail, subject, body);
    }
}
