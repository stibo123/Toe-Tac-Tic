package com.example.stefanbartos.toe_tac_tic;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class tictactoe_layout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }


}
