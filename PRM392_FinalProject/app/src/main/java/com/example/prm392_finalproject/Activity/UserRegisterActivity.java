package com.example.prm392_finalproject.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_finalproject.R;
@RequiresApi(api = Build.VERSION_CODES.O)
public class UserRegisterActivity extends AppCompatActivity {

    private EditText edt_username, edt_name, edt_password, edt_cf_password;
    private Button btn_register;
    private TextView btn_goto_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        btn_goto_login = findViewById(R.id.btn_goto_login);
        btn_register = findViewById(R.id.btn_register);

        edt_username = findViewById(R.id.edt_username);
        edt_name = findViewById(R.id.edt_name);
        edt_password = findViewById(R.id.edt_password);
        edt_cf_password = findViewById(R.id.edt_re_password);

        btn_goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt_username.getText().toString().trim();
                String name = edt_name.getText().toString().trim();
                String password = edt_password.getText().toString().trim();
                String cfPassword = edt_cf_password.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || name.isEmpty() || cfPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống bất kỳ ô nào", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                    finish();
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