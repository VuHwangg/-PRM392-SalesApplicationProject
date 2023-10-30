package com.example.prm392_finalproject;

import java.io.Serializable;

public class Order implements Serializable {

    private int orderId, orderCost, orderStatus;
    private String username, phoneNum, address;

    public Order(int orderId, int orderCost, String username, String phoneNum, String address, int orderStatus) {
        this.orderId = orderId;
        this.orderCost = orderCost;
        this.username = username;
        this.phoneNum = phoneNum;
        this.address = address;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(int orderCost) {
        this.orderCost = orderCost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
