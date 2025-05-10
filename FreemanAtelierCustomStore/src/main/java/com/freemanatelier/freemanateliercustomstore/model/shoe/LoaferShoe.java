package com.freemanatelier.freemanateliercustomstore.model.shoe;


import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class LoaferShoe extends Shoe {
    public LoaferShoe() {
        this.name = "Loafer Shoe";
        this.price = 75.0;
        this.imageUrl = "/images/dress.jpg"; // Placeholder
    }

    @Override
    public String getDescription() {
        return "Loafers shoe.";
    }
}
