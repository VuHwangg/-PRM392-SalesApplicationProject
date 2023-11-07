package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> lst;
    private  MainAdapter mainAdapter;
    private String serverIP = "10.0.2.15";
    private int serverPort = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverThread = new ServerThread();
        serverThread.startServer();

        recyclerView = findViewById(R.id.recyclerview_chat);
//        lst = GetList();
        mainAdapter = new MainAdapter(lst,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private List<String> GetList() {
        return null;
    }
    class ServerThread extends Thread implements Runnable{
        private boolean serverRunning;
        private ServerSocket serverSocket;
        private int count=0;
        public  void startServer(){
            serverRunning = true;
            start();
        }

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(serverPort);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                while (serverRunning){
                    Socket socket = serverSocket.accept();
                    count++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                    PrintWriter ouput_Server = new PrintWriter(socket.getOutputStream());
                    ouput_Server.write("Welcom to Server: " + count);
                    ouput_Server.flush();
                    socket.close();

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(!serverRunning){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        public  void  stopServer(){
            serverRunning = false;


//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    if(serverSocket!= null && !serverSocket.isClosed()){
//                        try {
//                            Log.d("123456", "run1: ");
//                            serverSocket.close();
//                            Log.d("123456", "run2: ");
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Log.d("123456", "run3: ");
//                                   tvStatus.setText("Server Stopped");
//                                    Log.d("123456", "run4: ");
//                                }
//                            });
//                        } catch (IOException e) {
//                            Log.d("123456", "run: ");
//
//                        }
//                    }
//                }
//            }).start();
        }
    }
    private  ServerThread serverThread;
}