package com.example.oving_4;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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
        meny.add("Neste bilde");
        meny.add("Forrige bilde");
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        FragmentManager manager = getSupportFragmentManager();
        ListFragment lf = (ListFragment) manager.findFragmentById(R.id.fragList);

        if(item.getTitle().equals("Neste bilde")){
            Log.i("option", "Neste");
            lf.navigateImages(1);

        }
        else if(item.getTitle().equals("Forrige bilde")){
            Log.i("option", "Forrige");
            lf.navigateImages(-1);
        }
        return true;
    }


}
