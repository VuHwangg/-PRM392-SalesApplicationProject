package com.example.prm392_finalproject.API;

import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Order_DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIServiceTest {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    APIServiceTest apiService = new Retrofit.Builder()
            .baseUrl("https://653e3e46f52310ee6a9abac5.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIServiceTest.class);

    @GET("/cart")
    Call<ArrayList<Cart_Product_DTO>> listCart();
    @GET("/order")
    Call<ArrayList<Order_DTO>> listOrder();
    @GET("/orderDeatail")
    Call<ArrayList<Cart_Product_DTO>> listOrderDetail();
}
