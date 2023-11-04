package com.example.prm392_finalproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.Adapter.CartAdapter;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;

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
    }
}