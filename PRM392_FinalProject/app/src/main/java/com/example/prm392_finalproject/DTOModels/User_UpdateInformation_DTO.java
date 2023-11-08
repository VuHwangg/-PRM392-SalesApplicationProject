package com.example.prm392_finalproject.DTOModels;

import java.io.Serializable;

public class User_UpdateInformation_DTO implements Serializable {
    private int userID;
    private String newName;
    private String newPhone;
    private String newAddress;

    public User_UpdateInformation_DTO(){

    }

    public User_UpdateInformation_DTO(int userID, String newName, String newPhone, String newAddress){
        this.userID = userID;
        this.newName = newName;
        this.newPhone = newPhone;
        this.newAddress = newAddress;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
}
