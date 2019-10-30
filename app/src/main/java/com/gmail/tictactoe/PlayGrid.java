package com.gmail.tictactoe;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PlayGrid extends AppCompatActivity implements View.OnClickListener {

    private ImageButton currentShapeImg;
    private ImageButton imgButtonUndo;
    private ImageButton imgButtonExit;
    private Button tmpButtonID;
    private Button[][] imgButtonArray2D;
    private int rows;
    private int columns;
    private int currentPlayer;
    private int currentNumber = 1;
    private int previousNumber = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_grid);

        currentPlayer = 1;
        if(MainActivity.GRID_X >= MainActivity.GRID_Y){
            rows = MainActivity.GRID_Y;
            columns = MainActivity.GRID_X;
        }else {
            rows = MainActivity.GRID_X;
            columns = MainActivity.GRID_Y;
        }

        imgButtonArray2D = new Button[rows][columns];

        currentShapeImg = findViewById(R.id.imageButtonCurrentShape);
        imgButtonUndo = findViewById(R.id.imageButtonUndo);
        imgButtonExit = findViewById(R.id.imageButtonExit);

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

        tableGridCreator();
        currentPlayer(currentNumber);
        previousNumber(currentPlayer);
    }

    private void tableGridCreator(){

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f );
        tableRowParams.setMargins(15, 15, 15, 15);

        TableLayout tablePlayField = findViewById(R.id.playFieldLayout);
        for(int row = 0; row < rows; row++){
            TableRow tableRow = new TableRow(this);
            tablePlayField.addView(tableRow);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f ));


            for(int col = 0; col < columns; col++){

                Button button = new Button(this);
                button.setLayoutParams(tableRowParams);
                button.setPadding(0,0,0,0 );
                int buttonID = Integer.parseInt("" + row + col);
                button.setId(buttonID);
                button.setBackgroundResource(R.color.colorWhite);
                button.setOnClickListener(this);
                tableRow.addView(button);
                imgButtonArray2D[row][col] = button;
            }
        }

    }

    private void lockButtonSizes() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Button button = imgButtonArray2D[row][col];

                int width = button.getWidth();
                button.setMinimumWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinimumHeight(height);
                button.setMaxHeight(height);
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

        }
    }


    private void undo() {

        System.out.println("========== BUTTON ID: undo");
        previousPlayer();
        previousNumber();
        setCurrentShapeImg(previousNumber);
        setButtonImage(tmpButtonID, previousNumber);


        imgButtonUndo.setClickable(false);
        tmpButtonID.setClickable(true);
        tmpButtonID.setBackgroundResource(R.color.colorWhite);

        System.out.println("========================================================================");
    }


    public void setCurrentShapeImg(int playerShape){


        switch (playerShape){
             case 1:
                 currentShapeImg.setImageResource(R.drawable.ring);
                 break;
             case 2:
                 currentShapeImg.setImageResource(R.drawable.x);
                 break;
             case 3:
                 currentShapeImg.setImageResource(R.drawable.trojkat);
                 break;

             case 4:
                currentShapeImg.setImageResource(R.drawable.kwadrat);
                break;

             case 5:
                currentShapeImg.setImageResource(R.drawable.star);
                break;

             case 6:
                currentShapeImg.setImageResource(R.drawable.trapez);
                break;

        }
    }

    public void setButtonImage(Button button, int buttonShape){
        System.out.println("imgWidth = " + button.getWidth() + ", imgHeight = " + button.getHeight());


        switch (buttonShape){
            case 1:
                button.setBackground(imageSizeForButton(R.drawable.ring, button));
                break;

            case 2:
                button.setBackground(imageSizeForButton(R.drawable.x, button));
                break;

            case 3:
                button.setBackground(imageSizeForButton(R.drawable.trojkat, button));
                break;

            case 4:
                button.setBackground(imageSizeForButton(R.drawable.kwadrat, button));
                break;

            case 5:
                button.setBackground(imageSizeForButton(R.drawable.star, button));
                break;

            case 6:
                button.setBackground(imageSizeForButton(R.drawable.trapez, button));
                break;
        }

    }

    public BitmapDrawable imageSizeForButton(int drawableRes, Button button){


        int newWidth = button.getWidth();
        int newHeight = button.getHeight();

        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);

        Resources resources = getResources();
        return new BitmapDrawable(resources, scaledBitmap);
    }


    @Override
    public void onClick(View v) {
        System.out.println("========== BUTTON ID: " +v.getId());

        v.setClickable(false);
        tmpButtonID = findViewById(v.getId());
        lockButtonSizes();
        setCurrentShapeImg(currentPlayer);
        setButtonImage(tmpButtonID, previousNumber);
        currentPlayer(currentNumber);
        previousNumber(currentPlayer);
        imgButtonUndo.setClickable(true);
        System.out.println("------------------------------------------------------------------------");


    }

}
