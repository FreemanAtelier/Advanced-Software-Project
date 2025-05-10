package com.freemanatelier.freemanateliercustomstore.model.shoe;

import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class DressShoe extends Shoe {
    public DressShoe() {
        this.name = "Dress Shoe";
        this.price = 90.0;
        this.imageUrl = "/images/dress.jpg"; // Placeholder
    }

    @Override
    public String getDescription() {
        return "A classic Dress shoe.";
    }
}
