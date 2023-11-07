package com.example.prm392_finalproject.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.CreateOrder;
import com.example.prm392_finalproject.DTOModels.POST_Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.POST_Order_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import vn.zalopay.sdk.Environment;
//import vn.zalopay.sdk.ZaloPayError;
//import vn.zalopay.sdk.ZaloPaySDK;
//import vn.zalopay.sdk.listeners.PayOrderListener;

public class PaymentActivity extends AppCompatActivity {

    TextView paymentCost;
    EditText phone,address;
    Button payment;
    double totalCost = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StrictMode.ThreadPolicy policy = new
//                StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        // ZaloPay SDK Init
//        ZaloPaySDK.init(2553, Environment.SANDBOX);
        setContentView(R.layout.activity_payment);
        paymentCost=findViewById(R.id.tv_payment_cost);
        payment = findViewById(R.id.btn_payment_zalo);
        phone = findViewById(R.id.edt_phone_num);
        address = findViewById(R.id.edt_address);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            totalCost = (double) bundle.get("totalPrice");
        }
        paymentCost.setText(Double.toString(totalCost));
        payment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(phone.getText().length()==0 && address.getText().length()==0){
                    Toast.makeText(PaymentActivity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
//                    requestZalo();
                    addOrder();
                    Log.d("dấdasd","sdadada");
                }
            }
        });

        // Ẩn action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

//    private void requestZalo() {
//        CreateOrder orderApi = new CreateOrder();
//        try {
//            String totalPrice = "3000000";
//            int x = (int) totalCost;
//            JSONObject data = orderApi.createOrder(Integer.toString(x));
//            Log.d("Amount", Double.toString(totalCost));
//            String code = data.getString("return_code");
//            Log.d("Amount2", code);
//            if (code.equals("1")) {
//                String token = data.getString("zp_trans_token");
//                ZaloPaySDK.getInstance().payOrder(PaymentActivity.this,token,"demozpdk://app", new PayOrderListener(){
//
//                    @Override
//                    public void onPaymentSucceeded(String s, String s1, String s2) {
//
//                    }
//
//                    @Override
//                    public void onPaymentCanceled(String s, String s1) {
//
//                    }
//
//                    @Override
//                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
//
//                    }
//                });
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        ZaloPaySDK.getInstance().onResult(intent);
//    }
    public void addOrder(){
        ArrayList<POST_Cart_Product_DTO> post_cart_product_dtos = new ArrayList<POST_Cart_Product_DTO>();
        for (Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCartSelected()){
            POST_Cart_Product_DTO post_cart_product_dto = new POST_Cart_Product_DTO(cart_product_dto.getId(),cart_product_dto.getQuantity());
            post_cart_product_dtos.add(post_cart_product_dto);
        }
        POST_Order_DTO post_order_dto = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            post_order_dto = new POST_Order_DTO(1, phone.getText().toString(), address.getText().toString(), LocalDate.now(), totalCost, post_cart_product_dtos);
        }
        APIService.apiService.addOrder(post_order_dto).enqueue(new Callback<POST_Order_DTO>() {
            @Override
            public void onResponse(Call<POST_Order_DTO> call, Response<POST_Order_DTO> response) {

            }

            @Override
            public void onFailure(Call<POST_Order_DTO> call, Throwable t) {

            }
        });
    }
}