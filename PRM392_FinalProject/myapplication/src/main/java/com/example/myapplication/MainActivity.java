package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Message_DTO> messageList = new ArrayList<>();
    Button button;
    EditText editMess;
    MainAdapter adapter;
    final String serverHost = "192.168.1.52";
    final int serverport = 9999;
    private boolean isRunning = true;
    private   ClientHandler client;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerview_chat);
        adapter = new MainAdapter(messageList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        messageList.add(new Message_DTO("1", "2","Welcome to my store!" , "11/06/2023"));
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
        initview();
        startServer();

    }

    private void sendMessage(final String text) {

        final String a = text;
        sendMessageToClient(a);
        messageList.add(new Message_DTO("1", "2", text, "11/06/2023"));
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);

    }
    private void sendMessageToClient(final String text) {
        // Lặp qua tất cả các client đang kết nối và gửi dữ liệu tới từng client
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                        PrintWriter writer = new PrintWriter(client.clientSocket.getOutputStream(), true);
                        writer.println(text);

                } catch (IOException e) {
                    e.printStackTrace();
                    // Xử lý lỗi nếu có
                }
            }
        }).start();
    }
    private void sendHelloToClient(ClientHandler client) {
        try {
            PrintWriter writer = new PrintWriter(client.clientSocket.getOutputStream(), true);
            writer.println("Welcome to my store!");

        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu có
        }
    }
    private void startServer() {
        new Thread(new ServerThread()).start();
    }

    class ServerThread implements Runnable {
        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(9999);
                while (isRunning) {
                    Socket socket = serverSocket.accept();
                    Log.d("Server1232", "Client connected: " + socket.getInetAddress().toString());

                     client = new ClientHandler(socket);

                    sendHelloToClient(client);

                    Thread clientThread = new Thread(client);
                    clientThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader reader;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    if (message != null) {
                        final String finalMessage = message;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                messageList.add(new Message_DTO("2", "1", finalMessage, "11/06/2023"));
                                adapter.notifyItemInserted(messageList.size() - 1);
                                recyclerView.scrollToPosition(messageList.size() - 1);
                            }
                        });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initview() {
        editMess = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editMess.getText().toString();
                sendMessage(text); // Gửi tin nhắn từ client
                editMess.getText().clear();
            }
        });
    }



}