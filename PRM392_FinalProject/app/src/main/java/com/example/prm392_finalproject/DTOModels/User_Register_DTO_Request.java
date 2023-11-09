package com.example.prm392_finalproject.DTOModels;

public class User_Register_DTO_Request {
    private String username, name, password, cfpassword;

    public User_Register_DTO_Request(String username, String name, String password, String cfpassword) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.cfpassword = cfpassword;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCfpassword() {
        return cfpassword;
    }

    public void setCfpassword(String cfpassword) {
        this.cfpassword = cfpassword;
    }
}
