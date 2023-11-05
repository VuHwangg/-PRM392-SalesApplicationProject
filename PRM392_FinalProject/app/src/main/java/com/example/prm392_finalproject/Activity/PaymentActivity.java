package com.example.prm392_finalproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_finalproject.R;

public class PaymentActivity extends AppCompatActivity {

    TextView paymentCost;
    EditText phone,address;
    Button payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        double totalCost = 0;
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
            @Override
            public void onClick(View view) {
                if(phone.getText().length()==0 && address.getText().length()==0){
                    Toast.makeText(PaymentActivity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Ẩn action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}