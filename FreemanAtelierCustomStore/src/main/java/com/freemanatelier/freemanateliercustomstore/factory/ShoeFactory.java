package com.freemanatelier.freemanateliercustomstore.factory;

import com.freemanatelier.freemanateliercustomstore.model.Shoe;
import com.freemanatelier.freemanateliercustomstore.model.shoe.*;

public class ShoeFactory {
    public static Shoe createShoe(String type) {
        return switch (type.toLowerCase()) {
            case "dress" -> new DressShoe();
            case "boot" -> new BootShoe();
            case "loafer" -> new LoaferShoe();
            case "monk" -> new MonkShoe();
            case "mule" -> new MuleShoe();
            default -> throw new IllegalArgumentException("Invalid shoe type: " + type);
        };
    }
}
