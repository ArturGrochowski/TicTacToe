package com.gmail.tictactoe;

import android.content.Context;

public class CustomButton extends android.support.v7.widget.AppCompatImageButton {

    private String name;
    private int ImInRow;
    private int ImInColumn;
    private int MyShape;

    public CustomButton(Context context) {
        super(context);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImInRow() {
        return ImInRow;
    }

    public void setImInRow(int imInRow) {
        ImInRow = imInRow;
    }

    public int getImInColumn() {
        return ImInColumn;
    }

    public void setImInColumn(int imInColumn) {
        ImInColumn = imInColumn;
    }

    public int getMyShape() {
        return MyShape;
    }

    public void setMyShape(int myShape) {
        MyShape = myShape;
    }


}
