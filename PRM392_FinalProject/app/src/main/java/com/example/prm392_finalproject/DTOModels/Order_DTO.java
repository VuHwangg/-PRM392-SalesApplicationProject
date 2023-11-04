package com.example.prm392_finalproject.DTOModels;

import java.io.Serializable;

public class Order_DTO implements Serializable {
    private int id;
    private String customerName;
    private String address;
    private String phone;
    private int status;
    private double price;

    public Order_DTO(int id, String customerName, String address, String phone, int status, double price) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
