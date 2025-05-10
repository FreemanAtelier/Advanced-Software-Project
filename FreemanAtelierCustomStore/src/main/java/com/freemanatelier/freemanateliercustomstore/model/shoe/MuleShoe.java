package com.freemanatelier.freemanateliercustomstore.model.shoe;


import com.freemanatelier.freemanateliercustomstore.model.Shoe;

public class MuleShoe extends Shoe {
    public MuleShoe() {
        this.name = "Mule Shoe";
        this.price = 65.0;
        this.imageUrl = "/images/dress.jpg"; // Placeholder
    }

    @Override
    public String getDescription() {
        return "Mule shoe.";
    }
}
