package com.example.prm392_finalproject.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.prm392_finalproject.main_fragment.AccountFragment;
import com.example.prm392_finalproject.main_fragment.CartFragment;
import com.example.prm392_finalproject.main_fragment.ChatFragment;
import com.example.prm392_finalproject.main_fragment.HomeFragment;
import com.example.prm392_finalproject.main_fragment.OrderFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {


    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CartFragment();
            case 2:
                return new OrderFragment();
            case 3:
                return new ChatFragment();
            case 4:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
