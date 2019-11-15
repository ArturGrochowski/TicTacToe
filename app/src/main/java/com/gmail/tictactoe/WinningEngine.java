package com.gmail.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WinningEngine {
    private int rows;
    private int columns;
    private int inLineToWin;
    private int previousShape;
    private int numberOfPlayers;
    private CustomButton[][] buttonsArray2D;
    private ArrayList<CustomButton> usedButtons;
    private HashSet<CustomButton> winInRow;
    private HashSet<CustomButton> winInColumn;
    private HashSet<CustomButton> winInDecreasing;
    private HashSet<CustomButton> winInIncreasing;
//    private HashMap<Integer, HashSet<CustomButton>> hashMap;
    private HashMap<Integer, HashMap<Integer, HashSet<CustomButton>>> hashMapOfPlayersMoves;

    public WinningEngine(CustomButton[][] buttonsArray2D, int numberOfPlayers, int previousShape, int inLineToWin, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.inLineToWin = inLineToWin;
        this.previousShape = previousShape;
        this.numberOfPlayers = numberOfPlayers;
        this.buttonsArray2D = buttonsArray2D;
//        hashMap = new HashMap<>();
    }

    public void start(int previousShape){
        this.previousShape = previousShape;
        assignHashMapAndLists();
        checkForWin(previousShape);
    }


    private void assignLists() {
        winInRow = new HashSet<>();
        winInColumn = new HashSet<>();
        winInDecreasing = new HashSet<>();
        winInIncreasing = new HashSet<>();
    }

    private void assignHashMapAndLists() {
        usedButtons = new ArrayList<>();
        hashMapOfPlayersMoves = new HashMap<>(numberOfPlayers);
        for(int i = 0; i<numberOfPlayers; i++){
            hashMapOfPlayersMoves.put(i, assignHashSets() );
        }
    }

    private HashMap<Integer, HashSet<CustomButton>> assignHashSets() {
        HashMap<Integer, HashSet<CustomButton>> hashMap= new HashMap<>();

        HashSet<CustomButton> winInRowInner = new HashSet<>(3);
        HashSet<CustomButton> winInColumnInner = new HashSet<>(3);
        HashSet<CustomButton> winInDecreasingInner = new HashSet<>(3);
        HashSet<CustomButton> winInIncreasingInner = new HashSet<>(3);

        hashMap.put(0, winInRowInner);
        hashMap.put(1, winInColumnInner);
        hashMap.put(2, winInDecreasingInner);
        hashMap.put(3, winInIncreasingInner);


        return hashMap;
    }

    private void checkForWin(int previousShape) {
        usedButtons.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (buttonsArray2D[row][col].getMyShape() == previousShape){
                    usedButtons.add(buttonsArray2D[row][col]);
                }
            }
        }
        searchForWinningButtons();

//        for(CustomButton customButton : usedButtons){
//            System.out.println(customButton.getId());
//        }
    }
    private void searchForWinningButtons() {
        for(int i = 0; i < usedButtons.size()-1; i++){
            CustomButton tmpButton1 = usedButtons.get(i);
            CustomButton tmpButton2 = usedButtons.get(i+1);
//            winInRow.add(tmpButton1);
            System.out.println(tmpButton1.getName() + " " + tmpButton2.getName());
            addButtonToTheRightHashSet(tmpButton1, tmpButton2);
        }

//        System.out.println("size in winInRow: " + winInRow.size());
//        System.out.println("size in winInColumn: " + winInColumn.size());
//        System.out.println("size in winInDecreasing: " + winInDecreasing.size());
//        System.out.println("size in winInIncreasing: " + winInIncreasing.size());
    }

    private void addButtonToTheRightHashSet(CustomButton tmpButton1, CustomButton tmpButton2) {
        assignTmpList();

        int tmpRowBtn = tmpButton1.getImInRow();
        int tmpRowBtn2 = tmpButton2.getImInRow();
        int tmpRowBtnIncreased = tmpButton2.getImInRow()-1;
        int tmpColBtn = tmpButton1.getImInColumn();
        int tmpColBtn2 = tmpButton2.getImInColumn();
        int tmpColBtnIncreased = tmpButton2.getImInColumn()-1;
        int tmpColBtnDecreased = tmpButton2.getImInColumn()+1;
//        System.out.println("Button 1 row: " + tmpButton1.getImInRow() + ", col: " + tmpButton1.getImInColumn());
//        System.out.println("Button 2 row: " + tmpButton2.getImInRow() + ", col: " + tmpButton2.getImInColumn());
//        System.out.println("button shape: " + tmpButton1.getMyShape());
//        System.out.println("tmpRowBtn == tmpRowBtn2:");
//        System.out.println(tmpRowBtn == tmpRowBtn2);
//        System.out.println("tmpColBtn  == tmpColBtnIncreased: ");
//        System.out.println(tmpColBtn  == tmpColBtnIncreased);
//        System.out.println("tmpRowBtn == tmpRowBtnIncreased");
//        System.out.println(tmpRowBtn == tmpRowBtnIncreased);
//        System.out.println("tmpColBtn == tmpColBtn2");
//        System.out.println(tmpColBtn == tmpColBtn2);
//        System.out.println("tmpColBtn == tmpColBtnDecreased");
//        System.out.println(tmpColBtn == tmpColBtnDecreased);
//        System.out.println("-------------------------------------");
//        HashSet<CustomButton> hashRow = new HashSet<>();
//        HashSet<CustomButton> hashCol = new HashSet<>();
//        HashSet<CustomButton> hashDec = new HashSet<>();
//        HashSet<CustomButton> hashInc = new HashSet<>();
//        HashMap<Integer, HashSet<CustomButton>> hashMap = new HashMap<>();
        if(tmpRowBtn == tmpRowBtn2 && tmpColBtn  == tmpColBtnIncreased){
//            hashRow.add(tmpButton1);
//            hashRow.add(tmpButton2);

//            winInRow.add(tmpButton1);
//            winInRow.add(tmpButton2);
//            hashMap.put(0, winInRow);
//            hashMapOfPlayersMoves.put(previousShape, hashMap);
//            System.out.println(hashMapOfPlayersMoves.get(previousShape).size());
//            System.out.println(hashMapOfPlayersMoves.get(previousShape).get(0).size());
            hashMapOfPlayersMoves.get(previousShape).get(0).add(tmpButton1);
            hashMapOfPlayersMoves.get(previousShape).get(0).add(tmpButton2);
            hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(0));
            System.out.println("win in Row: " + hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(0)).size());
        }
        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtn2){
//            hashCol.add(tmpButton1);
//            hashCol.add(tmpButton2);

//            winInColumn.add(tmpButton1);
//            winInColumn.add(tmpButton2);
//            hashMap.put(1, winInColumn);
            hashMapOfPlayersMoves.get(previousShape).get(1).add(tmpButton1);
            hashMapOfPlayersMoves.get(previousShape).get(1).add(tmpButton2);
            hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(1));
            System.out.println("win in Column: " + hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(1)).size());
        }
        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtnIncreased){
//            hashDec.add(tmpButton1);
//            hashDec.add(tmpButton2);

//            winInDecreasing.add(tmpButton1);
//            winInDecreasing.add(tmpButton2);
//            hashMap.put(2, winInDecreasing);
            hashMapOfPlayersMoves.get(previousShape).get(2).add(tmpButton1);
            hashMapOfPlayersMoves.get(previousShape).get(2).add(tmpButton2);
            hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(2));
            System.out.println("win in Decreasing: " + hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(2)).size());
        }
        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtnDecreased){
//            hashInc.add(tmpButton1);
//            hashInc.add(tmpButton2);

//            winInIncreasing.add(tmpButton1);
//            winInIncreasing.add(tmpButton2);
//            hashMap.put(3, winInIncreasing);
            hashMapOfPlayersMoves.get(previousShape).get(3).add(tmpButton1);
            hashMapOfPlayersMoves.get(previousShape).get(3).add(tmpButton2);
            hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(3));
            System.out.println("win in Increasing: " + hashMapOfPlayersMoves.put(previousShape, hashMapOfPlayersMoves.get(3)).size());
        }
