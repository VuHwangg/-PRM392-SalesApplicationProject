package com.example.prm392_finalproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_finalproject.API.APILoginService;
import com.example.prm392_finalproject.DTOModels.User_Login_DTO_Requset;
import com.example.prm392_finalproject.DTOModels.User_Login_DTO_Response;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Session.UserDataManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity {

    private EditText edt_username, edt_password;
    private Button btn_login;
    private TextView tv_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);



        // Ẩn action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        tv_login = findViewById(R.id.tv_login);

        btn_login.setOnClickListener(view -> {
            // TODO: Client Input Validation HCAVuu


            String username = edt_username.getText().toString().trim();
            String password = edt_password.getText().toString().trim();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(UserLoginActivity.this, "Username Password", Toast.LENGTH_SHORT).show();
            }
            if (!username.isEmpty() && !password.isEmpty()) {
                User_Login_DTO_Requset userLoginDtoRequset = new User_Login_DTO_Requset(username, password);
                APILoginService.apiLoginService.login(userLoginDtoRequset).enqueue(new Callback<User_Login_DTO_Response>() {
                    @Override
                    public void onResponse(Call<User_Login_DTO_Response> call, Response<User_Login_DTO_Response> response) {
                        User_Login_DTO_Response userLoginDtoResponse = response.body();
                        if (userLoginDtoResponse != null) {
                            UserDataManager.setUserJsonString(userLoginDtoResponse);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<User_Login_DTO_Response> call, Throwable t) {
                        Toast.makeText(UserLoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        tv_login.setOnClickListener(view ->{
            User_Login_DTO_Response userLoginDtoResponse = UserDataManager.getUserPreference();
            if (userLoginDtoResponse == null) {
                Toast.makeText(UserLoginActivity.this, "Qua den", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UserLoginActivity.this, userLoginDtoResponse.getName().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}