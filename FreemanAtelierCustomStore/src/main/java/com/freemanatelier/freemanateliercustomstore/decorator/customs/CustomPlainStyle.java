package com.freemanatelier.freemanateliercustomstore.decorator.customs;

import com.freemanatelier.freemanateliercustomstore.decorator.ShoeDecorator;
import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class CustomPlainStyle extends ShoeDecorator {
    public CustomPlainStyle(Shoe shoe) {
        super(shoe);
        this.price = decoratedShoe.getPrice() + 0;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getDescription() {
        return decoratedShoe.getDescription() + ", with Plain custom";
    }
}
