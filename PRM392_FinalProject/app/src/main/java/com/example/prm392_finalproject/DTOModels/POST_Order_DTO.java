package com.example.prm392_finalproject.DTOModels;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class POST_Order_DTO implements Serializable {
    private int customerID;
    private String customerPhone;
    private String customerAddress;
    private LocalDate orderDate;
    private double totalPrice;
    private ArrayList<POST_Cart_Product_DTO> products;

    public POST_Order_DTO(int customerID, String customerPhone, String customerAddress, LocalDate orderDate, double totalPrice, ArrayList<POST_Cart_Product_DTO> products) {
        this.customerID = customerID;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<POST_Cart_Product_DTO> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<POST_Cart_Product_DTO> products) {
        this.products = products;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
