package com.freemanatelier.freemanateliercustomstore.model.shoe;


import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class MonkShoe extends Shoe {
    public MonkShoe() {
        this.name = "Monk Shoe";
        this.price = 85.0;
        this.imageUrl = "/images/dress.jpg"; // Placeholder
    }

    @Override
    public String getDescription() {
        return "Monk shoe.";
    }
}
