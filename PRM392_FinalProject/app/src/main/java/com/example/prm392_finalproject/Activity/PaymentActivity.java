package com.example.prm392_finalproject.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.example.prm392_finalproject.DTOModels.POST_Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.POST_Order_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;
import com.example.prm392_finalproject.VNPAY.VNP_SdkCompletedCallback;
import com.example.prm392_finalproject.VNPAY.VNPay;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentActivity extends AppCompatActivity {

    TextView paymentCost, btnBack;
    EditText phone,address;
    Button payment;
    double totalCost = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btnBack = findViewById(R.id.btn_back);
        paymentCost=findViewById(R.id.tv_payment_cost);
        payment = findViewById(R.id.btn_payment_zalo);
        phone = findViewById(R.id.edt_phone_num);
        address = findViewById(R.id.edt_address);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            totalCost = (double) bundle.get("totalPrice");
        }
//        paymentCost.setText(Double.toString(totalCost) + " VNĐ");

        DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
        String formattedCost = decimalFormat.format(totalCost);
        paymentCost.setText(formattedCost);
        payment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(phone.getText().length()==0 && address.getText().length()==0){
                    Toast.makeText(PaymentActivity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        openSdk();
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
//                    addOrder();
                    Log.d("dấdasd","sdadada");
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Ẩn action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

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
    public void openSdk() throws UnsupportedEncodingException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        String vnp_TxnRef = getRandomNumber(8);
        String vnp_IpAddr = "1.1";
        String vnp_TmnCode = "XLTLXNQA";
        int amount = (int) ( totalCost* 100);
        Map vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        String bank_code = null;
        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Payment orders:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = null;
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "en");
        }
        vnp_Params.put("vnp_ReturnUrl", "https://localhost/́8080");
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = hmacSHA512("PWSEKDUNJALKZTUSCCRISFQJLUTZECNZ", hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html" + "?" + queryUrl;
        Intent intent = new Intent(getApplicationContext(), VNPay.class);
        intent.putExtra("url", paymentUrl); //bắt buộc, VNPAY cung cấp
        intent.putExtra("tmn_code", "DE7NBVFA"); //bắt buộc, VNPAY cung cấp
        intent.putExtra("scheme", "MainActivity"); //bắt buộc, scheme để mở lại app khi có kết quả thanh toán từ mobile banking
        intent.putExtra("is_sandbox", true); //bắt buộc, true <=> môi trường test, true <=> môi trường live
        VNPay.setSdkCompletedCallback(new VNP_SdkCompletedCallback() {
            @Override
            public void sdkAction(String action) {
                Log.wtf("SplashActivity", "action: " + action);
                if (action.equals("FaildBackAction")) {
                    Toast.makeText(getApplicationContext(), "Payment transaction failed", Toast.LENGTH_SHORT).show();
                }
                if (action.equals("SuccessBackAction")) {
                    Toast.makeText(getApplicationContext(), "Payment success", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent1);
                }

            }
        });
        startActivity(intent);
    }
    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }
    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}