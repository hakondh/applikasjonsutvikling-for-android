package com.example.oving_7;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "data_saving_log";
    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            db = new DatabaseManager(this);
            db.clean(); // Wiping db (for testing)

            // Read the books and authors from the files
            File dir = getFilesDir();
            Log.i(TAG, dir.toString());
            File file = new File(dir, "data.txt");
            FileReader fr = null;
            BufferedReader br = null;
            try{
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                String book = br.readLine();
                String author = "";
                String next;
                long id;
                ArrayList<String> authors = null;
                // Read lines until there is no more
                while(book != null){
                    Log.i(TAG, "New book:");
                    Log.i(TAG, book);
                    Log.i(TAG, "Author(s):");
                    author = br.readLine();
                    Log.i(TAG, author.substring(7));
                    id = db.insert(author.substring(7), book);

                    // Read the next line - check if it's another author, a new book or no more data
                    boolean foundAll = false;
                    while(!foundAll){
                        next = br.readLine();
                        // Check if there was a new line or if all the data has been read
                        if(next ==  null){
                            Log.i(TAG, "All data has been read.");
                            foundAll = true;
                            book = null;
                        }
                        else{
                            // Check if this is a new book or another author
                            String substring = next.substring(0, 7);
                            // More authors
                            if(substring.compareTo("Author:") == 0){
                                Log.i(TAG, "Adding another author of this book:");
                                String anotherAuthor = next.substring(7);
                                Log.i(TAG, anotherAuthor);
                                id = db.insert(anotherAuthor, book);
                            }
                            // A new book
                            else{
                                book = next;
                                foundAll = true;
                            }
                        }
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

            /*long id = db.insert("Bud Kurniawan","Android Application Development: A Beginners Tutorioal");
            id = db.insert("Mildrid Ljosland", "Programmering i C++");
            id = db.insert("Else Lervik", "Programmering i C++");
            id = db.insert("Mildrid Ljosland", "Algoritmer og datastrukturer");
            id = db.insert("Helge Hafting", "Algoritmer og datastrukturer");*/

            //   ArrayList<String> res = db.getAllAuthors();

            ArrayList<String> res = db.getAllBooks();
            //   ArrayList<String> res = db.getBooksByAuthor("Mildrid Ljosland");
            //   ArrayList<String> res = db.getAuthorsByBook("Programmering i C++");
            //   ArrayList<String> res = db.getAllBooksAndAuthors();
            showResults(res);
        }
        catch (Exception e) {
            String tekst = e.getMessage();
            TextView t = (TextView)findViewById(R.id.tw1);
            t.setText(tekst);
        }
    }



    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list)  {
            res.append(s+"\n");
        }
        TextView t = (TextView)findViewById(R.id.tw1);
        t.setText(res);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add("Endre bakgrunnsfarge");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getTitle().equals("Endre bakgrunnsfarge")){
            Log.i(TAG, "Endre bakgrunnsfarge");
            Intent i = new Intent(this, PreferenceActivity.class);
            startActivity(i);
        }
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int color = Integer.parseInt(sharedPref.getString("color_preference", "1"));
        findViewById(R.id.tw1).setBackgroundColor(color);
    }

    public void viewBooks(View v){
        ArrayList<String> res = db.getAllBooks();
        showResults(res);
    }

    public void viewAuthors(View v){
        ArrayList<String> res = db.getAllAuthors();
        showResults(res);
    }

   /* private void setColor(int color){
        TextView t = (TextView)findViewById(R.id.tw1);
        t.setBackgroundColor(color);
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}
