package com.example.prm392_finalproject.DTOModels;

import java.io.Serializable;

public class Cart_Product_DTO implements Serializable {
    private int id;
    private String image;
    private String name;
    private double price;
    private int quantity;

    public Cart_Product_DTO(int id, String image, String name, double price, int quantity) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
