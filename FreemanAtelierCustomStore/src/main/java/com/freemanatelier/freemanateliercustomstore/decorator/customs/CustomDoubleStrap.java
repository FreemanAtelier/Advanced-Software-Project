package com.freemanatelier.freemanateliercustomstore.decorator.customs;

import com.freemanatelier.freemanateliercustomstore.decorator.ShoeDecorator;
import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class CustomDoubleStrap extends ShoeDecorator {
    public CustomDoubleStrap(Shoe shoe) {
        super(shoe);
        this.price = decoratedShoe.getPrice() + 5;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getDescription() {
        return decoratedShoe.getDescription() + ", with Double Strap custom";
    }
}
