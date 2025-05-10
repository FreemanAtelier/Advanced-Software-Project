package com.freemanatelier.freemanateliercustomstore.observer;

import com.freemanatelier.freemanateliercustomstore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderNotifier {

    private final List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.notify(order);
        }
    }
}
