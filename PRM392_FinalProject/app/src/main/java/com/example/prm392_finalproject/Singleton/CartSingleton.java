package com.example.prm392_finalproject.Singleton;

import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {
    private static CartSingleton instance;
    private List<Cart_Product_DTO> cart = new ArrayList<>();
    private List<Cart_Product_DTO> cartSelected = new ArrayList<>();

    public CartSingleton() {
    }
    public static CartSingleton getInstance()
    {
        if (instance == null) {
            instance = new CartSingleton();
        }
        return instance;
    }

    public List<Cart_Product_DTO> getCart() {
        return cart;
    }
    public void setProductList(List<Cart_Product_DTO> cart) {
        this.cart =cart;
    }

    public List<Cart_Product_DTO> getCartSelected() {
        return cartSelected;
    }
}
