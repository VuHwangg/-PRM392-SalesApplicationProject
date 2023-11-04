package com.example.prm392_finalproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.Adapter.CartAdapter;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CartActivity extends AppCompatActivity {
    private RecyclerView revProduct;
    private CartAdapter mCartAdapter;
    private MainActivity mMainActivity;
    private View mView;
    private TextView tvCost;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btnCheckout = findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // Context của fragment
        revProduct = findViewById(R.id.rev_cart);
        tvCost = findViewById(R.id.tv_cart_cost);
        mCartAdapter = new CartAdapter(this,tvCost);
        // Layout hiện thị là dạng liner
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        revProduct.setLayoutManager(linearLayoutManager);
        CartSingleton cartSingleton = CartSingleton.getInstance();
        mCartAdapter.setData(cartSingleton.getCart());
        revProduct.setAdapter(mCartAdapter);
        double totalCost = 0;
        for(Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCart()){
            totalCost += cart_product_dto.getPrice()*cart_product_dto.getQuantity();
        }
        tvCost.setText(String.valueOf((int) totalCost));


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
}