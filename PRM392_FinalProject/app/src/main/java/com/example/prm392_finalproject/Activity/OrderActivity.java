package com.example.prm392_finalproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.Adapter.OrderAdapter;
import com.example.prm392_finalproject.DTOModels.Order_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Session.UserDataManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView revOrder;
    private OrderAdapter mOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        revOrder = findViewById(R.id.rev_order);

        mOrderAdapter = new OrderAdapter(OrderActivity.this, new OrderAdapter.IClickItemListener() {
            @Override
            public void onClickItemOrder(Order_DTO order) {
                onClicGoToOrderDetail(order);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        revOrder.setLayoutManager(linearLayoutManager);


        // Cau hinh bottom navigation
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setSelectedItemId(R.id.bottom_order);
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

                } else if (id == R.id.bottom_chat) {
                    Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_account) {
                    Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
    }

    // Cap nhat lai trang thai
    @Override
    protected void onResume() {
        super.onResume();
        getListOrder();
    }

//    private void getListOrder() {
//        APIServiceTest.apiService.listOrder().enqueue(new Callback<ArrayList<Order_DTO>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Order_DTO>> call, Response<ArrayList<Order_DTO>> response) {
//                List<Order_DTO> list = response.body();
//                mOrderAdapter.setData(list);
//                revOrder.setAdapter(mOrderAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Order_DTO>> call, Throwable t) {
//
//            }
//        });
//    }
    private void onClicGoToOrderDetail(Order_DTO order) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Order", order);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void getListOrder(){
    // Thay  listOrder(1) bang listOrder(userID)
        APIService.apiService.listOrder(UserDataManager.getUserPreference().getId()).enqueue(new Callback<ArrayList<Order_DTO>>() {
            @Override
            public void onResponse(Call<ArrayList<Order_DTO>> call, Response<ArrayList<Order_DTO>> response) {
                List<Order_DTO> list = response.body();
                mOrderAdapter.setData(list);
                revOrder.setAdapter(mOrderAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Order_DTO>> call, Throwable t) {

            }
        });
    }

}