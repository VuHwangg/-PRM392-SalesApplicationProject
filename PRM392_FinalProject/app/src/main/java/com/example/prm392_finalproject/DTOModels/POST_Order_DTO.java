package com.example.prm392_finalproject.DTOModels;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class POST_Order_DTO {
    private int customerID;
    private String customerPhone;
    private String customerAddress;
    private String orderDate;
    private double totalPrice;
    private ArrayList<POST_Cart_Product_DTO> products;

    public POST_Order_DTO(int customerID, String customerPhone, String customerAddress, String orderDate, double totalPrice, ArrayList<POST_Cart_Product_DTO> products) {
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
}
