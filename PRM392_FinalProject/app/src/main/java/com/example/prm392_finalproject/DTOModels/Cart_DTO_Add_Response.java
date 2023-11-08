package com.example.prm392_finalproject.DTOModels;

public class Cart_DTO_Add_Response {
    private int cartId;
    private int quantity;
    private int customerId;
    private int productId;

    public Cart_DTO_Add_Response() {
    }

    public Cart_DTO_Add_Response(int cartId, int quantity, int customerId, int productId) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.customerId = customerId;
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
