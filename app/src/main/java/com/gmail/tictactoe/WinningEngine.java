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

        checkRowInLine();

        checkColumnInLine();


        checkDecreaseInLine();

        checkIncreaseInLine();

    }

    private void checkRowInLine() {
        ArrayList<CustomButton> rowButtons = new ArrayList<>(hashMapOfPlayersMoves.get(previousShape-1).get(0));
        Collections.sort(rowButtons);

        for(int i = 0; i<rowButtons.size(); i++){
            inOneLineHashSet.clear();
            searchRowIncrease(rowButtons, i);
        }
        inOneLineToWin();
    }


    private void searchRowIncrease(List<CustomButton> incButtons, int index){

        int row = incButtons.get(index).getImInRow();
        int column = incButtons.get(index).getImInColumn()+1;

        for(int i = 0; i<incButtons.size(); i++){
            if(incButtons.get(i).getImInRow() == row && incButtons.get(i).getImInColumn() == column){
                inOneLineHashSet.add(incButtons.get(index));
                inOneLineHashSet.add(incButtons.get(i));
                column++;
                inOneLineToWin();
            }
        }
    }


    private void checkColumnInLine(){
        List<CustomButton> colButtons = new ArrayList<>(hashMapOfPlayersMoves.get(previousShape-1).get(1));
        Collections.sort(colButtons);

        for(int i = 0; i<colButtons.size(); i++){
            inOneLineHashSet.clear();
            searchColIncrease(colButtons, i);
        }
        inOneLineToWin();
    }

    private void searchColIncrease(List<CustomButton> incButtons, int index){

        int row = incButtons.get(index).getImInRow()+1;
        int column = incButtons.get(index).getImInColumn();

        for(int i = 0; i<incButtons.size(); i++){
            if(incButtons.get(i).getImInRow() == row && incButtons.get(i).getImInColumn() == column){
                inOneLineHashSet.add(incButtons.get(index));
                inOneLineHashSet.add(incButtons.get(i));
                row++;
                inOneLineToWin();
            }
        }
    }


    private void checkDecreaseInLine(){
        List<CustomButton> decButtons = new ArrayList<>(hashMapOfPlayersMoves.get(previousShape-1).get(2));
        Collections.sort(decButtons);

        for(int i = 0; i<decButtons.size(); i++){
            inOneLineHashSet.clear();
            searchRowAndColDecrease(decButtons, i);
        }

    }


    private void searchRowAndColDecrease(List<CustomButton> decButtons, int index){

        int rowPlus1 = decButtons.get(index).getImInRow()+1;
        int columnPlus1 = decButtons.get(index).getImInColumn()+1;
        for(int i = 0; i<decButtons.size(); i++){
            if(decButtons.get(i).getImInRow() == rowPlus1 && decButtons.get(i).getImInColumn() == columnPlus1){
                inOneLineHashSet.add(decButtons.get(index));
                inOneLineHashSet.add(decButtons.get(i));
                rowPlus1++;
                columnPlus1++;
                inOneLineToWin();
            }
        }
    }



    private void checkIncreaseInLine(){
        ArrayList<CustomButton> incButtons = new ArrayList<>(hashMapOfPlayersMoves.get(previousShape-1).get(3));
        Collections.sort(incButtons);

        for(int i = 0; i<incButtons.size(); i++){
            inOneLineHashSet.clear();
            searchRowAndColIncrease(incButtons, i);
        }
    }

    private void searchRowAndColIncrease(List<CustomButton> incButtons, int index){

        int row = incButtons.get(index).getImInRow()+1;
        int column = incButtons.get(index).getImInColumn()-1;

        for(int i = 0; i<incButtons.size(); i++){
            if(incButtons.get(i).getImInRow() == row && incButtons.get(i).getImInColumn() == column){
                inOneLineHashSet.add(incButtons.get(index));
                inOneLineHashSet.add(incButtons.get(i));
                row++;
                column--;
                inOneLineToWin();
            }
        }
    }


    private void inOneLineToWin() {
        if(inOneLineHashSet.size()>= inLineToWin){
            setWinner();
            inOneLineHashSet.clear();
        }
    }

    private void setWinner() {
        Iterator<CustomButton> winButtons = inOneLineHashSet.iterator();

        while(winButtons.hasNext()){
            winButtons.next().setBackgroundResource(R.drawable.gradient_background);

        }
    }


    public void undo(int backgroundColor, int row, int buttonBackgroundColor) {
        System.out.println("Undo Array:");
        for (int i = 0; i < buttonsArray2D.length; i++){
            for (int j = 0; j < buttonsArray2D.length; j++){
                System.out.println(buttonsArray2D[i][j].getName());
                System.out.println(buttonsArray2D[i][j].getId());
                System.out.println(buttonsArray2D[i][j].getImInRow());
                System.out.println(buttonsArray2D[i][j].getImInColumn());
                System.out.println(buttonsArray2D[i][j].getMyShape());
            }
        }
    }

}
