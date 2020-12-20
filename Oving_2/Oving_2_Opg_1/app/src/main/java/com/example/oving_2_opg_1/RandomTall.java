package com.example.oving_2_opg_1;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class RandomTall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_tall);

        int upperLimit = getIntent().getIntExtra("upper_limit", -1);//-1 is default value

        if(upperLimit == -1){
            Log.e("Error", "Upper limit error");
        }
        else{
            int random = new Random().nextInt(upperLimit); //Generate the random integer
            //Toast.makeText(this, Integer.toString(random), Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("random_int", random);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
