package com.example.prm392_finalproject.DTOModels;

import java.io.Serializable;

public class Home_Product_DTO implements Serializable {
    private int id;
    private String image;
    private String name;
    private double price;
    private double discount;
    private String color;

    public Home_Product_DTO(int id, String image, String name, double price, double discount, String color) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.color = color;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
