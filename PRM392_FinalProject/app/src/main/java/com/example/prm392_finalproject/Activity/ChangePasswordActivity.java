package com.example.prm392_finalproject.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.DTOModels.Order_DTO;
import com.example.prm392_finalproject.DTOModels.User_ChangePassword_DTO;
import com.example.prm392_finalproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText edtOldPassword, edtNewPassword, edtRePassword;
    private TextView btnBack;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtOldPassword = findViewById(R.id.edt_old_password);
        edtNewPassword = findViewById(R.id.edt_new_password);
        edtRePassword = findViewById(R.id.edt_re_new_password);
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
                ChangePassword();
            }
        });

        // Ẩn action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void ChangePassword(){
        User_ChangePassword_DTO user = new User_ChangePassword_DTO();
        //can them userID
        user.setUserID(1);
        user.setPassword(String.valueOf(edtOldPassword.getText()));

        APIService.apiService.confirmPassword(user).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                if(!response.body())
                    Toast.makeText(getApplicationContext(), "Old password is not valid", Toast.LENGTH_SHORT).show();
                else
                {
                    String newPass = String.valueOf(edtNewPassword.getText());
                    String rePass = String.valueOf(edtRePassword.getText());
                    if(newPass.length() == 0 || newPass == null) return;
                    if(!newPass.equals(rePass))
                        Toast.makeText(getApplicationContext(), "Re-password must be the same with new password", Toast.LENGTH_SHORT).show();
                    else
                    {
                        user.setPassword(newPass);
                        UpdatePassword(user);
                    }
                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void UpdatePassword(User_ChangePassword_DTO user){
        APIService.apiService.changePassword(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Change password successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Change password failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}