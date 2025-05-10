package com.freemanatelier.freemanateliercustomstore.decorator;

import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public abstract class ShoeDecorator extends Shoe {
    protected final Shoe decoratedShoe;

    public ShoeDecorator(Shoe shoe) {
        this.decoratedShoe = shoe;
    }

    @Override
    public String getName() {
        return decoratedShoe.getName();
    }

    @Override
    public String getImageUrl() {
        return decoratedShoe.getImageUrl();
    }
}
