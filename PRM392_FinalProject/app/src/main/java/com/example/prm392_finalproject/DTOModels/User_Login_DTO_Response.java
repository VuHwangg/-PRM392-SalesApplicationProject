package com.example.prm392_finalproject.DTOModels;

import java.io.Serializable;

public class User_Login_DTO_Response implements Serializable {
    private int id;
    private String username;
    private String name;
    private String address;
    private String phone;

    public User_Login_DTO_Response() {
    }

    public User_Login_DTO_Response(int id, String username, String name, String address, String phone) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
