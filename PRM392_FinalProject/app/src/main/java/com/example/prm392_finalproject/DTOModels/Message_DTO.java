package com.example.prm392_finalproject.DTOModels;

import java.util.Date;

public class Message_DTO {
    public String sendid,receivedid,mess,DateTime;
    public Date dataObj;

    public Message_DTO(String sendid, String receivedid, String mess, String dateTime) {
        this.sendid = sendid;
        this.receivedid = receivedid;
        this.mess = mess;
        DateTime = dateTime;
    }
}
