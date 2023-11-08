package com.example.prm392_finalproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.DTOModels.User_UpdateInformation_DTO;
import com.example.prm392_finalproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeAddressActivity extends AppCompatActivity {

    private EditText edtName, edtPhoneNum, edtAddress;
    private TextView btnBack;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);

        edtName = findViewById(R.id.edt_name_changeaddress);
        edtPhoneNum = findViewById(R.id.edt_phone_num_changeaddress);
        edtAddress = findViewById(R.id.edt_address_changeaddress);
        btnBack = findViewById(R.id.btn_back);
        btnUpdate = findViewById(R.id.btn_update);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UpdateAccountInformation();
            }
        });


        // Ẩn action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void UpdateAccountInformation(){
        User_UpdateInformation_DTO user = new User_UpdateInformation_DTO();
        //can them userid
        user.setUserID(1);
        user.setNewName(String.valueOf(edtName.getText()));
        user.setNewPhone(String.valueOf(edtPhoneNum.getText()));
        user.setNewAddress(String.valueOf(edtAddress.getText()));

        APIService.apiService.updateAccountInformation(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Update information successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Update information failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}