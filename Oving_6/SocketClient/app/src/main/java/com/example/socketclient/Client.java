package com.example.socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import android.util.Log;

public class Client extends Thread {
    private final static String TAG = "Client_log";
    private final static String IP = "10.0.2.2";
    private final static int PORT = 12345;
    private int number1;
    private int number2;
    private ClientActivity clientActivity;

    // Constructor to get the needed arguments
    public Client(int number1, int number2, ClientActivity clientActivity){
        this.number1 = number1;
        this.number2 = number2;
        this.clientActivity = clientActivity;
    }

    public void run() {
        Log.i(TAG, "Client thread started");
        Socket s 			= null;
        PrintWriter out		= null;
        BufferedReader in 	= null;

        try {
            s = new Socket(IP, PORT);
            Log.v(TAG, "C: Connected to server" + s.toString());
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Test-ping
            String res = in.readLine();
            Log.i(TAG,res);
            out.println("PING to server from client");

            out.println(number1);
            out.println(number2);

            sendResultBack(in.readLine());
        }
        catch (IOException e) {
            Log.i(TAG, "IOException");
            Log.i(TAG, e.getMessage());
        }
        // Close socket
        finally{
            try{
                Log.i(TAG, "Closing socket...");
                if(out != null)  out.close();
                if(in != null)  in.close();
                if(s != null) s.close();
            }
            catch(IOException e){}
        }
    }

    private void sendResultBack(String result){
        clientActivity.setResult(result);
    }
}