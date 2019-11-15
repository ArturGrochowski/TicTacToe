package com.gmail.tictactoe;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class PlayGrid extends AppCompatActivity implements View.OnClickListener {

    private ImageButton currentShapeButton;
    private ImageButton imgButtonUndo;
    private ImageButton imgButtonExit;
    private CustomButton tmpButtonID;
    private CustomButton[][] buttonsArray2D;
    private int rows;
    private int columns;
    private int marginSize;
    private int currentPlayer = 1;
    private int currentShape = 1;
    private int previousShape = 1;
    private int buttonBackgroundColor;
    private int inLineToWin = MainActivity.IN_A_LINE_TO_WIN;
    private int numberOfPlayers = MainActivity.NUMBER_OF_PLAYERS;
    private TableLayout tablePlayField;
    private TableRow.LayoutParams tableRowParams;
    private WinningEngine winningEngine;
//    private ArrayList<CustomButton> usedButtons;
//    private HashSet<CustomButton> winInRow;
//    private HashSet<CustomButton> winInColumn;
//    private HashSet<CustomButton> winInDecreasing;
//    private HashSet<CustomButton> winInIncreasing;
//    private HashMap<Integer, HashSet<CustomButton>> hashMap = new HashMap<>();
//    private HashMap<Integer, HashMap<Integer, HashSet<CustomButton>>> hashMapOfPlayersMoves;
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_grid);

        setRowsAndColumnsOrientation();
        createButtonsInArray2D();
        setupUndoButton();
        setupExitButton();
        setBackgroundMode();
        setupCurrentShapeButton();
        tableGridCreator();
