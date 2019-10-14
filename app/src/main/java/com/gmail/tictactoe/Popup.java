package com.gmail.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Popup extends Activity {

//    public static int ROW_X = 3;
//    public static int ROW_Y = 3;
//    public static int IN_ROW = 3;

    private EditText editTextInRow;
    private EditText editTextRowX;
    private EditText editTextRowY;
    private ImageButton imageButtonOK;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int screenHight = displayMetrics.heightPixels;

        getWindow().setLayout((int)(screenWidth*0.8),(int)(screenHight*0.5));

        editTextInRow = findViewById(R.id.textInARaw);
        editTextRowX = findViewById(R.id.textRawX);
        editTextRowY = findViewById(R.id.textRawY);
        imageButtonOK = findViewById(R.id.imageButtonOK);


        imageButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inRow = editTextInRow.getText().toString();
                String rowX = editTextRowX.getText().toString();
                String rowY = editTextRowY.getText().toString();
                if(inRow.isEmpty() || rowX.isEmpty() || rowY.isEmpty()){
                    Toast.makeText(getApplicationContext(), "All numbers required!",
                            Toast.LENGTH_LONG).show();
                }else {
                    MainActivity.IN_A_RAW_TO_WIN = Integer.parseInt(inRow);
                    MainActivity.GRID_X = Integer.parseInt(rowX);
                    MainActivity.GRID_Y = Integer.parseInt(rowY);
                    finish();
                }
            }
        });

    }


}
