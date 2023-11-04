package com.example.prm392_finalproject.main_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.Adapter.OrderDetailAdapter;
import com.example.prm392_finalproject.MainActivity;
import com.example.prm392_finalproject.Order;
import com.example.prm392_finalproject.ProductOrder;
import com.example.prm392_finalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailFragment extends Fragment {
    public static final String TAG = OrderDetailFragment.class.getName();
    private View mView;
    private TextView tvID, tvStatus, tvName, tvPhonenum, tvAddress, tvCost;
    private TextView btnBack;
    private Button btnCancel;
    private BottomNavigationView bottomNavigationView;
    private MainActivity mMainActivity;

    private RecyclerView revProductOrderDetail;
    private OrderDetailAdapter mOrderDetailAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_order_detail, container, false);
        // Context của fragment
        mMainActivity = (MainActivity) getActivity();

        tvID = mView.findViewById(R.id.tv_orderdetail_id);
        tvStatus = mView.findViewById(R.id.tv_orderdetail_status);
        tvName = mView.findViewById(R.id.tv_orderdetail_username);
        tvPhonenum = mView.findViewById(R.id.tv_orderdetail_phonenum);
        tvAddress = mView.findViewById(R.id.tv_orderdetail_address);
        tvCost = mView.findViewById(R.id.tv_orderdetail_cost);

        revProductOrderDetail = mView.findViewById(R.id.rev_orderdetail_product);

        btnBack = mView.findViewById(R.id.btn_back);
        btnCancel = mView.findViewById(R.id.btn_cancel);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        Bundle myBundle = getArguments();
        if (myBundle != null) {
            Order order = (Order) myBundle.get("object_order");
            if (order != null) {
                tvID.setText(order.getOrderId() + "");

                if (order.getOrderStatus() == 0) {
                    tvStatus.setText("Đã đặt hàng");
                    tvStatus.setTextColor(Color.parseColor("#80000000"));
                } else if (order.getOrderStatus() == 1) {
                    tvStatus.setText("Đã xác nhận");
                    tvStatus.setTextColor(Color.parseColor("#9C27B0"));
                } else if (order.getOrderStatus() == 2) {
                    tvStatus.setText("Đang giao hàng");
                    tvStatus.setTextColor(Color.parseColor("#3F51B5"));
                } else if (order.getOrderStatus() == 3) {
                    tvStatus.setText("Đã giao hàng");
                    tvStatus.setTextColor(Color.parseColor("#369C3A"));
                } else if (order.getOrderStatus() == 4) {
                    tvStatus.setText("Đơn hàng bị hủy");
                    tvStatus.setTextColor(Color.parseColor("#CC3125"));
                }

                tvName.setText(order.getUsername());
                tvPhonenum.setText(order.getPhoneNum());
                tvAddress.setText(order.getAddress());
                tvCost.setText(order.getOrderCost() + "");
            }
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mMainActivity, "Hủy đơn hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });


        // Xu ly recycler view

        mOrderDetailAdapter = new OrderDetailAdapter(mMainActivity);

        // Layout hiện thị recycler view là dạng liner
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        revProductOrderDetail.setLayoutManager(linearLayoutManager);

        mOrderDetailAdapter.setData(getListProduct());
        revProductOrderDetail.setAdapter(mOrderDetailAdapter);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Ẩn BottomNavigationView
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Hiển thị BottomNavigationView lại sau khi Fragment dừng
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private List<ProductOrder> getListProduct() {
        List<ProductOrder> list = new ArrayList<>();
        list.add(new ProductOrder(R.drawable.iphone4, "Iphone1", 90000000, 1));
        list.add(new ProductOrder(R.drawable.iphone8, "Iphone2", 999, 2));
        list.add(new ProductOrder(R.drawable.iphone15, "Iphone3", 123456, 3));
        list.add(new ProductOrder(R.drawable.iphone4, "Iphone4", 12500000, 4));
        list.add(new ProductOrder(R.drawable.iphone8, "Iphone5", 90000000, 5));
        list.add(new ProductOrder(R.drawable.iphone15, "Iphone6", 90000000, 6));
        list.add(new ProductOrder(R.drawable.iphone4, "Iphone7", 90000000, 7));
        list.add(new ProductOrder(R.drawable.iphone8, "Iphone8", 90000000, 8));
        list.add(new ProductOrder(R.drawable.iphone15, "IphoneX", 90000000, 9));
        list.add(new ProductOrder(R.drawable.iphone4, "Iphon11", 90000000, 10));
        list.add(new ProductOrder(R.drawable.iphone8, "Iphone12", 90000000, 11));
        list.add(new ProductOrder(R.drawable.iphone15, "Iphone13", 90000000, 100));
        list.add(new ProductOrder(R.drawable.iphone4, "Iphone14", 90000000, 1000));
        list.add(new ProductOrder(R.drawable.iphone8, "Iphone15", 90000000, 12));
        return list;
    }

}