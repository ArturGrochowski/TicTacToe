package com.gmail.tictactoe;

import android.os.Build;
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
    private LinearLayout[] rowArray;
    private ImageButton currentShapeImg;
    private ImageButton imgButtonUndo;
    private ImageButton imgButtonExit;
    private ImageButton tmpButtonID;
    private ImageButton[] imgButtonsArray;
    private ImageButton[] buttonsQeue = new ImageButton[2];
    private int rowX;
    private int rowY;
    private int numberOfButtons;
    private int currentPlayer;
    private int currentNumber = 1;
    private int previousNumber = 1;
    private int prePreviousNumber = 1;
    private ImageButton currentButton;
    private ImageButton previousButton;



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_grid);


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
        imgButtonUndo = findViewById(R.id.imageButtonUndo);
        imgButtonExit = findViewById(R.id.imageButtonExit);
        playField.setWeightSum((float) rowY);
        rowArray = new LinearLayout[rowY];
        numberOfButtons = rowY*rowX;
        imgButtonsArray = new ImageButton[numberOfButtons];

        imgButtonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo();
            }
        });

        imgButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
//                    imgButtonsArray[j].setPadding(3,3,3,3);

                    rowArray[i].addView(imgButtonsArray[j]);

                }
                playField.addView(rowArray[i]);
        }

        currentPlayer(currentNumber);
        previousNumber(currentPlayer);
    }


    public void currentPlayer(int currentNumber){
        if(currentPlayer<MainActivity.NUMBER_OF_PLAYERS){
            currentPlayer = currentNumber +1;
        }else {
            currentPlayer = 1;
        }
    }

    public void previousNumber(int number){
        if(currentNumber == 1 && previousNumber == 2 && currentPlayer != 2){
            previousNumber = 1;
            prePreviousNumber = MainActivity.NUMBER_OF_PLAYERS;
        }else {
            System.out.println("A currentPLAYER = " + currentPlayer);
            System.out.println("A PREPRIVnr = " + prePreviousNumber);
            System.out.println("A prevNr = " + previousNumber);
            System.out.println("A currentNr = " + currentNumber);

            prePreviousNumber = previousNumber;
            previousNumber = currentNumber;
            currentNumber = number;
            System.out.println("B PREPRIVnr = " + prePreviousNumber);
            System.out.println("B prevNr = " + previousNumber);
            System.out.println("B currentNr = " + currentNumber);
            System.out.println("B currentPLAYER = " + currentPlayer);

        }
    }

    public void previousButton(ImageButton button){
        previousButton = currentButton;
        currentButton = button;
    }

    private void undo() {
        imgButtonUndo.setClickable(false);
        previousNumber = prePreviousNumber;
        currentNumber = previousNumber;
        previousNumber(previousNumber);
        currentPlayer(prePreviousNumber);
        setCurrentShapeImg(tmpButtonID);
        tmpButtonID.setClickable(true);
        tmpButtonID.setImageDrawable(null);
    }

    private void addToButtonsQeue(ImageButton button) {
        buttonsQeue[0] = buttonsQeue[1];
        buttonsQeue[1] = button;
    }

    public void setCurrentShapeImg(ImageButton imageButton){
//        System.out.println("serCurrentShapeImg() currentPlayer = " + previousNumber);

        switch (currentPlayer){
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
//        System.out.println("setButtonImage() currentPlayer = " + previousNumber);

        switch (previousNumber){
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

//        System.out.println("A currentNR = " + currentNumber);
        System.out.println("A currentPLAYER = " + currentPlayer);
        v.setClickable(false);
        tmpButtonID = findViewById(v.getId());
        setCurrentShapeImg(tmpButtonID);
//        System.out.println(tmpButtonID);
//        System.out.println(v.getId());
        currentPlayer(currentNumber);
        previousNumber(currentPlayer);
        previousButton(tmpButtonID);
        imgButtonUndo.setClickable(true);
        System.out.println("B currentPLAYER = " + currentPlayer);
        addToButtonsQeue(tmpButtonID);


    }

}
