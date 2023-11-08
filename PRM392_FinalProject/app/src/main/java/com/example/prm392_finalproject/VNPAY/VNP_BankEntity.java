package com.example.prm392_finalproject.VNPAY;

import com.google.gson.annotations.SerializedName;

public class VNP_BankEntity {
    @SerializedName("bank_code")
    String bank_code;
    @SerializedName("ios_scheme")
    String ios_scheme;
    @SerializedName("andr_scheme")
    String andr_scheme;

    VNP_BankEntity() {
    }
}

