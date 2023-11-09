package com.example.prm392_finalproject.API;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.prm392_finalproject.DTOModels.Home_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Product_Detail_DTO;
import com.example.prm392_finalproject.DTOModels.User_Login_DTO_Requset;
import com.example.prm392_finalproject.DTOModels.User_Login_DTO_Response;
import com.example.prm392_finalproject.GsonAdapter.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

@RequiresApi(api = Build.VERSION_CODES.O)
public interface APILoginService {
    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    APILoginService apiLoginService = new Retrofit.Builder()
            .baseUrl("http://192.168.21.106:8888/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APILoginService.class);

    //    @GET("api/v1/products")
    @POST("api/v1/login")
    Call<User_Login_DTO_Response> login(@Body User_Login_DTO_Requset userLoginDtoRequset);

}
