package com.freemanatelier.freemanateliercustomstore.decorator.customs;

import com.freemanatelier.freemanateliercustomstore.decorator.ShoeDecorator;
import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class CustomDerby extends ShoeDecorator {
    public CustomDerby(Shoe shoe) {
        super(shoe);
        this.price = decoratedShoe.getPrice() + 12;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getDescription() {
        return decoratedShoe.getDescription() + ", with Derby custom";
    }
}
