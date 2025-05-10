package com.freemanatelier.freemanateliercustomstore.observer;

import com.freemanatelier.freemanateliercustomstore.model.Order;

public interface OrderObserver {
    void notify(Order order);
}
