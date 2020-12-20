package com.example.oving_2_opg_1;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivityForResult(new Intent("no.hist.itfag.RandomTallA"), request_Code);
        Intent intent = new Intent("com.example.oving_2_opg_1.RandomTall");
        intent.putExtra("upper_limit", 100);
        startActivityForResult(intent, request_Code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == request_Code && resultCode == RESULT_OK) {
            int random = data.getIntExtra("random_int", -1); //Returns -1 if an error occurred
            Toast.makeText(this, Integer.toString(random), Toast.LENGTH_LONG).show();
        }
    }
}
