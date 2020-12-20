package com.example.oving_2_opg_2;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class RandomNumber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_random_tall);

        int upperLimit = getIntent().getIntExtra("upper_limit", -1);//-1 is default value

        if(upperLimit == -1){
            Log.e("Error", "Upper limit error");
        }
        else{
            int random1 = new Random().nextInt(upperLimit); //Generate the random integer
            int random2 = new Random().nextInt(upperLimit); //Generate the random integer
            //Toast.makeText(this, Integer.toString(random), Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("random_int1", random1);
            intent.putExtra("random_int2", random2);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
