package com.gmail.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    static int GRID_ROWS = 3;
    static int GRID_COLUMNS = 3;
    static int IN_A_LINE_TO_WIN = 3;
    static int NUMBER_OF_PLAYERS = 2;
    static boolean darkMode;
    private RadioGroup radioGroupPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        MenuItem itemSwitch = menu.findItem(R.id.mySwitch);
        itemSwitch.setActionView(R.layout.use_switch);
        SwitchCompat sw = menu.findItem(R.id.mySwitch).getActionView().findViewById(R.id.action_switch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                darkMode = isChecked;
                setBackgroundMode();
        }
        });

        return true;
    }

    private void setBackgroundMode() {
        LinearLayout layout = findViewById(R.id.main_activity_layout);
        if(darkMode){
            layout.setBackgroundResource(R.color.colorBlack);
        } else {
            layout.setBackgroundResource(R.color.colorWhite);
        }
    }


    private void setupButtons() {
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
                GRID_ROWS = 3;
                GRID_COLUMNS = 3;
                IN_A_LINE_TO_WIN = 3;
                button5x5.setChecked(false);
                button8x8.setChecked(false);
                buttonCustom.setChecked(false);
                break;

            case R.id.imageButton5x5:
                GRID_ROWS = 5;
                GRID_COLUMNS = 5;
                IN_A_LINE_TO_WIN = 4;
                button3x3.setChecked(false);
                button8x8.setChecked(false);
                buttonCustom.setChecked(false);
                break;

            case R.id.imageButton8x8:
                GRID_ROWS = 8;
                GRID_COLUMNS = 8;
                IN_A_LINE_TO_WIN = 5;
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
                NUMBER_OF_PLAYERS = 2;
                break;

            case R.id.imageButtonPlayers3:
                NUMBER_OF_PLAYERS = 3;
                break;

            case R.id.imageButtonPlayers4:
                NUMBER_OF_PLAYERS = 4;
                break;

            case R.id.imageButtonPlayers5:
                NUMBER_OF_PLAYERS = 5;
                break;

            case R.id.imageButtonPlayers6:
                NUMBER_OF_PLAYERS = 6;
                break;

        }

    }

}
