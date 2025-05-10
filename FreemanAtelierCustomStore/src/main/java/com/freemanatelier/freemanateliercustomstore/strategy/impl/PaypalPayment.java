package com.freemanatelier.freemanateliercustomstore.strategy.impl;

import com.freemanatelier.freemanateliercustomstore.strategy.PaymentStrategy;

public class PaypalPayment implements PaymentStrategy {
    @Override
    public String pay(double amount) {
        return "Paypal payment processed for £" + amount;
    }
}
