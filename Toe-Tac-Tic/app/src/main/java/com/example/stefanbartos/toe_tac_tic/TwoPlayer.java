package com.example.stefanbartos.toe_tac_tic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    private TextView player1Name;
    private TextView player2Name;
    private TextView points_p1;
    private TextView points_p2;

    private Character[][] fieldArray = new Character[3][3];

    private int clickCNT = 0;
    private int cnt = 1;
    private String winner;

    private TextView showturn;

    private String sign;

    private Player playerObject1;
    private Player playerObject2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        initField();
        playerInput();
        selection();
    }

    public void playerInput() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final LinearLayout vDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
        alert.setView(vDialog);
        alert.setCancelable(false);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText e1 = (EditText) vDialog.findViewById(R.id.editTextP1Name);
                EditText e2 = (EditText) vDialog.findViewById(R.id.editTextP2Name);

                playerObject1 = new Player(e1.getText().toString(), 0);
                playerObject2 = new Player(e2.getText().toString(), 0);
                player1Name.setText(playerObject1.getName());
                player2Name.setText(playerObject2.getName());
                points_p1.setText(playerObject1.getPunktezahl() + "");
                points_p2.setText(playerObject2.getPunktezahl() + "");
            }
        });
        alert.setNegativeButton("CANCEL", null);
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


        player1Name = (TextView) findViewById(R.id.ainametv);
        player2Name = (TextView) findViewById(R.id.playernametv);

        points_p1 = (TextView) findViewById(R.id.aigameswontv);
        points_p2 = (TextView) findViewById(R.id.playergameswontv);

        showturn = (TextView) findViewById(R.id.tvshowturn);
        showturn.setVisibility(View.VISIBLE);

        for (int i = 0; i < fieldArray.length; i++) {
            for (int o = 0; o < fieldArray[0].length; o++) {
                fieldArray[i][o] = 'A';
            }
        }
    }

    public void selection() {
        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(top1);
                clickCNT += 1;
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(top2);
                clickCNT += 1;
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(top3);
                clickCNT += 1;
            }
        });

        mid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(mid1);
                clickCNT += 1;
            }
        });

        mid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(mid2);
                clickCNT += 1;
            }
        });

        mid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(mid3);
                clickCNT += 1;
            }
        });

        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bot1);
                clickCNT += 1;
            }
        });

        bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bot2);
                clickCNT += 1;
            }
        });

        bot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bot3);
                clickCNT += 1;
            }
        });
    }

    public String playerTurn(Button button) {
        if (clickCNT < 9) {
            if (cnt % 2 == 0) {
                showturn.setText(playerObject2.getName() + ": O");
                button.setBackgroundResource(R.drawable.x);
                button.setEnabled(false);
                if (button == top1) {
                    fieldArray[0][0] = 'X';
                }
                if (button == top2) {
                    fieldArray[0][1] = 'X';
                }
                if (button == top3) {
                    fieldArray[0][2] = 'X';
                }
                if (button == mid1) {
                    fieldArray[1][0] = 'X';
                }
                if (button == mid2) {
                    fieldArray[1][1] = 'X';
                }
                if (button == mid3) {
                    fieldArray[1][2] = 'X';
                }
                if (button == bot1) {
                    fieldArray[2][0] = 'X';
                }
                if (button == bot2) {
                    fieldArray[2][1] = 'X';
                }
                if (button == bot3) {
                    fieldArray[2][2] = 'X';
                }
                cnt += 1;
                checkifwon();
            } else {
                button.setBackgroundResource(R.drawable.o);
                showturn.setText(playerObject1.getName() + ": X");
                if (button == top1) {
                    fieldArray[0][0] = 'O';
                }
                if (button == top2) {
                    fieldArray[0][1] = 'O';
                }
                if (button == top3) {
                    fieldArray[0][2] = 'O';
                }
                if (button == mid1) {
                    fieldArray[1][0] = 'O';
                }
                if (button == mid2) {
                    fieldArray[1][1] = 'O';
                }
                if (button == mid3) {
                    fieldArray[1][2] = 'O';
                }
                if (button == bot1) {
                    fieldArray[2][0] = 'O';
                }
                if (button == bot2) {
                    fieldArray[2][1] = 'O';
                }
                if (button == bot3) {
                    fieldArray[2][2] = 'O';
                }
                cnt += 1;
                checkifwon();
            }
            return sign;
        } else {
            Toast.makeText(getApplicationContext(), "Spieler " + winner + " hat gewonnen", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    private boolean checkifwon() {
        //top
        if (fieldArray[0][0] == 'X' && fieldArray[0][1] == 'X' && fieldArray[0][2] == 'X') {
            gameWon(fieldArray[0][0]);
            return true;
        }
        if (fieldArray[0][0] == 'O' && fieldArray[0][1] == 'O' && fieldArray[0][2] == 'O') {
            gameWon(fieldArray[0][0]);
            return true;
        }
        //mid
        if (fieldArray[1][0] == 'X' && fieldArray[1][1] == 'X' && fieldArray[1][2] == 'X') {
            gameWon(fieldArray[1][0]);
            return true;
        }
        if (fieldArray[1][0] == 'O' && fieldArray[1][1] == 'O' && fieldArray[1][2] == 'O') {
            gameWon(fieldArray[1][0]);
            return true;
        }
        //bottom
        if (fieldArray[2][0] == 'X' && fieldArray[2][1] == 'X' && fieldArray[2][2] == 'X') {
            gameWon(fieldArray[2][0]);
            return true;
        }
        if (fieldArray[2][0] == 'O' && fieldArray[2][1] == 'O' && fieldArray[2][2] == 'O') {
            gameWon(fieldArray[2][0]);
            return true;
        }
        //diagonal
        if (fieldArray[0][0] == 'X' && fieldArray[1][1] == 'X' && fieldArray[2][2] == 'X') {
            gameWon(fieldArray[0][0]);
            return true;
        }
        if (fieldArray[0][2] == 'X' && fieldArray[1][1] == 'X' && fieldArray[2][0] == 'X') {
            gameWon(fieldArray[0][2]);
            return true;
        }
        if (fieldArray[0][2] == 'O' && fieldArray[1][1] == 'O' && fieldArray[2][0] == 'O') {
            gameWon(fieldArray[0][2]);
            return true;
        }
        if (fieldArray[0][0] == 'O' && fieldArray[1][1] == 'O' && fieldArray[2][2] == 'O') {
            gameWon(fieldArray[0][0]);
            return true;
        }
        //left
        if (fieldArray[0][0] == 'X' && fieldArray[1][0] == 'X' && fieldArray[2][0] == 'X') {
            gameWon(fieldArray[0][0]);
            return true;
        }
        if (fieldArray[0][0] == 'O' && fieldArray[1][0] == 'O' && fieldArray[2][0] == 'O') {
            gameWon(fieldArray[0][0]);
            return true;
        }
        //middown
        if (fieldArray[0][1] == 'X' && fieldArray[1][1] == 'X' && fieldArray[2][1] == 'X') {
            gameWon(fieldArray[0][1]);
            return true;
        }
        if (fieldArray[0][1] == 'O' && fieldArray[1][1] == 'O' && fieldArray[2][1] == 'O') {
            gameWon(fieldArray[0][1]);
            return true;
        }
        //right
        if (fieldArray[0][2] == 'X' && fieldArray[1][2] == 'X' && fieldArray[2][2] == 'X') {
            gameWon(fieldArray[0][2]);
            return true;
        }
        if (fieldArray[0][2] == 'O' && fieldArray[1][2] == 'O' && fieldArray[2][2] == 'O') {
            gameWon(fieldArray[0][2]);
            return true;
        }
        return false;
    }

    public void gameWon(Character character) {
        String winner_name;
        if (character == 'O') {

            winner_name = playerObject1.getName();
            playerObject1.setPunktezahl(playerObject1.getPunktezahl() + 1);
            points_p1.setText(playerObject1.getPunktezahl() + "");

        } else {
            winner_name = playerObject2.getName();
            playerObject2.setPunktezahl(playerObject2.getPunktezahl() + 1);
            points_p2.setText(playerObject2.getPunktezahl() + "");
        }
        Toast.makeText(getApplicationContext(), winner_name + " hat gewonnen!", Toast.LENGTH_SHORT).show();
        winner = winner_name;
        top1.setBackgroundResource(R.drawable.button_border);
        top1.setEnabled(true);
        top2.setBackgroundResource(R.drawable.button_border);
        top2.setEnabled(true);
        top3.setBackgroundResource(R.drawable.button_border);
        top3.setEnabled(true);
        mid1.setBackgroundResource(R.drawable.button_border);
        mid1.setEnabled(true);
        mid2.setBackgroundResource(R.drawable.button_border);
        mid2.setEnabled(true);
        mid3.setBackgroundResource(R.drawable.button_border);
        mid3.setEnabled(true);
        bot1.setBackgroundResource(R.drawable.button_border);
        bot1.setEnabled(true);
        bot2.setBackgroundResource(R.drawable.button_border);
        bot2.setEnabled(true);
        bot3.setBackgroundResource(R.drawable.button_border);
        bot3.setEnabled(true);

        for (int i = 0; i < fieldArray.length; i++) {
            for (int o = 0; o < fieldArray.length; o++) {
                fieldArray[i][o] = 'a';
            }
        }
    }
}
