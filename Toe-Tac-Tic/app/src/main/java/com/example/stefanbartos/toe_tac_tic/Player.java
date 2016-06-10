package com.example.stefanbartos.toe_tac_tic;

import android.graphics.drawable.Drawable;

/**
 * Created by StefanBartos on 09.06.16.
 */
public class Player {
    String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