//        checkNumberOfButtonsInTheLine(hashRow, hashCol, hashDec, hashInc);
        checkNumberOfButtonsInTheLine();
    }

    private void assignTmpList() {
        System.out.println("previous shape: " + previousShape);
//        System.out.println(winInRow.size());
        System.out.println(hashMapOfPlayersMoves.get(0).get(0).isEmpty());
        System.out.println(hashMapOfPlayersMoves.get(0).get(0).size());
//        winInRow = hashMapOfPlayersMoves.get(previousShape).get(0);
//        winInColumn = hashMapOfPlayersMoves.get(previousShape).get(1);
//        winInDecreasing = hashMapOfPlayersMoves.get(previousShape).get(2);
//        winInIncreasing = hashMapOfPlayersMoves.get(previousShape).get(3);
    }

    private void checkNumberOfButtonsInTheLine(/*HashSet<CustomButton> hashRow, HashSet<CustomButton> hashCol, HashSet<CustomButton> hashDec, HashSet<CustomButton> hashInc*/) {
        System.out.println("___________________________________________");
//        System.out.println("Lista Row:");
//        for(Object list : winInRow){
//            System.out.println(list.hashCode());
//        }
//        System.out.println("Lista Columns:");
//        for(Object list : winInColumn){
//            System.out.println(list.hashCode());
//        }
//        System.out.println("Lista Decreasnig:");
//        for(Object list : winInDecreasing){
//            System.out.println(list.hashCode());
//        }
//        System.out.println("Lista Increasing:");
//        for(Object list : winInIncreasing){
//            System.out.println(list.hashCode());
//        }
//        if(hashRow.size() >= inLineToWin){
//            setWinner(winInRow, "row");
//        }
//        if(hashCol.size() >= inLineToWin){
//            setWinner(winInColumn, "col");
//        }
//        if(hashDec.size() >= inLineToWin){
//            setWinner(winInDecreasing, "dec");
//        }
//        if(hashInc.size() >= inLineToWin){
//            setWinner(winInIncreasing, "inc");
//        }
        if(hashMapOfPlayersMoves.get(previousShape).get(0).size() >= inLineToWin){
            setWinner(winInRow, "row");
        }
        if(hashMapOfPlayersMoves.get(previousShape).get(1).size() >= inLineToWin){
            setWinner(winInColumn, "col");
        }
        if(hashMapOfPlayersMoves.get(previousShape).get(2).size() >= inLineToWin){
            setWinner(winInDecreasing, "dec");
        }
        if(hashMapOfPlayersMoves.get(previousShape).get(3).size() >= inLineToWin){
            setWinner(winInIncreasing, "inc");
        }
    }

    private void setWinner(HashSet winnerList, String direction) {
        System.out.println("lista zwycięsców HashSet=====!!!!!!!!!!!!!!!!!: " + direction);

    }
}
