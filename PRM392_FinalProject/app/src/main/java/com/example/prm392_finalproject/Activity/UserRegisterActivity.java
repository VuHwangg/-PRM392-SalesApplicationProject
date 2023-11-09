package com.example.prm392_finalproject.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_finalproject.R;
@RequiresApi(api = Build.VERSION_CODES.O)
public class UserRegisterActivity extends AppCompatActivity {

    private TextView btn_goto_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        btn_goto_login = findViewById(R.id.btn_goto_login);

        btn_goto_login.setOnClickListener(new View.OnClickListener() {
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
}