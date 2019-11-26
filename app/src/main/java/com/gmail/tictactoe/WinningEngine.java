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
        Collections.sort(decButtons);
        System.out.println("decButton size: "+decButtons.size());

        for(int i = 0; i<decButtons.size(); i++){
            searchRowAndColTest(decButtons, i);
        }
        System.out.println("-------------------------------PLAYER NUMBER: " + previousShape);

    }


    private void searchRowAndColTest (List<CustomButton> decButtons, int index){
        for (CustomButton cb : decButtons) {
            System.out.println(cb.getName());
        }
        int row = decButtons.get(index).getImInRow();
        int column = decButtons.get(index).getImInColumn();
        for(int i = 0; i<decButtons.size(); i++){
            if(decButtons.get(i).getImInRow() == row || decButtons.get(i).getImInColumn() == column){
                System.out.println("adding button: " + decButtons.get(i).getName());
                inOneLineHashSet.add(decButtons.get(index));
                inOneLineHashSet.add(decButtons.get(i));
                if(inOneLineHashSet.size()>=inLineToWin){
                    setWinner();
                    inOneLineHashSet.clear();
                } else if(index+1 < decButtons.size()){

                    searchRowAndColTest(decButtons, index +1);
                }

            }
        }
        inOneLineHashSet.clear();
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
