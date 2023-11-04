package com.example.prm392_finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.prm392_finalproject.Adapter.OrderAdapter;
import com.example.prm392_finalproject.Order;
import com.example.prm392_finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView revOrder;
    private OrderAdapter mOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        revOrder = findViewById(R.id.rev_order);

        mOrderAdapter = new OrderAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        revOrder.setLayoutManager(linearLayoutManager);

        mOrderAdapter.setData(getListOrder());
        revOrder.setAdapter(mOrderAdapter);
    }
    private List<Order> getListOrder() {
        List<Order> list = new ArrayList<>();
        list.add(new Order(1, 9210000, "Vũ Hoàng", "0833232520", "Khu 1 - Phai Dài - Thất Khê - Tràng Định - Lạng Sơn", 0));
        list.add(new Order(2, 200000, "Vũ Đẹp Zai", "012345678", "Phai Dài - Thất Khê - Tràng Định - Lạng Sơn", 1));
        list.add(new Order(3, 999999999, "Hoàng Chu Anh Vũ", "0833232520", "Thất Khê - Tràng Định - Lạng Sơn", 2));
        list.add(new Order(4, 1234567, "Người yêu của Vũ", "012345678", "Tràng Định - Lạng Sơn", 3));
        list.add(new Order(5, 20000, "Duy ngu", "0999999999", "Lạng Sơn", 4));
        return list;
    }
}