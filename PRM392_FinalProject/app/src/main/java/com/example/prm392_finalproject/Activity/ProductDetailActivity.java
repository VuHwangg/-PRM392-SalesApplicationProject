package com.example.prm392_finalproject.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Product_Detail_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView tvName, tvDes, btnBack;
    private TextView tvPrice, tvDiscount;
    private Button btn_addtocart, btn_loadmore;
    private View mView;
    private BottomNavigationView bottomNavigationView;
    private boolean isExpanded = false;
    private Product_Detail_DTO product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        image = findViewById(R.id.img_productdetail);
        tvDiscount = findViewById(R.id.tv_discount_productdetail);
        tvName = findViewById(R.id.tv_name_productdetail);
        tvDes = findViewById(R.id.tv_description_productdetail);
        tvPrice = findViewById(R.id.tv_price_productdetail);
        btnBack = findViewById(R.id.btn_back);
        btn_addtocart = findViewById(R.id.btn_addtocart);
        btn_loadmore = findViewById(R.id.btn_loadmore);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = (int) bundle.get("product_id");
            callAPIProductDetail(id);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check =false;
                CartSingleton cartSingleton = CartSingleton.getInstance();
                for(Cart_Product_DTO cart_product_dto : cartSingleton.getCart()){
                    if(cart_product_dto.getId() == product.getId()){
                        cart_product_dto.setQuantity(cart_product_dto.getQuantity()+1);
                        check =true;
                        Log.d("b","b1");
                        Toast.makeText(ProductDetailActivity.this, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                if (check == false){
                    cartSingleton.getCart().add(new Cart_Product_DTO(product.getId(),product.getImage(),product.getName(),product.getPrice(),1, product.getColor()));
                    Log.d("b","a1");
                    Toast.makeText(ProductDetailActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isExpanded = !isExpanded;
                if (isExpanded) {
                    tvDes.setMaxLines(Integer.MAX_VALUE);
                    btn_loadmore.setText("Thu gọn...");
                } else {
                    tvDes.setMaxLines(3);
                    btn_loadmore.setText("Xem thêm...");
                }
            }
        });
    }
    private void callAPIProductDetail(int id) {
        APIService.apiService.getProductDetail(id).enqueue(new Callback<Product_Detail_DTO>() {
            @Override
            public void onResponse(Call<Product_Detail_DTO> call, Response<Product_Detail_DTO> response) {
                product = response.body();
                if (product != null) {
                    Glide.with(ProductDetailActivity.this)
                            .load(product.getImage())
                            .centerCrop()
                            .into(image);
                    tvName.setText(product.getName());
                    tvDes.setText(product.getDescription());
                    tvPrice.setText((int) product.getPrice() + "VNĐ");
                    tvDiscount.setText("Giảm giá " + (double) product.getDiscount() + "%");
                }
            }

            @Override
            public void onFailure(Call<Product_Detail_DTO> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Call API failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}