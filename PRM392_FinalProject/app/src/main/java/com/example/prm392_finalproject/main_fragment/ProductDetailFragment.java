package com.example.prm392_finalproject.main_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prm392_finalproject.Product;
import com.example.prm392_finalproject.R;

public class ProductDetailFragment extends Fragment {

    public static final String TAG = ProductDetailFragment.class.getName();
    private TextView tvName;
    private Button btn_back;
    private View mView;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        tvName = mView.findViewById(R.id.tv_name_product);
        btn_back = mView.findViewById(R.id.btn_back);

        Bundle myBundle = getArguments();
        if (myBundle != null) {
            Product product = (Product) myBundle.get("object_product");
            if (product != null) {
                tvName.setText(product.getName());
            }
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        return mView;
    }
}