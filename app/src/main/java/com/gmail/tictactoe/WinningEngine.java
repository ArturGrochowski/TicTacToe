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
    private HashMap<Integer, HashMap<Integer, HashSet<CustomButton>>> hashMapOfPlayersMoves;


    public WinningEngine(CustomButton[][] buttonsArray2D, int numberOfPlayers, int previousShape, int inLineToWin, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.inLineToWin = inLineToWin;
        this.previousShape = previousShape;
        this.numberOfPlayers = numberOfPlayers;
        this.buttonsArray2D = buttonsArray2D;
    }

    public void start(int previousShape){
        this.previousShape = previousShape;
        assignHashMapAndLists();
        test();
        checkForWin(previousShape);
    }

    private void test() {
//
//        hashMapOfPlayersMoves.get(0).get(0).add(buttonsArray2D[0][0]);
//        hashMapOfPlayersMoves.get(0).get(0).add(buttonsArray2D[0][1]);
//        System.out.println("Test:");
//        System.out.println(hashMapOfPlayersMoves.get(0).get(0).size());
//        System.out.println(hashMapOfPlayersMoves.get(1).get(2).size());
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

    }
    private void searchForWinningButtons() {
        for(int i = 0; i < usedButtons.size()-1; i++){
            CustomButton tmpButton1 = usedButtons.get(i);
            CustomButton tmpButton2 = usedButtons.get(i+1);
            addButtonToTheRightHashSet(tmpButton1, tmpButton2);
        }

        System.out.println("searchForWinningButtons() usedButttons.size() usedButtons.get(0).getId() :");
        System.out.println(usedButtons.size());
        System.out.println(usedButtons.get(0).getId());

    }

    private void addButtonToTheRightHashSet(CustomButton tmpButton1, CustomButton tmpButton2) {
//        assignTmpList();

        int tmpRowBtn = tmpButton1.getImInRow();
        int tmpRowBtn2 = tmpButton2.getImInRow();
        int tmpRowBtnIncreased = tmpButton2.getImInRow()-1;
        int tmpColBtn = tmpButton1.getImInColumn();
        int tmpColBtn2 = tmpButton2.getImInColumn();
        int tmpColBtnIncreased = tmpButton2.getImInColumn()-1;
        int tmpColBtnDecreased = tmpButton2.getImInColumn()+1;
        System.out.println("addButtonToTheRightHashSet: tmpButton1.getName():");
        System.out.println(tmpButton1.getName());
        if(tmpRowBtn == tmpRowBtn2 && tmpColBtn  == tmpColBtnIncreased){
            System.out.println("previousShape: " + previousShape);
            hashMapOfPlayersMoves.get(previousShape-1).get(0).add(tmpButton1);
            hashMapOfPlayersMoves.get(previousShape-1).get(0).add(tmpButton2);
        }
        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtn2){
            hashMapOfPlayersMoves.get(previousShape-1).get(1).add(tmpButton1);
            hashMapOfPlayersMoves.get(previousShape-1).get(1).add(tmpButton2);
        }
        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtnIncreased){
            hashMapOfPlayersMoves.get(previousShape-1).get(2).add(tmpButton1);
            hashMapOfPlayersMoves.get(previousShape-1).get(2).add(tmpButton2);
        }
        if(tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtnDecreased){
            hashMapOfPlayersMoves.get(previousShape-1).get(3).add(tmpButton1);
            hashMapOfPlayersMoves.get(previousShape-1).get(3).add(tmpButton2);
        }
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

    private void checkNumberOfButtonsInTheLine() {
        if(hashMapOfPlayersMoves.get(previousShape-1).get(0).size() >= inLineToWin){
            setWinner(winInRow, "row");
        }
        if(hashMapOfPlayersMoves.get(previousShape-1).get(1).size() >= inLineToWin){
            setWinner(winInColumn, "col");
        }
        if(hashMapOfPlayersMoves.get(previousShape-1).get(2).size() >= inLineToWin){
            setWinner(winInDecreasing, "dec");
        }
        if(hashMapOfPlayersMoves.get(previousShape-1).get(3).size() >= inLineToWin){
            setWinner(winInIncreasing, "inc");
        }
    }

    private void setWinner(HashSet winnerList, String direction) {
        System.out.println("lista zwycięsców HashSet=====!!!!!!!!!!!!!!!!!: " + direction);

    }
}
