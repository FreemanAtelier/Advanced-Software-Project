package com.freemanatelier.freemanateliercustomstore.decorator.customs;

import com.freemanatelier.freemanateliercustomstore.decorator.ShoeDecorator;
import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class CustomLaceUp extends ShoeDecorator {
    public CustomLaceUp(Shoe shoe) {
        super(shoe);
        this.price = decoratedShoe.getPrice() + 5;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getDescription() {
        return decoratedShoe.getDescription() + ", with Lace Up custom";
    }
}
