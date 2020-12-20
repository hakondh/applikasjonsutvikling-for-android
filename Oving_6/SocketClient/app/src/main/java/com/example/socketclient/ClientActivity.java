package com.example.socketclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ClientActivity extends Activity {
    EditText number1;
    EditText number2;
    TextView resultView;
    private final String TAG = "Client_log";
    private String result = "";

    private Handler handler = new Handler();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "ClientActivity started...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the UI-components
        number1 = findViewById(R.id.number_1);
        number2 = findViewById(R.id.number_2);
        resultView = findViewById(R.id.result);

    }

    // Method called for adding the numbers
    public void add(View v){
        Log.i(TAG, "Button clicked");
        // Get the numbers, convert an empty string to 0
        int n1 = !number1.getText().toString().equals("") ? Integer.parseInt(number1.getText().toString()) : 0;
        int n2 = !number2.getText().toString().equals("") ? Integer.parseInt(number2.getText().toString()) : 0;

        new Client(n1, n2, this).start();
    }

    public void setResult(final String result){
        handler.post(new Thread(){
            @Override
            public void run(){
                resultView.setText(result);//Change UI view
            }
        });

    }
}
