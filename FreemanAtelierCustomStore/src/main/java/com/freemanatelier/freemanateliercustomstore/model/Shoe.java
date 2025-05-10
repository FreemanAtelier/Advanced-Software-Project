package com.freemanatelier.freemanateliercustomstore.model;

public abstract class Shoe {
    protected String name;
    protected double price;
    protected String imageUrl;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public abstract String getDescription();
}
