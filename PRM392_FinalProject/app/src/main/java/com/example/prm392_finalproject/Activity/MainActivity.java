package com.example.prm392_finalproject.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.Adapter.ProductAdapter;
import com.example.prm392_finalproject.DTOModels.Home_Product_DTO;
import com.example.prm392_finalproject.EmployeeLoginActivity;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.UserLoginActivity;
import com.example.prm392_finalproject.UserRegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView revProduct;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        APIServiceTest.apiService.listCart().enqueue(new Callback<ArrayList<Cart_Product_DTO>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Cart_Product_DTO>> call, Response<ArrayList<Cart_Product_DTO>> response) {
//                CartSingleton.getInstance().setProductList(response.body());
//                if (!CartSingleton.getInstance().getCart().isEmpty()){
//                    sendPushNotification(CartSingleton.getInstance().getCart().size());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Cart_Product_DTO>> call, Throwable t) {
//
//            }
//        });
        revProduct = findViewById(R.id.rev_home);
        mProductAdapter = new ProductAdapter(MainActivity.this, new ProductAdapter.IClickItemListener() {
            // Định nghĩa interface onClickItemProduct
            @Override
            public void onClickItemProduct(Home_Product_DTO product) {
                goToProductDetail(product);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        revProduct.setLayoutManager(gridLayoutManager);
        callAPIHomePage();

        // Cau hinh bottom navigation
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setSelectedItemId(R.id.bottom_home);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                } else if (id == R.id.bottom_cart) {
                    Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_order) {
                    Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_chat) {
                    Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_account) {
                    Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
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
        if (id == R.id.menu_map) {
            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intent);
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

    public void goToProductDetail(Home_Product_DTO product) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product_id", product.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void sendPushNotification(int cartSize)
    {   String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),channelID);
        builder.setSmallIcon(R.drawable.ic_shopping_cart)
                .setContentTitle("Thong bao")
                .setContentText("Gio hang cua ban co "+cartSize+" san pham chua thanh toan!")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent intent = new Intent(getApplicationContext(),CartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
            if(notificationChannel==null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID,"some",importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(0,builder.build());
    }
    private List<Home_Product_DTO> callAPIHomePage() {
        APIService.apiService.listProductHomePage().enqueue(new Callback<ArrayList<Home_Product_DTO>>() {
            @Override
            public void onResponse(Call<ArrayList<Home_Product_DTO>> call, Response<ArrayList<Home_Product_DTO>> response) {
                List<Home_Product_DTO> list = response.body();
                mProductAdapter.setData(list);
                revProduct.setAdapter(mProductAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Home_Product_DTO>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API failure", Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }
}