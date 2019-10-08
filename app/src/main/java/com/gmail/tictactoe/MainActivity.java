package com.gmail.tictactoe;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button3x3;
    private Button button5x5;
    private Button button8x8;
    private Button buttonCustom;
    private Button buttonStart;
    private Button buttonPlayers2;
    private Button buttonPlayers3;
    private Button buttonPlayers4;
    private Button buttonPlayers5;
    private Button buttonPlayers6;
    private int gridX = 3;
    private int gridY = 3;
    private int inARawToWin = 3;


    @SuppressLint("WrongViewCast")
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

        button3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridX = 3;
                gridY = 3;
                inARawToWin = 3;
            }
        });

        button5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridX = 5;
                gridY = 5;
                inARawToWin = 4;
            }
        });

        button8x8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridX = 8;
                gridY = 8;
                inARawToWin = 5;
            }
        });


    }
}