//        assignLists();
//        assignHashMapAndLists();
        currentPlayer(currentShape);
        previousShape(currentPlayer);
        createWinningEngine();
    }

    private void createWinningEngine() {
        winningEngine = new WinningEngine(buttonsArray2D, numberOfPlayers, previousShape, inLineToWin, rows, columns);
    }


    private void setRowsAndColumnsOrientation() {
        if(MainActivity.GRID_ROWS >= MainActivity.GRID_COLUMNS){
            rows = MainActivity.GRID_ROWS;
            columns = MainActivity.GRID_COLUMNS;
        }else {
            rows = MainActivity.GRID_COLUMNS;
            columns = MainActivity.GRID_ROWS;
        }
    }

    private void createButtonsInArray2D() {
        buttonsArray2D = new CustomButton[rows][columns];
    }

    private void setupUndoButton() {
        imgButtonUndo = findViewById(R.id.imageButtonUndo);
        imgButtonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo();
            }
        });
    }

    private void setupExitButton() {
        imgButtonExit = findViewById(R.id.imageButtonExit);
        imgButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupCurrentShapeButton() {
        currentShapeButton = findViewById(R.id.imageButtonCurrentShape);
    }


    private void tableGridCreator(){
        setupTableRowParams();
        createRowsColumnsAndButtons();
    }
//
//    private void assignLists() {
//        winInRow = new HashSet<>();
//        winInColumn = new HashSet<>();
//        winInDecreasing = new HashSet<>();
//        winInIncreasing = new HashSet<>();
//    }
//
//    private void assignHashMapAndLists() {
//        usedButtons =new ArrayList<>();
//        hashMapOfPlayersMoves = new HashMap<>(numberOfPlayers);
//        for(int i = 0; i<numberOfPlayers; i++){
//            hashMapOfPlayersMoves.put(i, assignHashSets() );
//        }
//    }
//
//    private HashMap<Integer, HashSet<CustomButton>> assignHashSets() {
//        hashMap= new HashMap<>();
//
//        HashSet<CustomButton> winInRowInner = new HashSet<>(3);
//        HashSet<CustomButton> winInColumnInner = new HashSet<>(3);
//        HashSet<CustomButton> winInDecreasingInner = new HashSet<>(3);
//        HashSet<CustomButton> winInIncreasingInner = new HashSet<>(3);
//
//        hashMap.put(0, winInRowInner);
//        hashMap.put(1, winInColumnInner);
//        hashMap.put(2, winInDecreasingInner);
//        hashMap.put(3, winInIncreasingInner);
//
//
//        return hashMap;
//    }

    private void setBackgroundMode() {
        LinearLayout backgroundColor = findViewById(R.id.playGridBackground);
        TableLayout playFiledBackground = findViewById(R.id.playFieldLayout);
        if(MainActivity.darkMode){
            backgroundColor.setBackgroundResource(R.color.colorBlack);
            playFiledBackground.setBackgroundResource(R.drawable.background_white);
            buttonBackgroundColor = android.R.color.black;
        } else {
            backgroundColor.setBackgroundResource(R.color.colorWhite);
            playFiledBackground.setBackgroundResource(R.drawable.background);
            buttonBackgroundColor = android.R.color.white;
        }
    }


    private void setupTableRowParams() {
        tableRowParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f );
        setMarginsSize();
        tableRowParams.setMargins(marginSize, marginSize, marginSize, marginSize);
    }

    private void setMarginsSize() {
        if(rows*columns>120)
            marginSize = 3;
        else if(rows*columns>60)
            marginSize = 5;
        else
            marginSize = 10;
    }

    private void createRowsColumnsAndButtons() {
        tablePlayField = findViewById(R.id.playFieldLayout);
        for(int row = 0; row < rows; row++){
            TableRow tableRow = createAndSetupTableRow(tablePlayField);
            for(int col = 0; col < columns; col++){
                CustomButton button = createAndSetupButton(tableRowParams, row, col);
                tableRow.addView(button);
                addButtonToArray2D(row, col, button);
            }
        }

    }

    private TableRow createAndSetupTableRow(TableLayout tablePlayField) {
        TableRow tableRow = new TableRow(this);
        tablePlayField.addView(tableRow);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f ));
        return tableRow;
    }

    private CustomButton createAndSetupButton(TableRow.LayoutParams tableRowParams, int row, int col) {
        CustomButton button = new CustomButton(this);
        button.setLayoutParams(tableRowParams);
        button.setPadding(0,0,0,0 );
        String nameID = convertRowColumnToName(row, col);
        int buttonID = Integer.parseInt("9" + nameID);
        button.setId(buttonID);
        button.setBackgroundResource(buttonBackgroundColor);
        button.setOnClickListener(this);
        button.setName(nameID);
        button.setImInRow(row);
        button.setImInColumn(col);
        return button;
    }

    private String convertRowColumnToName(int row, int column) {
        String tmpRow = "00" + row;
        tmpRow = tmpRow.substring(tmpRow.length()-3);
        String tmpColumn = "00" + column;
        tmpColumn = tmpColumn.substring(tmpColumn.length()-3);

        return tmpRow + tmpColumn;
    }

    private void addButtonToArray2D(int row, int col, CustomButton button) {
        buttonsArray2D[row][col] = button;
    }

    private void lockButtonSizes() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                CustomButton button = buttonsArray2D[row][col];

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
        if(currentPlayer<numberOfPlayers){
            currentPlayer = currentNumber +1;
        }else {
            currentPlayer = 1;
        }
    }

    public void previousPlayer(){
        if(currentPlayer>1){
            currentPlayer--;
        }else {
            currentPlayer = numberOfPlayers;
        }
    }

    public void previousShape(){
        if(previousShape >1){
            previousShape--;
        }else {
            previousShape = numberOfPlayers;
        }
        if(currentShape >1){
            currentShape--;
        }else {
            currentShape = numberOfPlayers;
        }
    }

    public void previousShape(int player){
        if(currentShape == 1 && previousShape == 2 && currentPlayer != 2){
            previousShape = 1;
        }else {

            if(currentShape == previousShape && currentShape !=1){
                currentShape--;
                previousShape--;
            }
            previousShape = currentShape;
            currentShape = player;
        }
    }

    private void undo() {
        imgButtonUndo.setClickable(false);
        if(tmpButtonID!=null) {
            previousPlayer();
            previousShape();
            setCurrentShapeButton(previousShape);
            setButtonImage(tmpButtonID, previousShape);
            tmpButtonID.setClickable(true);
            tmpButtonID.setImageDrawable(null);
        }

    }

    public void setCurrentShapeButton(int player){
        switch (player){
             case 1:
                 currentShapeButton.setImageResource(R.drawable.ring);
                 break;
             case 2:
                 currentShapeButton.setImageResource(R.drawable.x);
                 break;
             case 3:
                 currentShapeButton.setImageResource(R.drawable.trojkat);
                 break;

             case 4:
                currentShapeButton.setImageResource(R.drawable.kwadrat);
                break;

             case 5:
                currentShapeButton.setImageResource(R.drawable.star);
                break;

             case 6:
                currentShapeButton.setImageResource(R.drawable.trapez);
                break;
        }

    }

    public void setButtonImage(CustomButton button, int buttonShape){
        switch (buttonShape){
            case 1:
                button.setImageDrawable(imageSizeForButton(R.drawable.ring, button));
                break;

            case 2:
                button.setImageDrawable(imageSizeForButton(R.drawable.x, button));
                break;

            case 3:
                button.setImageDrawable(imageSizeForButton(R.drawable.trojkat, button));
                break;

            case 4:
                button.setImageDrawable(imageSizeForButton(R.drawable.kwadrat, button));
                break;

            case 5:
                button.setImageDrawable(imageSizeForButton(R.drawable.star, button));
                break;

            case 6:
                button.setImageDrawable(imageSizeForButton(R.drawable.trapez, button));
                break;
        }

    }

    public BitmapDrawable imageSizeForButton(int drawableRes, CustomButton button){
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resources = getResources();
        return new BitmapDrawable(resources, scaledBitmap);
    }


    @Override
    public void onClick(View v) {
        v.setClickable(false);
        tmpButtonID = findViewById(v.getId());
        lockButtonSizes();
        setCurrentShapeButton(currentPlayer);
        setButtonImage(tmpButtonID, previousShape);
        tmpButtonID.setMyShape(previousShape);
//        checkForWin(previousShape);
        winningEngine.start(previousShape);
        currentPlayer(currentShape);
        previousShape(currentPlayer);
        imgButtonUndo.setClickable(true);
    }

