package com.example.oving_3;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText name;
    EditText birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        birthdate = (EditText) findViewById(R.id.birthdate);
    }

    public boolean onCreateOptionsMenu(Menu meny){
        super.onCreateOptionsMenu(meny);
        meny.add("Venneliste");
        return true;
    }

    public void onClickRegister(View v) {
        try{
            String newName = name.getText().toString();
            String newBirthdate = birthdate.getText().toString();
            Intent intent = new Intent();
            Friend newFriend = new Friend(newName, newBirthdate);
            intent.putExtra("newFriend", (Friend) newFriend);
            setResult(RESULT_OK, intent);
            finish();
        }
        catch(Exception e){
            Toast.makeText(this, "Noe gikk galt. Pass på å skriv inn et tall.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().equals("Venneliste")){
            Log.i("onOptionsItemSelected()", "Venneliste");
            finish(); // Just finish this activity to return to the main activity
        }
        return true;
    }
}
