package com.gmail.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton button3x3;
    private ImageButton button5x5;
    private ImageButton button8x8;
    private ImageButton buttonCustom;
    private ImageButton buttonStart;
    private ImageButton buttonPlayers2;
    private ImageButton buttonPlayers3;
    private ImageButton buttonPlayers4;
    private ImageButton buttonPlayers5;
    private ImageButton buttonPlayers6;
    private int gridX = 3;
    private int gridY = 3;
    private int inARawToWin = 3;
    private int numberOfPlayers = 2;



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

    }

    private void startTheGame(){
            Intent intent = new Intent(this, PlayGrid.class);
            startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton3x3:
                gridX = 3;
                gridY = 3;
                inARawToWin = 3;
                break;

            case R.id.imageButton5x5:
                gridX = 5;
                gridY = 5;
                inARawToWin = 4;
                break;

            case R.id.imageButton8x8:
                gridX = 8;
                gridY = 8;
                inARawToWin = 5;
                break;

            case R.id.imageButtonCustom:

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
