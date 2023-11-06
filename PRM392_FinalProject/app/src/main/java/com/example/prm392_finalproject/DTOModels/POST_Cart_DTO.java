package com.example.prm392_finalproject.DTOModels;

import java.util.ArrayList;

public class POST_Cart_DTO {
    private int cusID;
    private ArrayList<POST_Cart_Product_DTO> post_cart_dtos;

    public POST_Cart_DTO(int cusID, ArrayList<POST_Cart_Product_DTO> post_cart_dtos) {
        this.cusID = cusID;
        this.post_cart_dtos = post_cart_dtos;
    }

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public ArrayList<POST_Cart_Product_DTO> getPost_cart_dtos() {
        return post_cart_dtos;
    }

    public void setPost_cart_dtos(ArrayList<POST_Cart_Product_DTO> post_cart_dtos) {
        this.post_cart_dtos = post_cart_dtos;
    }
}
