package com.gmail.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton button3x3;
    private RadioButton button5x5;
    private RadioButton button8x8;
    private RadioButton buttonCustom;
    private ImageButton buttonStart;
    private RadioButton buttonPlayers2;
    private RadioButton buttonPlayers3;
    private RadioButton buttonPlayers4;
    private RadioButton buttonPlayers5;
    private RadioButton buttonPlayers6;
    private int gridX = 3;
    private int gridY = 3;
    private int inARawToWin = 3;
    private int numberOfPlayers = 2;
    private RadioGroup radioGroupPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button3x3 = findViewById(R.id.imageButton3x3);
        button5x5 = findViewById(R.id.imageButton5x5);
        button8x8 = findViewById(R.id.imageButton8x8);
        buttonCustom = findViewById(R.id.imageButtonCustom);
        buttonStart = findViewById(R.id.imageButtonStart);
        buttonPlayers2 = findViewById(R.id.imageButtonPlayers2);
        buttonPlayers3 = findViewById(R.id.imageButtonPlayers3);
        buttonPlayers4 = findViewById(R.id.imageButtonPlayers4);
        buttonPlayers5 = findViewById(R.id.imageButtonPlayers5);
        buttonPlayers6 = findViewById(R.id.imageButtonPlayers6);
        radioGroupPlayers = findViewById(R.id.radioGroupPlayers);
  ;

        button3x3.setOnClickListener(this);
        button5x5.setOnClickListener(this);
        button8x8.setOnClickListener(this);
        buttonCustom.setOnClickListener(this);
        buttonStart.setOnClickListener(this);
        buttonPlayers2.setOnClickListener(this);
        buttonPlayers3.setOnClickListener(this);
        buttonPlayers4.setOnClickListener(this);
        buttonPlayers5.setOnClickListener(this);
        buttonPlayers6.setOnClickListener(this);
;
    }

    private void startTheGame() {
        Intent intentGame = new Intent(this, PlayGrid.class);
        startActivity(intentGame);
    }

    private void customSize(){
        Intent intentPopup = new Intent(this, Popup.class);
        startActivity(intentPopup);
    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.imageButton3x3:
                gridX = 3;
                gridY = 3;
                inARawToWin = 3;
                button5x5.setChecked(false);
                button8x8.setChecked(false);
                buttonCustom.setChecked(false);
                break;

            case R.id.imageButton5x5:
                gridX = 5;
                gridY = 5;
                inARawToWin = 4;
                button3x3.setChecked(false);
                button8x8.setChecked(false);
                buttonCustom.setChecked(false);
                break;

            case R.id.imageButton8x8:
                gridX = 8;
                gridY = 8;
                inARawToWin = 5;
                button3x3.setChecked(false);
                button5x5.setChecked(false);
                buttonCustom.setChecked(false);
                break;

            case R.id.imageButtonCustom:

                button3x3.setChecked(false);
                button5x5.setChecked(false);
                button8x8.setChecked(false);
                customSize();

                break;

            case R.id.imageButtonStart:
                startTheGame();
                break;

            case R.id.imageButtonPlayers2:
                numberOfPlayers = 2;
                break;

            case R.id.imageButtonPlayers3:
                numberOfPlayers = 3;
                break;

            case R.id.imageButtonPlayers4:
                numberOfPlayers = 4;
                break;

            case R.id.imageButtonPlayers5:
                numberOfPlayers = 5;
                break;

            case R.id.imageButtonPlayers6:
                numberOfPlayers = 6;
                break;

        }

    }

}
