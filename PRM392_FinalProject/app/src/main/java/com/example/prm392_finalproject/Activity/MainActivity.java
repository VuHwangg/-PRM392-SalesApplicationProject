package com.example.prm392_finalproject.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.API.APIService;
import com.example.prm392_finalproject.Adapter.ProductAdapter;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.DTOModels.Home_Product_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Session.UserDataManager;
import com.example.prm392_finalproject.Singleton.CartSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private RecyclerView revProduct;
    private ProductAdapter mProductAdapter;
    private BottomNavigationView mBottomNavigationView;
    private boolean notificationOn = true;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        User_Login_DTO_Response user = new User_Login_DTO_Response(1, "vuhoang", "Hoang Chu ANh Vu", "Lang Son", "08123123");
//        UserDataManager.setUserJsonString(user);
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

//        Send notification
        sendCartNotification();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.getItem(1);
        if(UserDataManager.getNotify())
            item.setIcon(R.drawable.ic_notification);
        else
            item.setIcon(R.drawable.ic_notification_off);
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
            if (UserDataManager.getNotify()) {
                item.setIcon(R.drawable.ic_notification_off);
            } else {
                item.setIcon(R.drawable.ic_notification);
            }
            UserDataManager.setNotify(!UserDataManager.getNotify());
        } else if (id == R.id.menu_option1) {
            Toast.makeText(this, "Menu option 1 selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_option2) {
            Toast.makeText(this, "Menu option 2 selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), UserRegisterActivity.class);
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

    private void sendPushNotification(String content)
    {   String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),channelID);
        builder.setSmallIcon(R.drawable.ic_shopping_cart)
                .setContentTitle("Thong bao")
                .setContentText(content)
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

    private void directToLogin(){
        Intent intent = new Intent(getApplicationContext(),UserLoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Cau hinh bottom navigation
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setSelectedItemId(R.id.bottom_home);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                } else if (id == R.id.bottom_cart) {
                    if(UserDataManager.getUserPreference() == null){
                        directToLogin();
                    }else {
                        Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                        startActivity(intent);
                    }
                } else if (id == R.id.bottom_order) {
                    if(UserDataManager.getUserPreference() == null){
                        directToLogin();
                    }else {
                        Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                        startActivity(intent);
                    }
                } else if (id == R.id.bottom_chat) {
                    if(UserDataManager.getUserPreference() == null){
                        directToLogin();
                    }else {
                        Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                        startActivity(intent);
                    }
                } else if (id == R.id.bottom_account) {
                    if(UserDataManager.getUserPreference() == null){
                        directToLogin();
                    }else {
                        Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
    }


    public void sendCartNotification(){
        if(UserDataManager.getNotify() && UserDataManager.getUserPreference() != null){
            //call api lay number of product
            getCartSize();
        }
    }
    public void getCartSize(){
        //thay thang 1 bang thang userID
        APIService.apiService.listCart(UserDataManager.getUserPreference().getId()).enqueue(new Callback<ArrayList<Cart_Product_DTO>>() {
            @Override
            public void onResponse(Call<ArrayList<Cart_Product_DTO>> call, Response<ArrayList<Cart_Product_DTO>> response) {
                List<Cart_Product_DTO> list = response.body();
                int productNumber = list.size();
                if(productNumber > 0)
                    sendPushNotification("Gio hang cua ban co "+productNumber+" san pham chua thanh toan!");
                else
                    sendPushNotification("Ban khong co san pham nao trong gio hang. Mua ngay!");
            }

            @Override
            public void onFailure(Call<ArrayList<Cart_Product_DTO>> call, Throwable t) {

            }
        });
    }

    // Format 100000000 -> 100,000,000
    public static String formattedPrice (int price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }
    public static String formattedPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }
}