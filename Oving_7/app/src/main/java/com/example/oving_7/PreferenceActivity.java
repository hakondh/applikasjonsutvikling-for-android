package com.example.oving_7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);


        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            getFragmentManager().beginTransaction().add(R.id.fragment_container, new ColorOptionFragment()).commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add("Tilbake til listen");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().equals("Tilbake til listen")){
            Log.i("onOptionsItemSelected()", "Tilbake til listen");
            finish(); // Just finish this activity to return to the main activity
        }
        return true;
    }
}
