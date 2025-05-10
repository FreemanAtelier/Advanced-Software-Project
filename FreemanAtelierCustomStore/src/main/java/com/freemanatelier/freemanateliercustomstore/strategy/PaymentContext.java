package com.freemanatelier.freemanateliercustomstore.strategy;

public class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public String executePayment(double amount) {
        return strategy.pay(amount);
    }
}
