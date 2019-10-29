package com.gmail.tictactoe;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PlayGrid extends AppCompatActivity implements View.OnClickListener {

//    private TableLayout playField;
//    private LinearLayout[] rowArray;
    private ImageButton currentShapeImg;
    private ImageButton imgButtonUndo;
    private ImageButton imgButtonExit;
    private ImageButton tmpButtonID;
//    private ImageButton[] imgButtonsArray;
    private ImageButton[][] imgButtonArray2D;
    private int rowX;
    private int rowY;
    private int numberOfButtons;
    private int currentPlayer;
    private int currentNumber = 1;
    private int previousNumber = 1;
    private int imgWidth;
    private int imgHight;
    private Resources resources;


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

        imgButtonArray2D = new ImageButton[rowX][rowY];

        currentShapeImg = findViewById(R.id.imageButtonCurrentShape);
//        playField = findViewById(R.id.playFieldLayout);
        imgButtonUndo = findViewById(R.id.imageButtonUndo);
        imgButtonExit = findViewById(R.id.imageButtonExit);
//        playField.setWeightSum((float) rowY);
//        rowArray = new LinearLayout[rowY];
        numberOfButtons = rowY*rowX;
//        imgButtonsArray = new ImageButton[numberOfButtons];

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
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0 , 1.0f);
//        LinearLayout.LayoutParams buttonsParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
//
//        for (int i= 0; i< rowY; i++){
//            rowArray[i] = new LinearLayout(this);
//            rowArray[i].setWeightSum((float) rowX);
//            rowArray[i].setLayoutParams(layoutParams);
//        }
//
//
//        for (int i = 0; i < rowY; i++){
//
//                for(int j= 0; j<numberOfButtons; j++){
//
//                    int buttonID = Integer.parseInt("" + i + j);
//                    imgButtonsArray[j] = new ImageButton(this);
//                    imgButtonsArray[j].setId(buttonID);
//                    imgButtonsArray[j].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//                    imgButtonsArray[j].setOnClickListener(this);
//                    imgButtonsArray[j].setLayoutParams(buttonsParams);
//                    rowArray[i].addView(imgButtonsArray[j]);
//
//                }
//                playField.addView(rowArray[i]);
//        }
        tableGridCreator();
        currentPlayer(currentNumber);
        previousNumber(currentPlayer);
    }

    private void tableGridCreator(){
        TableLayout tablePlayField = findViewById(R.id.playFieldLayout);
        for(int row = 0; row < rowX; row++){
            TableRow tableRow = new TableRow(this);
            tablePlayField.addView(tableRow);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f ));

//
//            TableRow rowDivider = new TableRow(this);
//            tablePlayField.addView(rowDivider);
//            rowDivider.setLayoutParams(new TableLayout.LayoutParams(
//                    TableLayout.LayoutParams.MATCH_PARENT, 2,
//                    1.0f ));
//            rowDivider.setBackgroundColor(getResources().getColor(R.color.colorBlack));

            for(int col = 0; col < rowY; col++){
                ImageButton imageButton = new ImageButton(this);
                imageButton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f ));
                int buttonID = Integer.parseInt("" + row + col);
                imageButton.setId(buttonID);
                imageButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//                imageButton.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                imageButton.setOnClickListener(this);
                tableRow.addView(imageButton);
                imgButtonArray2D[row][col] = imageButton;
            }
        }
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
//                imageButton.setImageResource(R.drawable.ring);
                imageButton.setImageDrawable(imageSizeForButton(R.drawable.ring));
                break;
            case 2:
//                imageButton.setImageResource(R.drawable.x);
                imageButton.setImageDrawable(imageSizeForButton(R.drawable.x));
                break;
            case 3:
                imageButton.setImageDrawable(imageSizeForButton(R.drawable.trojkat));
                break;

            case 4:
//                imageButton.setImageResource(R.drawable.kwadrat);
                imageButton.setImageDrawable(imageSizeForButton(R.drawable.kwadrat));
                break;

            case 5:
//                imageButton.setImageResource(R.drawable.star);
                imageButton.setImageDrawable(imageSizeForButton(R.drawable.star));
                break;

            case 6:
//                imageButton.setImageResource(R.drawable.trapez);
                imageButton.setImageDrawable(imageSizeForButton(R.drawable.trapez));
                break;

        }

    }

    public BitmapDrawable imageSizeForButton(int drawableRes){

        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, imgWidth, imgHight, true);

        return new BitmapDrawable(resources, scaledBitmap);
    }


    @Override
    public void onClick(View v) {
        System.out.println("========== BUTTON ID: " +v.getId());
        imgWidth = v.getWidth();
        imgHight = v.getHeight();
        resources = getResources();



//        System.out.println("A currentNR = " + currentNumber);
        System.out.println("A currentPLAYER = " + currentPlayer);
        v.setClickable(false);
        tmpButtonID = findViewById(v.getId());
        setCurrentShapeImg(tmpButtonID, currentPlayer, previousNumber);

        tmpButtonID.setMinimumWidth(imgWidth);
        tmpButtonID.setMaxWidth(imgWidth);
        tmpButtonID.setMinimumHeight(imgHight);
        tmpButtonID.setMaxHeight(imgHight);
        currentPlayer(currentNumber);
        previousNumber(currentPlayer);
        imgButtonUndo.setClickable(true);
        System.out.println("B currentPLAYER = " + currentPlayer);
        System.out.println("------------------------------------------------------------------------");


    }

    private int drawableRes(int buttonShape) {
        int shape = R.drawable.ring;
        switch (buttonShape){
            case 1:
//                imageButton.setImageResource(R.drawable.ring);
                shape = R.drawable.ring;
                break;
            case 2:
//                imageButton.setImageResource(R.drawable.x);
                shape = R.drawable.x;
                break;
            case 3:
                shape = R.drawable.trojkat;
                break;

            case 4:
//                imageButton.setImageResource(R.drawable.kwadrat);
                shape = R.drawable.kwadrat;
                break;

            case 5:
//                imageButton.setImageResource(R.drawable.star);
                shape = R.drawable.star;
                break;

            case 6:
//                imageButton.setImageResource(R.drawable.trapez);
                shape = R.drawable.trapez;
                break;

        }
        return shape;
    }

}
