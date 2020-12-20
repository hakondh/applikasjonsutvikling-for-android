package com.example.oving_2_opg_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView number1;
    TextView number2;
    EditText answer;
    EditText upperLimit;
    static final int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = (TextView) findViewById(R.id.number1);
        number2 = (TextView) findViewById(R.id.number2);
        answer = (EditText) findViewById(R.id.answer);
        upperLimit = (EditText) findViewById(R.id.upperLimit);

        setRandomNumbers();
    }

    public void setRandomNumbers(){
        Intent intent = new Intent("com.example.oving_2_opg_2.RandomNumber");
        intent.putExtra("upper_limit", Integer.parseInt(upperLimit.getText().toString()));
        startActivityForResult(intent, request_Code);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == request_Code && resultCode == RESULT_OK) {
            int random1 = data.getIntExtra("random_int1", -1); //Returns -1 if an error occurred
            int random2 = data.getIntExtra("random_int2", -1); //Returns -1 if an error occurred
            number1.setText("" + random1);
            number2.setText("" + random2);
        }
    }

    public void onClickAdder(View v) {
        try{
            int n1 = Integer.parseInt(number1.getText().toString());
            int n2 = Integer.parseInt(number2.getText().toString());
            int ans =Integer.parseInt(answer.getText().toString());
            int correctAns = n1 + n2;
            Log.i("valueCheck", "n1: " + n1 + ", n2: " + n2 + ", ans: " + ans + ", correctAns:" + correctAns);

            if(correctAns == ans){
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,  getString(R.string.wrong) + " " + correctAns, Toast.LENGTH_LONG).show();
            }
            setRandomNumbers();
        }
        catch(Exception e){
            Toast.makeText(this, "Noe gikk galt. Pass på å skriv inn et tall.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickMultiply(View v) {
        try{
            int n1 = Integer.parseInt(number1.getText().toString());
            int n2 = Integer.parseInt(number2.getText().toString());
            int ans =Integer.parseInt(answer.getText().toString());

            int correctAns = n1 * n2;
            Log.i("valueCheck", "n1: " + n1 + ", n2: " + n2 + ", ans: " + ans + ", correctAns:" + correctAns);

            if(correctAns == ans){
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, getString(R.string.wrong) + " " + correctAns, Toast.LENGTH_LONG).show();
            }
            setRandomNumbers();
        }

        catch(Exception e){
            Toast.makeText(this, "Noe gikk galt. Pass på å skriv inn et tall.", Toast.LENGTH_LONG).show();
        }
    }
}
