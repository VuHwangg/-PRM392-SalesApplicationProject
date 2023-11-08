package com.example.prm392_finalproject.DTOModels;

public class User_ChangePassword_DTO {
    private int userID;
    private String password;

    public User_ChangePassword_DTO() {
    }

    public User_ChangePassword_DTO(int userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
