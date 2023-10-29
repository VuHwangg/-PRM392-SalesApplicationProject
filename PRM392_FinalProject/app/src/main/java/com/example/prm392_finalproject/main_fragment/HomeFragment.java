package com.example.prm392_finalproject.main_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.DTOModels.Home_Product_DTO;
import com.example.prm392_finalproject.MainActivity;
import com.example.prm392_finalproject.Product;
import com.example.prm392_finalproject.Adapter.ProductAdapter;
import com.example.prm392_finalproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView revProduct;
    private ProductAdapter mProductAdapter;
    private View mView;
    private MainActivity mMainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        // Context của fragment
        mMainActivity = (MainActivity) getActivity();

        revProduct = mView.findViewById(R.id.rev_home);
        mProductAdapter = new ProductAdapter(mMainActivity, new ProductAdapter.IClickItemListener() {
            @Override
            public void onClickItemProduct(Home_Product_DTO product) {
                mMainActivity.goToDetailFragment(product);
            }
        });

        // Layout hiện thị là dạng grid mỗi hàng có 2 cột
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mMainActivity, 2);
        revProduct.setLayoutManager(gridLayoutManager);

//        mProductAdapter.setData(getListProduct());
//        revProduct.setAdapter(mProductAdapter);
        callAPIHomePage();
        return mView;
    }



    private List<Home_Product_DTO> callAPIHomePage() {
        boolean check = true;
        List<Home_Product_DTO> list = new ArrayList<>();
//        list.add(new Home_Product_DTO(1,"https://www.tsttourist.com/vnt_upload/news/07_2021/tsttourist-10-buc-anh-troi-dem-dep-nhat-the-gioi-1.jpeg", "Iphone1", 123456,1));
//        list.add(new Home_Product_DTO(1,"https://www.tsttourist.com/vnt_upload/news/07_2021/tsttourist-10-buc-anh-troi-dem-dep-nhat-the-gioi-1.jpeg", "Iphone1", 123456,1));
//        list.add(new Home_Product_DTO(1,"https://www.tsttourist.com/vnt_upload/news/07_2021/tsttourist-10-buc-anh-troi-dem-dep-nhat-the-gioi-1.jpeg", "Iphone1", 123456,1));
        APIService.apiService.listProductHomePage().enqueue(new Callback<ArrayList<Home_Product_DTO>>() {
            @Override
            public void onResponse(Call<ArrayList<Home_Product_DTO>> call, Response<ArrayList<Home_Product_DTO>> response) {
                List<Home_Product_DTO> list = response.body();
                mProductAdapter.setData(list);
                revProduct.setAdapter(mProductAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Home_Product_DTO>> call, Throwable t) {
                Toast.makeText(mMainActivity, "Call API failure", Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }

}
