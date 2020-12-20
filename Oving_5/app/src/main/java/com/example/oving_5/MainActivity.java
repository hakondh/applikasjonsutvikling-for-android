package com.example.oving_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private HttpWrapperThreaded network;
    private final String serverURL = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";

    // UI-components
    private TextView infoTitle;
    private EditText nameEdit;
    private EditText cardNumberEdit;
    private Button startButton;

    private TextView hint;
    private EditText answer;
    private Button sendButton;

    private String name;
    private String cardNumber;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("gamelog", "Contacting the server...");
        network = new HttpWrapperThreaded(this, serverURL);

        //Get the UI-components
        infoTitle = findViewById(R.id.infotitle);
        nameEdit = findViewById(R.id.name);
        cardNumberEdit = findViewById(R.id.cardNumber);
        startButton = findViewById(R.id.startButton);

        hint = findViewById(R.id.hint);
        answer = findViewById(R.id.answer);
        sendButton = findViewById(R.id.sendButton);

        // Disable the game-UI from the start
        disableGameUI();

        infoTitle.setText("Oppgi navn og kortnummer for å spille");

        counter = 0;
    }

    // Send the request to server when starting the game
    public void startGame(View v){
        this.name = nameEdit.getText().toString();
        this.cardNumber = cardNumberEdit.getText().toString();

        Log.i("gamelog", name);
        Log.i("gamelog", cardNumber);

        disableInfoUI();
        enableGameUI();

        infoTitle.setText("Brukerinformasjon mottatt.");

        Map<String, String> requestValues = createRequestValues(name, cardNumber);
        network.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET, requestValues);
    }

    // Send the guess to serve
    public void sendAnswer(View v){
        String strAnswer = answer.getText().toString();
        Map<String, String> requestValue = createRequestValuesForGuess(strAnswer);
        network.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET, requestValue);
    }

    // Create the request values for the HTTP-request (personal info)
    private Map<String,String> createRequestValues(String name, String cardNumber){
        Map<String,String> valueList = new HashMap<>();
        valueList.put("navn", name);
        valueList.put("kortnummer", cardNumber);
        Log.i("gamelog", valueList.toString());
        return valueList;
    }

    // Create the request value for the HTTP-request (answer)
    private Map<String,String> createRequestValuesForGuess(String guess){
        Map<String,String> valueList = new HashMap<>();
        valueList.put("tall", guess);
        return valueList;
    }


    //Method for showing response from HTTP server
    public void showResponse(String response){
        Log.i("server_response","Server responded with: " + response);
        String testString =response.substring(0, 5);
        Log.i("name", testString);

        hint.setText(response);

        // Restart if the user has used all their tries
        if(counter == 3){
            counter = 0;
            disableGameUI();
            enableInfoUI();
        }
        else{
            counter++;
        }
    }

    // Enable info-UI
    private void enableInfoUI(){
        nameEdit.setEnabled(true);
        cardNumberEdit.setEnabled(true);
        startButton.setEnabled(true);
    }

    // Enable the game-UI
    private void enableGameUI(){
        answer.setEnabled(true);
        sendButton.setEnabled(true);
    }

    // Disable the personal info-UI
    private void disableInfoUI(){
        nameEdit.setText("");
        nameEdit.setEnabled(false);
        cardNumberEdit.setText("");
        cardNumberEdit.setEnabled(false);
        startButton.setEnabled(false);
    }

    private void disableGameUI(){
        answer.setText("");
        answer.setEnabled(false);
        sendButton.setEnabled(false);
    }

}
