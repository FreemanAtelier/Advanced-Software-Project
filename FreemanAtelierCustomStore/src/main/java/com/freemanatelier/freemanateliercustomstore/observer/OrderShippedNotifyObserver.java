package com.freemanatelier.freemanateliercustomstore.observer;

import com.freemanatelier.freemanateliercustomstore.model.Order;
import com.freemanatelier.freemanateliercustomstore.service.EmailService;

public class OrderShippedNotifyObserver implements OrderObserver {
    private final EmailService emailService;

    public OrderShippedNotifyObserver(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void notify(Order order) {
        String subject = "Your Freeman Atelier Order #" + order.getId() + " has been Shipped!";
        String body = "Hi " + order.getUsername() + ",\n\nGood news! Your order has been shipped and is on its way to you.\n\nThank you for Patronizing Freeman Atelier!";
        emailService.sendEmail(order.getUsername(), subject, body);
    }
}
