package com.example.prm392_finalproject.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.Adapter.CartAdapter;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.POST_Cart_DTO;
import com.example.prm392_finalproject.DTOModels.POST_Cart_Product_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Session.UserDataManager;
import com.example.prm392_finalproject.Singleton.CartSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.protobuf.StringValue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@RequiresApi(api = Build.VERSION_CODES.O)
public class CartActivity extends AppCompatActivity {
    private RecyclerView revProduct;
    private CartAdapter mCartAdapter;
    private TextView tvCost;
    private Button btnCheckout;
    DecimalFormat decimalFormat = new DecimalFormat("#,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btnCheckout = findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPayment();
            }
        });

        revProduct = findViewById(R.id.rev_cart);
        tvCost = findViewById(R.id.tv_cart_cost);
        mCartAdapter = new CartAdapter(this,tvCost);
        // Layout hiện thị là dạng liner
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        revProduct.setLayoutManager(linearLayoutManager);
//        CartSingleton cartSingleton = CartSingleton.getInstance();
//        mCartAdapter.setData(cartSingleton.getCart());
//        revProduct.setAdapter(mCartAdapter);
        getCartData();//xoa 3 dong tren

        // Cau hinh bottom navigation
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setSelectedItemId(R.id.bottom_cart);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_cart) {

                } else if (id == R.id.bottom_order) {
                    Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_chat) {
                    Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_account) {
                    Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        postCartData();//call API ngon het thi xoa thang duoi di
//        CartSingleton.getInstance().getCartSelected().clear();
    }
    public void goToPayment() {
        double costDouble = 0;
        for(Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCartSelected()){
            costDouble+= cart_product_dto.getPrice()*cart_product_dto.getQuantity();
        }
        try {
            costDouble = Double.parseDouble(tvCost.getText().toString());
        } catch (NumberFormatException e) {
        }
        if(costDouble>0){
            Intent intent = new Intent(this, PaymentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("totalPrice", costDouble);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            Toast.makeText(CartActivity.this,"Chon sản phẩm để thanh toán",Toast.LENGTH_SHORT).show();
        }
    }

    public void getCartData(){
        //thay thang 1 bang thang userID
        APIService.apiService.listCart(UserDataManager.getUserPreference().getId()).enqueue(new Callback<ArrayList<Cart_Product_DTO>>() {
            @Override
            public void onResponse(Call<ArrayList<Cart_Product_DTO>> call, Response<ArrayList<Cart_Product_DTO>> response) {
                CartSingleton.getInstance().getCart().clear();
                CartSingleton.getInstance().getCartSelected().clear();
                CartSingleton.getInstance().setProductList(response.body());
                List<Cart_Product_DTO> list = response.body();
                mCartAdapter.setData(list);
                revProduct.setAdapter(mCartAdapter);
                Log.d("getCartData", String.valueOf(list.get(0).getId()));
            }

            @Override
            public void onFailure(Call<ArrayList<Cart_Product_DTO>> call, Throwable t) {

            }
        });
    }

    public void postCartData(){

        ArrayList<POST_Cart_Product_DTO> post_cart_product_dtos = new ArrayList<POST_Cart_Product_DTO>();
        for (Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCart()){
            POST_Cart_Product_DTO post_cart_product_dto = new POST_Cart_Product_DTO(cart_product_dto.getId(),cart_product_dto.getQuantity());
            post_cart_product_dtos.add(post_cart_product_dto);
        }
        POST_Cart_DTO cart = new POST_Cart_DTO(UserDataManager.getUserPreference().getId(), post_cart_product_dtos);
        APIService.apiService.updateCart(cart).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("UpdateCart","Success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("UpdateCart","Fail");
            }
        });
    }
}