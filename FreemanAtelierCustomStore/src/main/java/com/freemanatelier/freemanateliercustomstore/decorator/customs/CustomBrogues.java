package com.freemanatelier.freemanateliercustomstore.decorator.customs;

import com.freemanatelier.freemanateliercustomstore.decorator.ShoeDecorator;
import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class CustomBrogues extends ShoeDecorator {
    public CustomBrogues(Shoe shoe) {
        super(shoe);
        this.price = decoratedShoe.getPrice() + 8;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getDescription() {
        return decoratedShoe.getDescription() + ", with Brogues custom";
    }
}
