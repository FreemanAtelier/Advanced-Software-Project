package com.freemanatelier.freemanateliercustomstore.model.shoe;

import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class BootShoe extends Shoe {
    public BootShoe() {
        this.name = "Boot Shoe";
        this.price = 95.0;
        this.imageUrl = "/images/dress.jpg"; // Placeholder
    }

    @Override
    public String getDescription() {
        return "Boot Shoe.";
    }
}
