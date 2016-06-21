package com.example.stefanbartos.toe_tac_tic;


/**
 * Created by StefanBartos on 09.06.16.
 */
public class Player {
    String name;
    int punktezahl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPunktezahl() {
        return punktezahl;
    }

    public void setPunktezahl(int punktezahl) {
        this.punktezahl = punktezahl;
    }

    public Player(String name, int punktezahl) {
        this.name = name;
        this.punktezahl = punktezahl;


    }
}
