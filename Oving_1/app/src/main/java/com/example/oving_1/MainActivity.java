package com.example.oving_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu meny){
        super.onCreateOptionsMenu(meny);
        meny.add("Håkon");
        meny.add("Dalen Hestnes");
        Log.i("onCreateOptionsMenu()", "meny laget");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().equals("Håkon")){
            Log.i("onOptionsItemSelected()", "Håkon");
        }
        else if(item.getTitle().equals("Dalen Hestnes")){
            Log.i("onOptionsItemSelected()", "Dalen Hestnes");
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy");
    }
}
