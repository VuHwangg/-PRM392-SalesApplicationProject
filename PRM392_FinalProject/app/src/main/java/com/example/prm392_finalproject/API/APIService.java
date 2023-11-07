package com.example.prm392_finalproject.API;

import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Home_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Order_DTO;
import com.example.prm392_finalproject.DTOModels.POST_Cart_DTO;
import com.example.prm392_finalproject.DTOModels.Product_Detail_DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    APIService apiService = new Retrofit.Builder()
//            .baseUrl("http://192.168.1.36:8888/") //nao chay thi cmt thang duoi mo thang nay ra
            .baseUrl("https://653b8a902e42fd0d54d54bb7.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

//    @GET("api/v1/products")
////////////////////////////////////////////        Gia lap         ///////////////////////////////////////////////////
    @GET("productDTO")
    Call<ArrayList<Home_Product_DTO>> listProductHomePage();

    @GET("productDetail/{id}")
    Call<Product_Detail_DTO> getProductDetail(@Path("id") int id);
    @GET("/cart")
    Call<ArrayList<Cart_Product_DTO>> listCart();//cai nay de call gia lap
    @GET("/order")
    Call<ArrayList<Order_DTO>> listOrder();
    @GET("/orderDeatail")
    Call<ArrayList<Cart_Product_DTO>> listOrderDetail();


//////////////////////////////////////           Call that             /////////////////////////////////////////////////////////

    //tat ca id deu dang de mac dinh la 1 nhe

//    @GET("productDTO") //Viet Anh doi duong dan o day
//    Call<ArrayList<Home_Product_DTO>> listProductHomePage();
//
//    @GET("productDetail/{id}")//Viet Anh doi duong dan o day
//    Call<Product_Detail_DTO> getProductDetail(@Path("id") int id);
    @GET("/cart/{id}")//truyen vao id nguoi dung
    Call<ArrayList<Cart_Product_DTO>> listCart(@Path("id") int id);
    @GET("/order/{id}")//truyen vao id nguoi dung, dung thang nay thi vao OrderActivity xoa cmt thang cu lai uncmt thang moi o duoi ra, nho sua lai id nguoi dung ca thang detail cung the
    Call<ArrayList<Order_DTO>> listOrder(@Path("id") int id);
    @GET("/orderDeatail/{id}")//truyen vao id cua order, dung thang nay thi vao OrderActivity xoa cmt thang cu lai uncmt thang moi o duoi ra
    Call<ArrayList<Cart_Product_DTO>> listOrderDetail(@Path("id") int id);
    @POST("/cart")
    Call<Void> updateCart(@Body POST_Cart_DTO cart);

    @PUT("order/{id}")//truyen vào orderID thay doi trang thai ve huy don
    Call<Order_DTO> cancelOrder(@Path("id") int id);

    @GET("numProduct/{id}")//tra ve so luong product trong cart(chi dem product khong can tong quantity)
    Call<Integer> productInCart(@Path("id") int id);

//    @GET("api/v1/products")
//    Call<List<Home_Product_DTO>> getListProduct();

}
