package com.gmail.tictactoe;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class PlayGrid extends AppCompatActivity implements View.OnClickListener {

    private TableLayout playField;
    private LinearLayout[] rowArray;
    private ImageButton currentShapeImg;
    private ImageButton imgButtonUndo;
    private ImageButton imgButtonExit;
    private ImageButton tmpButtonID;
    private ImageButton[] imgButtonsArray;
    private int rowX;
    private int rowY;
    private int numberOfButtons;
    private int currentPlayer;
    private int currentNumber = 1;
    private int previousNumber = 1;



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

    public void previousPlayer(){
        if(currentPlayer>1){
            currentPlayer--;
        }else {
            currentPlayer = MainActivity.NUMBER_OF_PLAYERS;
        }
    }

    public void previousNumber(){
        if(previousNumber>1){
            previousNumber--;
        }else {
            previousNumber = MainActivity.NUMBER_OF_PLAYERS;
        }
        if(currentNumber>1){
            currentNumber--;
        }else {
            currentNumber = MainActivity.NUMBER_OF_PLAYERS;
        }
        System.out.println("previousNumber() previousNumber: " + previousNumber);
        System.out.println("previousNumber() currentNumber: " + currentNumber);

    }

    public void previousNumber(int number){
        if(currentNumber == 1 && previousNumber == 2 && currentPlayer != 2){
            previousNumber = 1;
        }else {

            if(currentNumber == previousNumber && currentNumber!=1){
                currentNumber--;
                previousNumber--;
            }

            previousNumber = currentNumber;
            currentNumber = number;
            System.out.println("previousNumber(int number) previousNumber: " + previousNumber);
            System.out.println("previousNumber(int number) currentNumber: " + currentNumber);

        }
    }


    private void undo() {

        System.out.println("========== BUTTON ID: undo");
        previousPlayer();
        previousNumber();
        setCurrentShapeImg(tmpButtonID, previousNumber, previousNumber);


        imgButtonUndo.setClickable(false);
        tmpButtonID.setClickable(true);
        tmpButtonID.setImageDrawable(null);

        System.out.println("========================================================================");
    }


    public void setCurrentShapeImg(ImageButton imageButton, int playerShape, int buttonShape){
        System.out.println("setCurrentShapeImg() = " + playerShape);

        switch (playerShape){
             case 1:
                 currentShapeImg.setImageResource(R.drawable.ring);
                 setButtonImage(imageButton, buttonShape);
                 break;
             case 2:
                 currentShapeImg.setImageResource(R.drawable.x);
                 setButtonImage(imageButton, buttonShape);
                 break;
             case 3:
                 currentShapeImg.setImageResource(R.drawable.trojkat);
                 setButtonImage(imageButton, buttonShape);
                 break;

             case 4:
                currentShapeImg.setImageResource(R.drawable.kwadrat);
                 setButtonImage(imageButton, buttonShape);
                break;

             case 5:
                currentShapeImg.setImageResource(R.drawable.star);
                 setButtonImage(imageButton, buttonShape);
                break;

             case 6:
                currentShapeImg.setImageResource(R.drawable.trapez);
                 setButtonImage(imageButton, buttonShape);
                break;

        }
    }

    public void setButtonImage(ImageButton imageButton, int buttonShape){
        System.out.println("setButtonImage() = " + buttonShape);
        switch (buttonShape){
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
        System.out.println("========== BUTTON ID: " +v.getId());

//        System.out.println("A currentNR = " + currentNumber);
        System.out.println("A currentPLAYER = " + currentPlayer);
        v.setClickable(false);
        tmpButtonID = findViewById(v.getId());
        setCurrentShapeImg(tmpButtonID, currentPlayer, previousNumber);
        currentPlayer(currentNumber);
        previousNumber(currentPlayer);
//        shape.setCurrentShape(currentPlayer);
//        shape.setCurrentButtonShape(previousNumber);
        imgButtonUndo.setClickable(true);
        System.out.println("B currentPLAYER = " + currentPlayer);
        System.out.println("------------------------------------------------------------------------");


    }

}

//class Shape {
//
//    private int currentShape = 1;
//    private int currentButtonShape = 1;
//    private int previousShape = 1;
//    private int previousButtonShape = 1;
//
//
//    public int getCurrentShape() {
//        return currentShape;
//    }
//
//    public void setCurrentShape(int currentShape) {
//        this.currentShape = currentShape;
//    }
//
//    public int getCurrentButtonShape() {
//        return currentButtonShape;
//    }
//
//    public void setCurrentButtonShape(int currentButtonShape) {
//        this.currentButtonShape = currentButtonShape;
//    }
//
//    public int getPreviousShape() {
//        return previousShape;
//    }
//
//    public void setPreviousShape(int previousShape) {
//        this.previousShape = previousShape;
//    }
//
//    public int getPreviousButtonShape() {
//        return previousButtonShape;
//    }
//
//    public void setPreviousButtonShape(int previousButtonShape) {
//        this.previousButtonShape = previousButtonShape;
//    }
//
//}
