package com.freemanatelier.freemanateliercustomstore.strategy.impl;

import com.freemanatelier.freemanateliercustomstore.strategy.PaymentStrategy;

public class DummyPay implements PaymentStrategy {
    @Override
    public String pay(double amount) {
        return "Dummy payment processed for £" + amount;
    }
}
