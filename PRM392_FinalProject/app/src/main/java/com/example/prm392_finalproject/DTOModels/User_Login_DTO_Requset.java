package com.example.prm392_finalproject.DTOModels;

public class User_Login_DTO_Requset {
    private String username;
    private String password;

    public User_Login_DTO_Requset() {
    }

    public User_Login_DTO_Requset(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
