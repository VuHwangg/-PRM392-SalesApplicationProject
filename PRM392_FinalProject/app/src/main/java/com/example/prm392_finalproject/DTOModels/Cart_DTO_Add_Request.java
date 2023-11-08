package com.example.prm392_finalproject.DTOModels;

public class Cart_DTO_Add_Request {
    private int customerId;
    private int productId;

    public Cart_DTO_Add_Request() {
    }

    public Cart_DTO_Add_Request(int customerId, int productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Cart_DTO_Add_Request{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                '}';
    }
}
