package com.example.prm392_finalproject.API;

import com.example.prm392_finalproject.DTOModels.Home_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Product_Detail_DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.21.106:8888/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("api/v1/products")
    Call<List<Home_Product_DTO>> listProductHomePage();

    @GET("api/v1/products/{id}")
    Call<Product_Detail_DTO> getProductDetail(@Path("id") int id);

//    @GET("api/v1/products")
//    Call<List<Home_Product_DTO>> getListProduct();

}
