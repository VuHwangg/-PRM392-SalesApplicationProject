package com.example.prm392_finalproject.main_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Product_Detail_DTO;
import com.example.prm392_finalproject.MainActivity;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {

    public static final String TAG = ProductDetailFragment.class.getName();
    private ImageView image;
    private TextView tvName, tvDes, btnBack;
    private TextView tvPrice, tvDiscount;
    private Button btn_addtocart, btn_loadmore;
    private View mView;
    private BottomNavigationView bottomNavigationView;
    private boolean isExpanded = false;
    private Product_Detail_DTO product;
    private MainActivity mMainActivity;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        // Context của fragment
        mMainActivity = (MainActivity) getActivity();
        image = mView.findViewById(R.id.img_productdetail);
        tvDiscount = mView.findViewById(R.id.tv_discount_productdetail);
        tvName = mView.findViewById(R.id.tv_name_productdetail);
        tvDes = mView.findViewById(R.id.tv_description_productdetail);
        tvPrice = mView.findViewById(R.id.tv_price_productdetail);
        btnBack = mView.findViewById(R.id.btn_back);
        btn_addtocart = mView.findViewById(R.id.btn_addtocart);
        btn_loadmore = mView.findViewById(R.id.btn_loadmore);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        Bundle myBundle = getArguments();
        if (myBundle != null) {
            int id = (int) myBundle.get("product_id");
            callAPIProductDetail(id);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
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
                boolean check =false;
                CartSingleton cartSingleton = CartSingleton.getInstance();
                for(Cart_Product_DTO cart_product_dto : cartSingleton.getCart()){
                    if(cart_product_dto.getId() == product.getId()){
                        cart_product_dto.setQuantity(cart_product_dto.getQuantity()+1);
                        check =true;
                        Log.d("b","b1");
                        Toast.makeText(mMainActivity, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                if (check == false){
                    cartSingleton.getCart().add(new Cart_Product_DTO(product.getId(),product.getImage(),product.getName(),product.getPrice(),1));
                    Log.d("b","a1");
                    Toast.makeText(mMainActivity, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isExpanded = !isExpanded;
                if (isExpanded) {
                    tvDes.setMaxLines(Integer.MAX_VALUE);
                    btn_loadmore.setText("Thu gọn...");
                } else {
                    tvDes.setMaxLines(3);
                    btn_loadmore.setText("Xem thêm...");
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

    private void callAPIProductDetail(int id) {
        APIService.apiService.getProductDetail(id).enqueue(new Callback<Product_Detail_DTO>() {
            @Override
            public void onResponse(Call<Product_Detail_DTO> call, Response<Product_Detail_DTO> response) {
                product = response.body();
                if (product != null) {
                    Glide.with(ProductDetailFragment.this)
                            .load(product.getImage())
                            .centerCrop()
                            .into(image);
                    tvName.setText(product.getName());
                    tvDes.setText(product.getDescription());
                    tvPrice.setText((int) product.getPrice() + "VNĐ");
                    tvDiscount.setText("Giảm giá " + (double) product.getDiscount() + "%");
                }
            }

            @Override
            public void onFailure(Call<Product_Detail_DTO> call, Throwable t) {
                Toast.makeText(mMainActivity, "Call API failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

}