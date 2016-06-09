package com.example.stefanbartos.toe_tac_tic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonOnePlayer;
    private Button buttonTwoPlayer;
    private Button buttonBTGame;
    private Button buttonStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initButtons();

    }

    public void initButtons(){
        buttonOnePlayer = (Button) findViewById(R.id.buttonOnePlayer);
        buttonTwoPlayer = (Button) findViewById(R.id.buttonTwoPlayer);
        buttonBTGame = (Button) findViewById(R.id.buttonBTGame);
        buttonStats = (Button) findViewById(R.id.buttonStats);

        buttonOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OPintent = new Intent(getApplicationContext(), tictactoe_layout.class);
                startActivity(OPintent);
            }
        });

        buttonTwoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TPintent = new Intent(getApplicationContext(),TwoPlayer.class);
                startActivity(TPintent);
            }
        });

        buttonBTGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BTGame = new Intent(getApplicationContext(), BTGame.class);
                startActivity(BTGame);
            }
        });

        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statsIntent = new Intent(getApplicationContext(), Stats.class);
                startActivity(statsIntent);
            }
        });
    }


}
