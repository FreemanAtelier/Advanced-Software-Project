package com.freemanatelier.freemanateliercustomstore.observer;

import com.freemanatelier.freemanateliercustomstore.model.Order;
import com.freemanatelier.freemanateliercustomstore.service.EmailService;

public class UserNotificationObserver implements OrderObserver {
    private final EmailService emailService;

    public UserNotificationObserver(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void notify(Order order) {
        String subject = "Your Freeman Atelier Order #" + order.getId();
        String body = "Hi " + order.getUsername() + ",\n\nYour order has been placed successfully! We’ll notify you once it’s shipped.";
        emailService.sendEmail(order.getUsername(), subject, body);
    }
}
