package com.example.socketserver;

import android.app.Activity;
import android.os.Bundle;

public class ServerActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Server().start();//start server
    }
}