package com.example.prm392_finalproject.main_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.Product;
import com.example.prm392_finalproject.ProductAdapter;
import com.example.prm392_finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView revProduct;
    private ProductAdapter mProductAdapter;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        revProduct = mView.findViewById(R.id.rev_home);
        mProductAdapter = new ProductAdapter(getActivity());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        revProduct.setLayoutManager(gridLayoutManager);

        mProductAdapter.setData(getListProduct());
        revProduct.setAdapter(mProductAdapter);

        return mView;
    }


    private List<Product> getListProduct() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        list.add(new Product(R.drawable.iphone15, "Iphone15", 25000000));
        return list;
    }


}
