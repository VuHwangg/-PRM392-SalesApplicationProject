package com.example.prm392_finalproject.main_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prm392_finalproject.DTOModels.Home_Product_DTO;
import com.example.prm392_finalproject.Product;
import com.example.prm392_finalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductDetailFragment extends Fragment {

    public static final String TAG = ProductDetailFragment.class.getName();
    private ImageView image;
    private TextView tvName, tvDes, btn_back;
    private TextView tvPrice, tvDiscount;
    private Button btn_addtocart, btn_loadmore;
    private View mView;
    private BottomNavigationView bottomNavigationView;
    private boolean isExpanded = false;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        image = mView.findViewById(R.id.img_productdetail);
        tvDiscount = mView.findViewById(R.id.tv_discount_productdetail);
        tvName = mView.findViewById(R.id.tv_name_productdetail);
        tvDes = mView.findViewById(R.id.tv_description_productdetail);
        tvPrice = mView.findViewById(R.id.tv_price_productdetail);
        btn_back = mView.findViewById(R.id.btn_back);
        btn_addtocart = mView.findViewById(R.id.btn_addtocart);
        btn_loadmore = mView.findViewById(R.id.btn_loadmore);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        Bundle myBundle = getArguments();
        if (myBundle != null) {
            Home_Product_DTO product = (Home_Product_DTO) myBundle.get("object_product");
            if (product != null) {
                Glide.with(ProductDetailFragment.this)
                        .load(product.getImage())
                        .centerCrop()
                        .into(image);
                tvName.setText(product.getName());
//                tvDes.setText(product.getDescription());
                tvPrice.setText((int) product.getPrice() + "VNĐ");
                tvDiscount.setText("Giảm giá " + (double) product.getDiscount() + "%");
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

        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Add to cart successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btn_loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isExpanded = !isExpanded;
                if (isExpanded) {
                    tvDes.setMaxLines(Integer.MAX_VALUE);
                    btn_loadmore.setText("Thu gọn");
                } else {
                    tvDes.setMaxLines(3);
                    btn_loadmore.setText("Xem thêm");
                }
            }
        });



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

}