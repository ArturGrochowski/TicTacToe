package com.gmail.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Popup extends Activity {


    private EditText editTextInRow;
    private EditText editTextRowX;
    private EditText editTextRowY;
    private ImageButton imageButtonOK;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);
        setWindowSize();
        setEditTextView();
        setupOkButton();
        setBackgroundMode();
    }

    private void setWindowSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        getWindow().setLayout((int)(screenWidth*0.8),(int)(screenHeight*0.5));
    }

    private void setEditTextView() {
        editTextInRow = findViewById(R.id.textInARaw);
        editTextRowX = findViewById(R.id.textRawX);
        editTextRowY = findViewById(R.id.textRawY);
    }

    private void setupOkButton() {
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
                    MainActivity.IN_A_LINE_TO_WIN = Integer.parseInt(inRow);
                    MainActivity.GRID_ROWS = Integer.parseInt(rowX);
                    MainActivity.GRID_COLUMNS = Integer.parseInt(rowY);
                    finish();
                }
            }
        });
    }


    private void setBackgroundMode() {
        LinearLayout backgroundColor = findViewById(R.id.popupWindow);
        if(MainActivity.DARK_MODE){
            backgroundColor.setBackgroundResource(R.color.colorBlack);
            imageButtonOK.setBackgroundResource(R.drawable.button_ok_dark);
        } else {
            backgroundColor.setBackgroundResource(R.color.colorWhite);
            imageButtonOK.setBackgroundResource(R.drawable.ok_button);
            imageButtonOK.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        }
    }

}
