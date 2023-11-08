package com.example.prm392_finalproject.DTOModels;

import java.io.Serializable;

public class POST_Cart_Product_DTO implements Serializable {
    private int id;
    private int quantity;

    public POST_Cart_Product_DTO(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
