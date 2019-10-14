package com.gmail.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class Popup extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int screenHight = displayMetrics.heightPixels;

        getWindow().setLayout((int)(screenWidth*0.8),(int)(screenHight*0.5));
    }


}
