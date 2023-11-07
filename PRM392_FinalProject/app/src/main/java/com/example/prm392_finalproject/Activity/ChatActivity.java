package com.example.prm392_finalproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.Adapter.ChatAdapter;
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
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button button;
    EditText editMess;
    ChatAdapter adapter;
    List<Message_DTO> list;
    final String serverHost = "192.168.1.52";
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

    }

    private void connectServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Gửi yêu cầu kết nối tới Server đang lắng nghe
                    // trên máy 'localhost' cổng 9999.
                    socketOfClient = new Socket(serverHost, 9999);

                    // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
                    os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

                    // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
                    is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

                } catch (UnknownHostException e) {
                    System.err.println("Don't know about host " + serverHost);
                    return;
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " + serverHost+"\n" + e.getMessage());
                    return;
                }

                ///
                try {
                    // Đọc dữ liệu trả lời từ phía server
                    // Bằng cách đọc luồng đầu vào của Socket tại Client.
                    String responseLine;
                    while ((responseLine = is.readLine()) != null) {
                        final String res = responseLine;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //edtInput.setText(edtInput.getText() + "\n" + res);

                                Gson gson = new Gson();
                                Message_DTO mess = gson.fromJson(res, Message_DTO.class);
                                list.add(mess);
                            }
                        });
                        if (responseLine.contains("QUIT")) {
                            break;
                        }
                    }

                    os.close();
                    is.close();
                    socketOfClient.close();
                } catch (UnknownHostException e) {
                    System.err.println("Trying to connect to unknown host: " + e);
                } catch (IOException e) {
                    System.err.println("IOException:  " + e);
                }
            }
        }).start();
    }


    private void initview() {
        list = new ArrayList<>();
       // adapter.notifyItemRangeInserted(list.size(),li);
        recyclerView = findViewById(R.id.recyclerview_chat);
        editMess = findViewById(R.id.editinputtex);
        button = findViewById(R.id.chat1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String text = editMess.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            String mess = gson.toJson(new Message_DTO("1","2",text, "11/06/2023"));
                            os.write(text); // Ghi dữ liệu vào luồng đầu ra của Socket tại Client.
                            os.newLine(); // kết thúc dòng
                            os.flush(); // đẩy dữ liệu đi.
                            list.add(new Message_DTO("1","2",text, "11/06/2023"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }

    private void sendMessToFire() {
        String str_mess = editMess.getText().toString().trim();
//        HashMap<String,>
    }


}