package com.gmail.tictactoe;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PlayGrid extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout playField;
    private int rowX;
    private int rowY;
    private ImageButton[] imgButtonsArray;
    private int numberOfButtons;
    private LinearLayout[] rowArray;
    private ImageButton currentShapeImg;
    private int currentPlayer;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_grid);

        ColorDrawable colorDrawable = new ColorDrawable(Color.WHITE);

        currentPlayer = 1;
        if(MainActivity.GRID_X >= MainActivity.GRID_Y){
            rowX = MainActivity.GRID_Y;
            rowY = MainActivity.GRID_X;
        }else {
            rowX = MainActivity.GRID_X;
            rowY = MainActivity.GRID_Y;
        }

        currentShapeImg = findViewById(R.id.imageButtonCurrentShape);
        playField = findViewById(R.id.playFieldLayout);
        playField.setWeightSum((float) rowY);
        rowArray = new LinearLayout[rowY];

        numberOfButtons = rowY*rowX;
        imgButtonsArray = new ImageButton[numberOfButtons];


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0 , 1.0f);
        LinearLayout.LayoutParams buttonsParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);

        for (int i= 0; i< rowY; i++){
            rowArray[i] = new LinearLayout(this);
            rowArray[i].setWeightSum((float) rowX);
            rowArray[i].setLayoutParams(layoutParams);
        }


        for (int i = 0; i < rowY; i++){

                for(int j= 0; j<numberOfButtons; j++){

                    int buttonID = Integer.parseInt("" + i + j);
                    imgButtonsArray[j] = new ImageButton(this);
                    imgButtonsArray[j].setId(buttonID);
                    imgButtonsArray[j].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imgButtonsArray[j].setOnClickListener(this);
                    imgButtonsArray[j].setLayoutParams(buttonsParams);
//                    imgButtonsArray[j].setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    imgButtonsArray[j].setBackground(colorDrawable);
                    imgButtonsArray[j].setPadding(3,3,3,3);

                    rowArray[i].addView(imgButtonsArray[j]);

                }
                playField.addView(rowArray[i]);
        }
        System.out.println("----------------------------------------------------------");
        System.out.println(imgButtonsArray[0].getBackground());

    }

    public void currentPlayer(){
        if(currentPlayer<MainActivity.NUMBER_OF_PLAYERS){
            currentPlayer++;
        }else {
            currentPlayer = 1;
        }
    }

    public void setCurrentShapeImg(ImageButton imageButton){
        int nextShape = currentPlayer;
        switch (nextShape){
             case 1:
                 currentShapeImg.setImageResource(R.drawable.ring);
                 setButtonImage(imageButton);
                 break;
             case 2:
                 currentShapeImg.setImageResource(R.drawable.x);
                 setButtonImage(imageButton);
                 break;
             case 3:
                 currentShapeImg.setImageResource(R.drawable.trojkat);
                 setButtonImage(imageButton);
                 break;

             case 4:
                currentShapeImg.setImageResource(R.drawable.kwadrat);
                 setButtonImage(imageButton);
                break;

             case 5:
                currentShapeImg.setImageResource(R.drawable.star);
                 setButtonImage(imageButton);
                break;

             case 6:
                currentShapeImg.setImageResource(R.drawable.trapez);
                 setButtonImage(imageButton);
                break;

        }
    }

    public void setButtonImage(ImageButton imageButton){
        switch (currentPlayer){
            case 1:
                imageButton.setImageResource(R.drawable.ring);
                break;
            case 2:
                imageButton.setImageResource(R.drawable.x);
                break;
            case 3:
                imageButton.setImageResource(R.drawable.trojkat);
                break;

            case 4:
                imageButton.setImageResource(R.drawable.kwadrat);
                break;

            case 5:
                imageButton.setImageResource(R.drawable.star);
                break;

            case 6:
                imageButton.setImageResource(R.drawable.trapez);
                break;

        }

    }


    @Override
    public void onClick(View v) {
        currentPlayer();
        ImageButton tmpButtonID = findViewById(v.getId());
        setCurrentShapeImg(tmpButtonID);
        System.out.println(tmpButtonID);
        System.out.println(v.getId());
    }
}
