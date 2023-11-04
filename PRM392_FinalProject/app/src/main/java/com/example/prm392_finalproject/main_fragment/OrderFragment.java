package com.example.prm392_finalproject.main_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.Adapter.OrderAdapter;
import com.example.prm392_finalproject.Activity.MainActivity;
import com.example.prm392_finalproject.Order;
import com.example.prm392_finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private RecyclerView revOrder;
    private OrderAdapter mOrderAdapter;
    private MainActivity mMainActivity;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_order, container, false);

        // Context của fragment
        mMainActivity = (MainActivity) getActivity();
        revOrder = mView.findViewById(R.id.rev_order);

        mOrderAdapter = new OrderAdapter(mMainActivity, new OrderAdapter.IClickItemListener(){
            @Override
            public void onClickItemOrder(Order order) {
                mMainActivity.goToOrderDetailFragment(order);
            }
        });

        // Layout hiện thị là dạng liner
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        revOrder.setLayoutManager(linearLayoutManager);

        mOrderAdapter.setData(getListOrder());
        revOrder.setAdapter(mOrderAdapter);

        return mView;
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
