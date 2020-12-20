package com.example.oving_3;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeActivity extends AppCompatActivity {
    EditText nameview;
    EditText birthdateview;
    String name;
    String birtdate;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        // Get the values for this friend
        position = getIntent().getIntExtra("position", -1); // -1 is default value
        name = getIntent().getStringExtra("name");
        birtdate = getIntent().getStringExtra("birthdate");

        // Get the views and set name and birth date
        nameview = (EditText) findViewById(R.id.editName);
        birthdateview = (EditText) findViewById(R.id.editBirtdate);
        nameview.setText(name);
        birthdateview.setText(birtdate);
    }

    public boolean onCreateOptionsMenu(Menu meny){
        super.onCreateOptionsMenu(meny);
        meny.add("Venneliste");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().equals("Venneliste")){
            Log.i("onOptionsItemSelected()", "Venneliste");
            finish(); // Just finish this activity to return to the main activity
        }
        return true;
    }

    public void onclickEdit(View v) {
        try{
            String newName = nameview.getText().toString();
            String newBirthdate = birthdateview.getText().toString();
            Log.i("Edit", name + " is being changed to " + newName + ", and birthdate to " + newBirthdate);

            Intent intent = new Intent();
            Friend editedFriend = new Friend(newName, newBirthdate);

            intent.putExtra("editedFriend", (Friend) editedFriend);
            intent.putExtra("position", position);
            setResult(RESULT_OK, intent);
            finish();
        }
        catch(Exception e){
            Toast.makeText(this, "Noe gikk galt.", Toast.LENGTH_LONG).show();
        }
    }



}
