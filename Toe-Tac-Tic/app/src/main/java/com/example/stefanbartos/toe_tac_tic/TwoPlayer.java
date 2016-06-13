package com.example.stefanbartos.toe_tac_tic;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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

    private int clickCNT = 0;
    private int cnt = 1;
    private String winner;

    private String sign;

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);

        playerInput();
        initField();
        selection();
    }

    public void playerInput(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final LinearLayout vDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
        alert.setView(vDialog);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText e1 = (EditText) vDialog.findViewById(R.id.editTextP1Name);
                EditText e2 = (EditText) vDialog.findViewById(R.id.editTextP2Name);


                player = new Player(e1.getText().toString(), 0);
                player = new Player(e2.getText().toString(), 0);                                    //unnötiges anlegen von Player, müssen bei Punkteberechung angelegt werden wegen Felder Name und Punkte
            }
        });

        alert.setNegativeButton("Cancel", null);
        alert.show();
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
                playerTurn(top1);
                clickCNT+=1;
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(top2);
                clickCNT+=1;
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(top3);
                clickCNT+=1;
            }
        });

        mid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(mid1);
                clickCNT+=1;
            }
        });

        mid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(mid2);
                clickCNT+=1;
            }
        });

        mid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(mid3);
                clickCNT+=1;
            }
        });

        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bot1);
                clickCNT+=1;
            }
        });

        bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bot2);
                clickCNT+=1;
            }
        });

        bot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bot3);
                clickCNT+=1;
            }
        });
    }

    public String playerTurn(Button b) {
        if (clickCNT <= 9) {
            if (cnt % 2 == 0) {
                b.setBackgroundResource(R.drawable.x);
                cnt = 1;
            } else {
                b.setBackgroundResource(R.drawable.o);
                cnt += 1;
            }
            return sign;
        }
        else{
            Toast.makeText(getApplicationContext(), "Spieler " + winner + "hat gewonnen", Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
