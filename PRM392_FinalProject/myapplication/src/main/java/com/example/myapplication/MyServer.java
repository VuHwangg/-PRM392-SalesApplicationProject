package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends AsyncTask<Void, String, Void> {

    private ServerSocket serverSocket;

    @Override
    protected Void doInBackground(Void... params) {
        try {
            serverSocket = new ServerSocket(9999);
            publishProgress("start server!");

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                publishProgress("New connection!");

                // Loop for sending and receiving data
                while (true) {
                    // Read data from the client
                    String line = is.readLine();

                    // Handle received data (in this example, just log it)
                    publishProgress("Received: " + line);

                    // Send a response back to the client
                    os.write("Server response: " + line);
                    os.newLine();
                    os.flush();

                    if (line.equals("QUIT")) {
                        os.write(">> OK");
                        os.newLine();
                        os.flush();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        // Update UI or log the progress message
        Log.d("Server", values[0]);
    }
}
