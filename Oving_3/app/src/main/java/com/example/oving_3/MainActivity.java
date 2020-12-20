package com.example.oving_3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private CustomAdapter adapter;
    static final int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Adding some "example friends"
        friends.add(new Friend("Ola", "010198"));
        friends.add(new Friend("Per", "020287"));

        //adapter = new ArrayAdapter<Friend>(this, android.R.layout.simple_list_item_1, friends);
        adapter = new CustomAdapter(friends, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView friendList = findViewById(R.id.list);
        friendList.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu meny){
        super.onCreateOptionsMenu(meny);
        meny.add("Registrer");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().equals("Registrer")){
            Log.i("onOptionsItemSelected()", "Registrer");
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivityForResult(i, request_Code);
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 ) {
            if(resultCode == RESULT_OK){
                Friend newFriend = (Friend) data.getParcelableExtra("newFriend"); // Get the new Friend object from the intent sent from RegisterActivity
                friends.add(newFriend);
                adapter.notifyDataSetChanged();
            }
            else{
                System.out.println("Error when getting new friend from RegisterActivity");
            }
        }
        else if(requestCode == 2 ) {
            if(resultCode == RESULT_OK){
                Friend friend = data.getParcelableExtra("editedFriend");
                int pos = data.getIntExtra("position", -1);
                Log.i("edited", friend.toString());
                friends.set(pos, friend);
                adapter.notifyDataSetChanged();
            }
            else{
                System.out.println("Error when editing friend");
            }
        }
    }
}
