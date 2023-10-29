package com.example.prm392_finalproject;

import java.io.Serializable;

public class Product implements Serializable {

    private int image, price;
    private String name, description;
    private double discount;

    public Product(int image, String name, String description, int price, double discount) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
        this.discount = discount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