//    private void checkForWin(int previousShape) {
//        usedButtons.clear();
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < columns; col++) {
//                if (buttonsArray2D[row][col].getMyShape() == previousShape){
//                    usedButtons.add(buttonsArray2D[row][col]);
//                }
//            }
//        }
//        searchForWinningButtons();
//
////        for(CustomButton customButton : usedButtons){
////            System.out.println(customButton.getId());
////        }
//    }

//    private void searchForWinningButtons() {
//        for(int i = 0; i < usedButtons.size()-1; i++){
//            CustomButton tmpButton1 = usedButtons.get(i);
//            CustomButton tmpButton2 = usedButtons.get(i+1);
////            winInRow.add(tmpButton1);
//
//            addButtonToTheRightHashSet(tmpButton1, tmpButton2);
//        }
//
////        System.out.println("size in winInRow: " + winInRow.size());
////        System.out.println("size in winInColumn: " + winInColumn.size());
////        System.out.println("size in winInDecreasing: " + winInDecreasing.size());
////        System.out.println("size in winInIncreasing: " + winInIncreasing.size());
//    }
//
//    private void addButtonToTheRightHashSet(CustomButton tmpButton1, CustomButton tmpButton2) {
//        assignTmpList();
//
//        int tmpRowBtn = tmpButton1.getImInRow();
//        int tmpRowBtn2 = tmpButton2.getImInRow();
//        int tmpRowBtnIncreased = tmpButton2.getImInRow()-1;
//        int tmpColBtn = tmpButton1.getImInColumn();
//        int tmpColBtn2 = tmpButton2.getImInColumn();
//        int tmpColBtnIncreased = tmpButton2.getImInColumn()-1;
//        int tmpColBtnDecreased = tmpButton2.getImInColumn()+1;
////        System.out.println("Button 1 row: " + tmpButton1.getImInRow() + ", col: " + tmpButton1.getImInColumn());
////        System.out.println("Button 2 row: " + tmpButton2.getImInRow() + ", col: " + tmpButton2.getImInColumn());
////        System.out.println("button shape: " + tmpButton1.getMyShape());
////        System.out.println("tmpRowBtn == tmpRowBtn2:");
////        System.out.println(tmpRowBtn == tmpRowBtn2);
////        System.out.println("tmpColBtn  == tmpColBtnIncreased: ");
////        System.out.println(tmpColBtn  == tmpColBtnIncreased);
////        System.out.println("tmpRowBtn == tmpRowBtnIncreased");
////        System.out.println(tmpRowBtn == tmpRowBtnIncreased);
////        System.out.println("tmpColBtn == tmpColBtn2");
////        System.out.println(tmpColBtn == tmpColBtn2);
////        System.out.println("tmpColBtn == tmpColBtnDecreased");
////        System.out.println(tmpColBtn == tmpColBtnDecreased);
////        System.out.println("-------------------------------------");
////        HashSet<CustomButton> hashRow = new HashSet<>();
////        HashSet<CustomButton> hashCol = new HashSet<>();
////        HashSet<CustomButton> hashDec = new HashSet<>();
////        HashSet<CustomButton> hashInc = new HashSet<>();
////        HashMap<Integer, HashSet<CustomButton>> hashMap = new HashMap<>();
//        if(tmpRowBtn == tmpRowBtn2 && tmpColBtn  == tmpColBtnIncreased){
////            hashRow.add(tmpButton1);
////            hashRow.add(tmpButton2);
//
////            winInRow.add(tmpButton1);
////            winInRow.add(tmpButton2);
////            hashMap.put(0, winInRow);
////            hashMapOfPlayersMoves.put(previousShape, hashMap);
////            System.out.println(hashMapOfPlayersMoves.get(previousShape).size());
////            System.out.println(hashMapOfPlayersMoves.get(previousShape).get(0).size());
//            hashMapOfPlayersMoves.get(previousShape).get(0).add(tmpButton1);
//            hashMapOfPlayersMoves.get(previousShape).get(0).add(tmpButton2);
//            hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(0));
//            System.out.println("win in Row: " + hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(0)).size());
//        }
//        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtn2){
////            hashCol.add(tmpButton1);
////            hashCol.add(tmpButton2);
//
////            winInColumn.add(tmpButton1);
////            winInColumn.add(tmpButton2);
////            hashMap.put(1, winInColumn);
//            hashMapOfPlayersMoves.get(previousShape).get(1).add(tmpButton1);
//            hashMapOfPlayersMoves.get(previousShape).get(1).add(tmpButton2);
//            hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(1));
//            System.out.println("win in Column: " + hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(1)).size());
//        }
//        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtnIncreased){
////            hashDec.add(tmpButton1);
////            hashDec.add(tmpButton2);
//
////            winInDecreasing.add(tmpButton1);
////            winInDecreasing.add(tmpButton2);
////            hashMap.put(2, winInDecreasing);
//            hashMapOfPlayersMoves.get(previousShape).get(2).add(tmpButton1);
//            hashMapOfPlayersMoves.get(previousShape).get(2).add(tmpButton2);
//            hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(2));
//            System.out.println("win in Decreasing: " + hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(2)).size());
//        }
//        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtnDecreased){
////            hashInc.add(tmpButton1);
////            hashInc.add(tmpButton2);
//
////            winInIncreasing.add(tmpButton1);
////            winInIncreasing.add(tmpButton2);
////            hashMap.put(3, winInIncreasing);
//            hashMapOfPlayersMoves.get(previousShape).get(3).add(tmpButton1);
//            hashMapOfPlayersMoves.get(previousShape).get(3).add(tmpButton2);
//            hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(3));
//            System.out.println("win in Increasing: " + hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(3)).size());
//        }
////        checkNumberOfButtonsInTheLine(hashRow, hashCol, hashDec, hashInc);
//        checkNumberOfButtonsInTheLine();
//    }
//
//    private void assignTmpList() {
//        System.out.println("previous shape: " + previousShape);
//        System.out.println(winInRow.size());
//        System.out.println(hashMapOfPlayersMoves.get(0).get(0).isEmpty());
////        winInRow = hashMapOfPlayersMoves.get(previousShape).get(0);
////        winInColumn = hashMapOfPlayersMoves.get(previousShape).get(1);
////        winInDecreasing = hashMapOfPlayersMoves.get(previousShape).get(2);
////        winInIncreasing = hashMapOfPlayersMoves.get(previousShape).get(3);
//    }
//
//    private void checkNumberOfButtonsInTheLine(/*HashSet<CustomButton> hashRow, HashSet<CustomButton> hashCol, HashSet<CustomButton> hashDec, HashSet<CustomButton> hashInc*/) {
//        System.out.println("___________________________________________");
////        System.out.println("Lista Row:");
////        for(Object list : winInRow){
////            System.out.println(list.hashCode());
////        }
////        System.out.println("Lista Columns:");
////        for(Object list : winInColumn){
////            System.out.println(list.hashCode());
////        }
////        System.out.println("Lista Decreasnig:");
////        for(Object list : winInDecreasing){
////            System.out.println(list.hashCode());
////        }
////        System.out.println("Lista Increasing:");
////        for(Object list : winInIncreasing){
////            System.out.println(list.hashCode());
////        }
////        if(hashRow.size() >= inLineToWin){
////            setWinner(winInRow, "row");
////        }
////        if(hashCol.size() >= inLineToWin){
////            setWinner(winInColumn, "col");
////        }
////        if(hashDec.size() >= inLineToWin){
////            setWinner(winInDecreasing, "dec");
////        }
////        if(hashInc.size() >= inLineToWin){
////            setWinner(winInIncreasing, "inc");
////        }
//        if(hashMapOfPlayersMoves.get(previousShape).get(0).size() >= inLineToWin){
//            setWinner(winInRow, "row");
//        }
//        if(hashMapOfPlayersMoves.get(previousShape).get(1).size() >= inLineToWin){
//            setWinner(winInColumn, "col");
//        }
//        if(hashMapOfPlayersMoves.get(previousShape).get(2).size() >= inLineToWin){
//            setWinner(winInDecreasing, "dec");
//        }
//        if(hashMapOfPlayersMoves.get(previousShape).get(3).size() >= inLineToWin){
//            setWinner(winInIncreasing, "inc");
//        }
//    }
//
//    private void setWinner(HashSet winnerList, String direction) {
//        System.out.println("lista zwycięsców HashSet=====!!!!!!!!!!!!!!!!!: " + direction);
//
//    }

}

