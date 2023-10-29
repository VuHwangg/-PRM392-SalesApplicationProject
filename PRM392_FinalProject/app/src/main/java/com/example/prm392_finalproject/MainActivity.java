package com.example.prm392_finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.prm392_finalproject.Adapter.MyViewPagerAdapter;
import com.example.prm392_finalproject.DTOModels.Home_Product_DTO;
import com.example.prm392_finalproject.main_fragment.ProductDetailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager2 = findViewById(R.id.view_pager_2);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
        mViewPager2.setAdapter(myViewPagerAdapter);

        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                    mViewPager2.setCurrentItem(0);
                } else if (id == R.id.bottom_cart) {
                    mViewPager2.setCurrentItem(1);
                } else if (id == R.id.bottom_order) {
                    mViewPager2.setCurrentItem(2);
                } else if (id == R.id.bottom_chat) {
                    mViewPager2.setCurrentItem(3);
                } else if (id == R.id.bottom_account) {
                    mViewPager2.setCurrentItem(4);
                }
                return true;
            }
        });

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_cart).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_order).setChecked(true);
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_chat).setChecked(true);
                        break;
                    case 4:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_account).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_search) {
            Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_notification) {
            Toast.makeText(this, "Notification selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_option1) {
            Toast.makeText(this, "Menu option 1 selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_option2) {
            Toast.makeText(this, "Menu option 2 selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), UserRegisterActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_option3) {
            Toast.makeText(this, "Menu option 3 selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), EmployeeLoginActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void goToDetailFragment(Home_Product_DTO product) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Tương tự như intent dùng để gửi dữ liêu giữa các Fragment
        ProductDetailFragment detailFragment = new ProductDetailFragment();

        Bundle mybundle =new Bundle();
        mybundle.putSerializable("product_id", product.getId());

        detailFragment.setArguments(mybundle);

        fragmentTransaction.replace(R.id.main_activity, detailFragment);
        fragmentTransaction.addToBackStack(ProductDetailFragment.TAG);
        fragmentTransaction.commit();
    }
}