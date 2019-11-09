package com.gmail.tictactoe;

import android.content.Context;

public class CustomButton extends android.support.v7.widget.AppCompatImageButton {

    private String name;

    public CustomButton(Context context) {
        super(context);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
