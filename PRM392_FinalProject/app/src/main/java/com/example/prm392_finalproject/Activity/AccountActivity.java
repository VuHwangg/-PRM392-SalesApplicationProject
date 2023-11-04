package com.example.prm392_finalproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_finalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Cau hinh bottom navigation
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setSelectedItemId(R.id.bottom_account);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_cart) {
                    Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_order) {
                    Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_chat) {
                    Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_account) {
                }
                return true;
            }
        });
    }
}