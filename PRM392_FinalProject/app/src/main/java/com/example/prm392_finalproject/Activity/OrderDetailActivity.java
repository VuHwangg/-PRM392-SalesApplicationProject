package com.example.prm392_finalproject.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.API.APIServiceTest;
import com.example.prm392_finalproject.Adapter.OrderDetailAdapter;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Order_DTO;
import com.example.prm392_finalproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView tvID, tvStatus, tvName, tvPhonenum, tvAddress, tvCost;
    private TextView btnBack;
    private Button btnCancel;
    private RecyclerView revProductOrderDetail;
    private OrderDetailAdapter mOrderDetailAdapter;

    private Order_DTO order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        tvID = findViewById(R.id.tv_orderdetail_id);
        tvStatus = findViewById(R.id.tv_orderdetail_status);
        tvName = findViewById(R.id.tv_orderdetail_username);
        tvPhonenum = findViewById(R.id.tv_orderdetail_phonenum);
        tvAddress = findViewById(R.id.tv_orderdetail_address);
        tvCost = findViewById(R.id.tv_orderdetail_cost);

        revProductOrderDetail = findViewById(R.id.rev_orderdetail_product);

        btnBack = findViewById(R.id.btn_back);
        btnCancel = findViewById(R.id.btn_cancel);
        Bundle myBundle = getIntent().getExtras();
        if (myBundle != null) {
            Order_DTO order = (Order_DTO) myBundle.get("object_order");
            if (order != null) {
                tvID.setText(order.getId() + "");

                if (order.getStatus() == 0) {
                    tvStatus.setText("Đã đặt hàng");
                    tvStatus.setTextColor(Color.parseColor("#80000000"));
                } else if (order.getStatus() == 1) {
                    tvStatus.setText("Đã xác nhận");
                    tvStatus.setTextColor(Color.parseColor("#9C27B0"));
                } else if (order.getStatus() == 2) {
                    tvStatus.setText("Đang giao hàng");
                    tvStatus.setTextColor(Color.parseColor("#3F51B5"));
                } else if (order.getStatus() == 3) {
                    tvStatus.setText("Đã giao hàng");
                    tvStatus.setTextColor(Color.parseColor("#369C3A"));
                } else if (order.getStatus() == 4) {
                    tvStatus.setText("Đơn hàng bị hủy");
                    tvStatus.setTextColor(Color.parseColor("#CC3125"));
                }

                tvName.setText(order.getCustomerName());
                tvPhonenum.setText(order.getPhone());
                tvAddress.setText(order.getAddress());
                tvCost.setText(order.getPrice() + "");
            }
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderDetailActivity.this, "Hủy đơn hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
        mOrderDetailAdapter = new OrderDetailAdapter(OrderDetailActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderDetailActivity.this);
        revProductOrderDetail.setLayoutManager(linearLayoutManager);
        getOrderDetail();
    }

    private void getOrderDetail() {
        APIServiceTest.apiService.listOrderDetail().enqueue(new Callback<ArrayList<Cart_Product_DTO>>() {
            @Override
            public void onResponse(Call<ArrayList<Cart_Product_DTO>> call, Response<ArrayList<Cart_Product_DTO>> response) {
                List<Cart_Product_DTO> list = response.body();
                mOrderDetailAdapter.setData(list);
                revProductOrderDetail.setAdapter(mOrderDetailAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<Cart_Product_DTO>> call, Throwable t) {

            }
        });
    }

}