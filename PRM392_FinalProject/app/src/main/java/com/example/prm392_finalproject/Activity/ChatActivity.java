package com.example.prm392_finalproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.Adapter.ChatAdapter;
import com.example.prm392_finalproject.Adapter.MessageAdapter;
import com.example.prm392_finalproject.DTOModels.Message_DTO;
import com.example.prm392_finalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.type.DateTime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button button;
    EditText editMess;
    MessageAdapter adapter;
    List<Message_DTO> list = new ArrayList<>();
    final String serverHost = "10.0.2.15";
    private boolean aBoolean = true;
    Socket socketOfClient = null;
    BufferedWriter os = null;
    BufferedReader is = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        // Cau hinh bottom navigation
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setSelectedItemId(R.id.bottom_chat);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_cart) {
                    Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_order) {
                    Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.bottom_chat) {

                } else if (id == R.id.bottom_account) {
                    Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
        initview();
        connectServer();
        recyclerView = findViewById(R.id.recyclerview_chat);
        adapter = new MessageAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void connectServer() {
        aBoolean = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Kết nối đến máy chủ
                    socketOfClient = new Socket(serverHost, 9999);
                    os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
                    is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

                    while (aBoolean) {
                        final String receivedMessage = is.readLine();
                        if (receivedMessage != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Xử lý tin nhắn nhận được từ máy chủ (ví dụ: hiển thị lên RecyclerView)
                                    Gson gson = new Gson();
                                    Message_DTO receivedMessageDto = new Message_DTO("2","1", receivedMessage,"1");
                                    list.add(receivedMessageDto);

                                    adapter.notifyItemInserted(list.size() - 1);
                                    // Scroll tới vị trí mới nhất trong RecyclerView
                                    recyclerView.smoothScrollToPosition(list.size() - 1);
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    aBoolean = false;
                }
            }
        }).start();
    }


    private void initview() {
        editMess = findViewById(R.id.editinputtex);
        button = findViewById(R.id.chat1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = editMess.getText().toString();
                Message_DTO receivedMessageDto = new Message_DTO("1","2",text,"1");
                list.add(receivedMessageDto);
                adapter.notifyItemInserted(list.size() - 1);
                 int a=list.size();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Gửi dữ liệu văn bản tới máy chủ
                            if (os != null) {
                                Gson gson = new Gson();
                                String message = editMess.getText().toString();

                                os.write(message);
                                os.newLine();
                                os.flush();
                                editMess.getText().clear();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }




}