package com.example.prm392_finalproject;

import java.io.Serializable;

public class ProductOrder implements Serializable {

    private int image, price, quantity;
    private String name;

    public ProductOrder(int image, String name, int price, int quantity) {
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
