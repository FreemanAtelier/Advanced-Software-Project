package com.freemanatelier.freemanateliercustomstore.strategy.impl;

import com.freemanatelier.freemanateliercustomstore.strategy.PaymentStrategy;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public String pay(double amount) {
        return "Paid £" + amount + " with Credit Card.";
    }
}
