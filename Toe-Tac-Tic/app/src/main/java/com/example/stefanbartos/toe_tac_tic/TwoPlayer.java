package com.example.stefanbartos.toe_tac_tic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TwoPlayer extends AppCompatActivity {

    private Button top1;
    private Button top2;
    private Button top3;
    private Button mid1;
    private Button mid2;
    private Button mid3;
    private Button bot1;
    private Button bot2;
    private Button bot3;

    private String sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);

        initField();
        selection();
    }

    public void initField() {
        top1 = (Button) findViewById(R.id.topleft);
        top2 = (Button) findViewById(R.id.top);
        top3 = (Button) findViewById(R.id.topright);
        mid1 = (Button) findViewById(R.id.midleft);
        mid2 = (Button) findViewById(R.id.mid);
        mid3 = (Button) findViewById(R.id.midright);
        bot1 = (Button) findViewById(R.id.bottomleft);
        bot2 = (Button) findViewById(R.id.bottom);
        bot3 = (Button) findViewById(R.id.bottomright);
    }

    public void selection(){
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top1.setText(playerTurn());
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top2.setText(playerTurn());
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top3.setText(playerTurn());
            }
        });

        mid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mid1.setText(playerTurn());
            }
        });

        mid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mid2.setText(playerTurn());
            }
        });

        mid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mid3.setText(playerTurn());
            }
        });

        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bot1.setText(playerTurn());
            }
        });

        bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bot2.setText(playerTurn());
            }
        });

        bot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bot3.setText(playerTurn());
            }
        });
    }

    public String playerTurn(){
        int cnt = 1;
        if (cnt % 2 == 0){
            sign = "X";
            cnt = 1;
        }
        else
        {
            sign = "O";
            cnt+=1;
        }
        return sign;
    }
}
