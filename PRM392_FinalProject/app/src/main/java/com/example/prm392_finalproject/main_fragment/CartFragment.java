package com.example.prm392_finalproject.main_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.Adapter.CartAdapter;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.Activity.MainActivity;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;

import java.util.List;

public class CartFragment extends Fragment {
    public static final String TAG = CartFragment.class.getName();
    private RecyclerView revProduct;
    private CartAdapter mCartAdapter;
    private MainActivity mMainActivity;
    private View mView;
    private TextView tvCost;

    private Button btnCheckout;

    @SuppressLint("SuspiciousIndentation")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart, container, false);

        btnCheckout = mView.findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.goToPaymentFragment();
            }
        });

        // Context của fragment
        mMainActivity = (MainActivity) getActivity();
        revProduct = mView.findViewById(R.id.rev_cart);
        tvCost = mView.findViewById(R.id.tv_cart_cost);
        mCartAdapter = new CartAdapter(getActivity(),tvCost);
        // Layout hiện thị là dạng liner
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        revProduct.setLayoutManager(linearLayoutManager);
        CartSingleton cartSingleton = CartSingleton.getInstance();
        mCartAdapter.setData(cartSingleton.getCart());
        revProduct.setAdapter(mCartAdapter);
        double totalCost = 0;
        for(Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCart()){
            totalCost += cart_product_dto.getPrice()*cart_product_dto.getQuantity();
        }
        tvCost.setText(String.valueOf((int) totalCost));
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Ẩn BottomNavigationView
        List<Cart_Product_DTO>cart = CartSingleton.getInstance().getCart();
        mCartAdapter.setData(cart);
        double totalCost = 0;
        for(Cart_Product_DTO cart_product_dto : cart){
            totalCost += cart_product_dto.getPrice()*cart_product_dto.getQuantity();
        }
        tvCost.setText(String.valueOf((int) totalCost));
    }
}
