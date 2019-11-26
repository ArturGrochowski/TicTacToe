package com.gmail.tictactoe;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class WinningEngine {
    private int rows;
    private int columns;
    private int inLineToWin;
    private int previousShape;
    private int numberOfPlayers;
    private CustomButton[][] buttonsArray2D;
    private ArrayList<CustomButton> usedButtons;
    private HashSet<CustomButton> inOneLineHashSet;
    private HashMap<Integer, HashMap<Integer, HashSet<CustomButton>>> hashMapOfPlayersMoves;


    public WinningEngine(CustomButton[][] buttonsArray2D, int numberOfPlayers, int previousShape, int inLineToWin, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.inLineToWin = inLineToWin;
        this.previousShape = previousShape;
        this.numberOfPlayers = numberOfPlayers;
        this.buttonsArray2D = buttonsArray2D;
        inOneLineHashSet  = new HashSet<>();
    }

    public void start(int previousShape){
        this.previousShape = previousShape;
        assignHashMapAndLists();
        checkForWin(previousShape);
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
            addButtonToTheRightHashSet(tmpButton1);
        }
    }

    private void addButtonToTheRightHashSet(CustomButton tmpButton1) {

        int tmpRowBtn = tmpButton1.getImInRow();
        int tmpColBtn = tmpButton1.getImInColumn();


        for(int i = 0; i < usedButtons.size()-1; i++) {
            CustomButton tmpButton2 = usedButtons.get(i + 1);


            int tmpRowBtn2 = tmpButton2.getImInRow();
            int tmpRowBtnIncreased = tmpButton2.getImInRow() - 1;
            int tmpColBtn2 = tmpButton2.getImInColumn();
            int tmpColBtnIncreased = tmpButton2.getImInColumn() - 1;
            int tmpColBtnDecreased = tmpButton2.getImInColumn() + 1;


            if (tmpRowBtn == tmpRowBtn2 && tmpColBtn == tmpColBtnIncreased) {
                hashMapOfPlayersMoves.get(previousShape - 1).get(0).add(tmpButton1);
                hashMapOfPlayersMoves.get(previousShape - 1).get(0).add(tmpButton2);
            }
            if (tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtn2) {
                hashMapOfPlayersMoves.get(previousShape - 1).get(1).add(tmpButton1);
                hashMapOfPlayersMoves.get(previousShape - 1).get(1).add(tmpButton2);
            }
            if (tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtnIncreased) {
                hashMapOfPlayersMoves.get(previousShape - 1).get(2).add(tmpButton1);
                hashMapOfPlayersMoves.get(previousShape - 1).get(2).add(tmpButton2);
            }
            if (tmpRowBtn == tmpRowBtnIncreased && tmpColBtn == tmpColBtnDecreased) {
                hashMapOfPlayersMoves.get(previousShape - 1).get(3).add(tmpButton1);
                hashMapOfPlayersMoves.get(previousShape - 1).get(3).add(tmpButton2);
            }
        }
        checkNumberOfButtonsInTheLine();
    }


    private void checkNumberOfButtonsInTheLine() {

        for (int i = 0; i < rows; i++){
            checkRowInLine(i);
        }

        for (int i = 0; i < columns; i++){
            checkColumnInLine(i);
        }

            checkDecreasInLine();


        for (int i = 0; i < rows; i++){
            checkIncreasInLine(i);
        }

    }

    private void checkRowInLine(int index) {

        ArrayList<CustomButton> rowButtons = new ArrayList<>(hashMapOfPlayersMoves.get(previousShape-1).get(0));

        for(int i = 0; i<rowButtons.size(); i++){
            if(rowButtons.get(i).getImInRow() == index){
                inOneLineHashSet.add(rowButtons.get(i));
            }
        }
        inALineToWin();
        test(rowButtons);
    }

    private void checkColumnInLine(int columnIndex){
        List<CustomButton> colButtons = new ArrayList<>(hashMapOfPlayersMoves.get(previousShape-1).get(1));

        for(int i = 0; i<colButtons.size(); i++){
            if(colButtons.get(i).getImInColumn() == columnIndex){
                inOneLineHashSet.add(colButtons.get(i));
            }
        }
        inALineToWin();
        test(colButtons);
    }

    private void checkDecreasInLine(){
        List<CustomButton> decButtons = new ArrayList<>(hashMapOfPlayersMoves.get(previousShape-1).get(2));
//        Collections.sort(decButtons);
        System.out.println("decButton size: "+decButtons.size());

        for(int i = 1; i<decButtons.size(); i++){
            if(isSecondBtnPositionDecreasing(decButtons, i)){
                System.out.println("adding decButtons");
                System.out.println("loop: " + i);
                inOneLineHashSet.add(decButtons.get(i-1));
                inOneLineHashSet.add(decButtons.get(i));
            }
        }
        System.out.println("-------------PLAYER NUMBER: " + previousShape);

        System.out.println("inOne... size: " + inOneLineHashSet.size());
        Iterator<CustomButton> rowIter = inOneLineHashSet.iterator();
//        System.out.println("HashSet: hasNext: " + rowIter.hasNext());
        while (rowIter.hasNext()) {
            System.out.println("while");
            System.out.println(rowIter.next().getName());

        }
        inALineToWin();
//        test(decButtons);
//        test(inOneLineHashSet);
    }


    private boolean isSecondBtnPositionDecreasing(List<CustomButton> decButtons, int i) {
        boolean isBigger = false;
        System.out.println("boolean i: " + i);
        System.out.println("btn index 0 row = " + decButtons.get(0).getImInRow());
        System.out.println("btn index 1 row = " + decButtons.get(1).getImInRow());
        int imInRow = decButtons.get(i-1).getImInRow();
        int imInCol = decButtons.get(i-1).getImInColumn();

        if (searchNextRow(decButtons, imInRow) || searchNextColumn(decButtons, imInCol)){
            System.out.println("Im BIGGER");
            isBigger = true;
        }
//        int imInRow2;
//        int imInCol2;
//        for (int j = 0; j<decButtons.size(); j++) {
//            System.out.println("loop j: " + j);
//            imInRow2 = decButtons.get(j).getImInRow() + 1;
//            System.out.println("btn in loop index " + j + " = " + imInRow2);
//            if(imInRow == imInRow2) {
//                System.out.println("if 1");
//                imInCol2 = decButtons.get(j).getImInColumn() + 1;
//                if(imInCol == imInCol2){
//                    System.out.println("if 2");
//
//                    isBigger = true;
//                }
//            }
//        }
        return isBigger;
    }

    private boolean searchNextRow (List<CustomButton> decButtons, int currentRow) {
        boolean itHas = false;
        System.out.println("CurrentRow: " + currentRow);
        int nexRow = 999;
        for(int i = 0; i<decButtons.size(); i++){
            nexRow  = decButtons.get(i).getImInRow()+1;
            System.out.println("NextRow Loop");
            if(currentRow == nexRow){
                itHas = true;
            }
        }

        System.out.println("NextRow: "+ nexRow);

        System.out.println("row has = " + itHas);
        return itHas;
    }

    private boolean searchNextColumn (List<CustomButton> decButtons, int currentColumn) {
        boolean itHas = false;

        for(int i = 0; i<decButtons.size(); i++){
            System.out.println("NextColumn Loop");
            if(currentColumn == decButtons.get(i).getImInColumn()+1){
                itHas = true;
            }
        }
        System.out.println("column has = " + itHas);

        return itHas;
    }


    private void checkIncreasInLine(int increasIndex){
        ArrayList<CustomButton> incButtons = new ArrayList<>(hashMapOfPlayersMoves.get(previousShape-1).get(3));
//        for(int i = 1; i<incButtons.size(); i++){
//            if(incButtons.get(i-1).getImInRow() == incButtons.get(increasIndex).getImInRow()+1 || incButtons.get(i-1).getImInColumn() == incButtons.get(increasIndex).getImInColumn()-1){
//                inOneLineHashSet.add(incButtons.get(i));
//            }
//        }
        test(incButtons);

        inALineToWin();
    }

    private void test(List<CustomButton> rowButtons) {
//        Iterator<CustomButton> rowIter = rowButtons.iterator();
//        System.out.println("PLAYER NUMBER: " + previousShape);
////        System.out.println("rowButtons ArrayList:");
//        arrayListLoop(rowIter);
////        System.out.println("colButtons ArrayList:");
//        System.out.println("===============================");
    }
    private void test(HashSet<CustomButton> rowButtons) {
        System.out.println("PLAYER NUMBER: " + previousShape);
        Iterator<CustomButton> rowIter = rowButtons.iterator();
        System.out.println("HashSet: hasNext: " + rowIter.hasNext());
        while (rowIter.hasNext()) {
            System.out.println("while");
            System.out.println(rowIter.next().getName());

        }
        System.out.println("===============================");
    }

    private void arrayListLoop(Iterator<CustomButton> winButtons) {
        System.out.println("loop: hasNext: " + winButtons.hasNext());
        if(winButtons.hasNext()) {
            System.out.println("next");
            while (winButtons.hasNext()) {
                System.out.println("while");
                System.out.println(winButtons.next().getName());

            }
        }
    }

    private void inALineToWin() {
//        System.out.println("inOneLineArrayList size: " + inOneLineArrayList.size());
        if(inOneLineHashSet.size()>= inLineToWin){
//            System.out.println("We have the winner!!!");
            setWinner();
        }
        inOneLineHashSet.clear();
    }

    private void setWinner() {
        Iterator<CustomButton> winButtons = inOneLineHashSet.iterator();

        while(winButtons.hasNext()){
            winButtons.next().setBackgroundResource(R.color.colorGray);

        }
    }

}
