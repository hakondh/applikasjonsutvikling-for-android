package com.example.socketserver;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private final static String TAG = "ServerThread";
    private final static int PORT = 12345;

    public void run(){
        ServerSocket ss = null;
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try{
            Log.i(TAG, InetAddress.getLocalHost().getHostAddress());

            Log.i(TAG,"Starting server....");
            ss = new ServerSocket(PORT);
            Log.i(TAG,"Serversocket created, running on port 12345. Waiting for client....");

            // Run the server until we stop it manually
            while(true){
                // Listen for client connections
                s = ss.accept();
                Log.v(TAG, "Client connected...");

                // PrintWriter-object to use for writing to client
                out = new PrintWriter(s.getOutputStream(), true);

                // BufferedReader-object to use for for reading from client
                in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));

                // Send text to client
                out.println("Welcome client...");

                // Receive text from client. Blocks the server code until client writes!
                String res = in.readLine();
                Log.i(TAG,"Message from client: " + res);

                int number1 = Integer.parseInt(in.readLine());
                int number2 = Integer.parseInt(in.readLine());

                String logString = "Recieved numbers " + number1 + " and " + number2;
                Log.i(TAG, logString);

                logString = "Sending back the answer " + (number1 + number2);
                Log.i(TAG, logString);
                out.println(number1 + number2);
            }
        }
        catch (IOException e) {
            Log.i("ServerThread", "IOException");
            e.printStackTrace();
        }
        // Close sockets!
        finally{
            Log.i("ServerThread", "Closing socket...");
            try{
                out.close();
                in.close();
                s.close();
                ss.close();
            }
            catch(Exception e){}
        }
    }
}
