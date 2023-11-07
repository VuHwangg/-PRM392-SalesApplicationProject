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

import com.example.prm392_finalproject.DTOModels.CreateOrder;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;

import org.json.JSONObject;

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
//
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
}