package com.example.stefanbartos.toe_tac_tic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by odieplinger on 09.06.2016.
 */
public class OnePlayer extends AppCompatActivity {
    Button topleft;
    Button top;
    Button topright;
    Button midright;
    Button mid;
    Button midleft;
    Button bottomleft;
    Button bottom;
    Button bottomright;

    Player player1;
    Player ai;

    Character[][] botarray = new Character[3][3];

    String playername;
    String name = "";

    SharedPreferences prefs;
    String ainame = "";
    TextView aigameswontv;
    TextView playergameswontv;
    TextView ainametv;
    TextView playernametv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        showDialog();
        initVariables();
    }

    private void showDialog() {
        final EditText editText = new EditText(this);
        int maxLength = 11;
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Type your name: ")
                .setCancelable(false)
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setView(editText)
                .setTitle("Toe-Tac-Tic")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = editText.getText().toString();
                        if (name.equals("")) {
                            name = "Player1";
                        }
                        player1 = new Player(name, 0);
                        playernametv.setText(player1.getName());
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void initVariables() {
        topleft = (Button) findViewById(R.id.topleft);
        top = (Button) findViewById(R.id.top);
        topright = (Button) findViewById(R.id.topright);
        midleft = (Button) findViewById(R.id.midleft);
        mid = (Button) findViewById(R.id.mid);
        midright = (Button) findViewById(R.id.midright);
        bottomleft = (Button) findViewById(R.id.bottomleft);
        bottom = (Button) findViewById(R.id.bottom);
        bottomright = (Button) findViewById(R.id.bottomright);

        topleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(topleft);

            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(top);
            }
        });
        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(topright);
            }
        });
        midleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(midleft);
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(mid);
            }
        });
        midright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(midright);
            }
        });
        bottomleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bottomleft);
            }
        });
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bottom);
            }
        });
        bottomright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bottomright);
            }
        });
        for (int i = 0; i < botarray.length; i++) {
            for (int o = 0; o < botarray.length; o++) {
                botarray[i][o] = 'a';
            }
        }
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        ainame = prefs.getString("ainame", "AI");
        ai = new Player(ainame, 0);

        aigameswontv = (TextView) findViewById(R.id.aigameswontv);
        aigameswontv.setText(ai.getPunktezahl() + "");
        playergameswontv = (TextView) findViewById(R.id.playergameswontv);
        playergameswontv.setText(0 + "");

        ainametv = (TextView) findViewById(R.id.ainametv);
        ainametv.setText(ainame);
        playernametv = (TextView) findViewById(R.id.playernametv);

    }

    public void playerTurn(Button button) {
        button.setBackgroundResource(R.drawable.x);
        button.setEnabled(false);
        findcorrectBotArray(button);
        if (!checkifwon()) {
            if (!checkiffull()) {
                try {
                    bot();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        checkifwon();
    }


    private boolean checkiffull() {
        for (int i = 0; i < botarray.length; i++) {
            for (int o = 0; o < botarray.length; o++) {
                if (botarray[i][o] == 'a') {
                    return false;
                }
            }
        }
        botarray[0][0] = 'N';
        gameWon(botarray[0][0]);
        return true;
    }

    private void findcorrectBotArray(Button button) {
        if (button == topleft) {
            botarray[0][0] = 'X';
        }
        if (button == top) {
            botarray[0][1] = 'X';
        }
        if (button == topright) {
            botarray[0][2] = 'X';
        }
        if (button == midleft) {
            botarray[1][0] = 'X';
        }
        if (button == mid) {
            botarray[1][1] = 'X';
        }
        if (button == midright) {
            botarray[1][2] = 'X';
        }
        if (button == bottomleft) {
            botarray[2][0] = 'X';
        }
        if (button == bottom) {
            botarray[2][1] = 'X';
        }
        if (button == bottomright) {
            botarray[2][2] = 'X';
        }
    }

    private boolean checkifwon() {
        //top
        if (botarray[0][0] == 'X' && botarray[0][1] == 'X' && botarray[0][2] == 'X') {
            gameWon(botarray[0][0]);
            return true;
        }
        if (botarray[0][0] == 'O' && botarray[0][1] == 'O' && botarray[0][2] == 'O') {
            gameWon(botarray[0][0]);
            return true;
        }
        //mid
        if (botarray[1][0] == 'X' && botarray[1][1] == 'X' && botarray[1][2] == 'X') {
            gameWon(botarray[1][0]);
            return true;
        }
        if (botarray[1][0] == 'O' && botarray[1][1] == 'O' && botarray[1][2] == 'O') {
            gameWon(botarray[1][0]);
            return true;
        }
        //bottom
        if (botarray[2][0] == 'X' && botarray[2][1] == 'X' && botarray[2][2] == 'X') {
            gameWon(botarray[2][0]);
            return true;
        }
        if (botarray[2][0] == 'O' && botarray[2][1] == 'O' && botarray[2][2] == 'O') {
            gameWon(botarray[2][0]);
            return true;
        }
        //diagonal
        if (botarray[0][0] == 'X' && botarray[1][1] == 'X' && botarray[2][2] == 'X') {
            gameWon(botarray[0][0]);
            return true;
        }
        if (botarray[0][2] == 'X' && botarray[1][1] == 'X' && botarray[2][0] == 'X') {
            gameWon(botarray[0][2]);
            return true;
        }
        if (botarray[0][2] == 'O' && botarray[1][1] == 'O' && botarray[2][0] == 'O') {
            gameWon(botarray[0][2]);
            return true;
        }
        if (botarray[0][0] == 'O' && botarray[1][1] == 'O' && botarray[2][2] == 'O') {
            gameWon(botarray[0][0]);
            return true;
        }
        //left
        if (botarray[0][0] == 'X' && botarray[1][0] == 'X' && botarray[2][0] == 'X') {
            gameWon(botarray[0][0]);
            return true;
        }
        if (botarray[0][0] == 'O' && botarray[1][0] == 'O' && botarray[2][0] == 'O') {
            gameWon(botarray[0][0]);
            return true;
        }
        //middown
        if (botarray[0][1] == 'X' && botarray[1][1] == 'X' && botarray[2][1] == 'X') {
            gameWon(botarray[0][1]);
            return true;
        }
        if (botarray[0][1] == 'O' && botarray[1][1] == 'O' && botarray[2][1] == 'O') {
            gameWon(botarray[0][1]);
            return true;
        }
        //right
        if (botarray[0][2] == 'X' && botarray[1][2] == 'X' && botarray[2][2] == 'X') {
            gameWon(botarray[0][2]);
            return true;
        }
        if (botarray[0][2] == 'O' && botarray[1][2] == 'O' && botarray[2][2] == 'O') {
            gameWon(botarray[0][2]);
            return true;
        }
        return false;
    }

    private void gameWon(Character character) {
        if (character == 'O') {
            playername = ai.getName();
            ai.setPunktezahl(ai.getPunktezahl() + 1);
            aigameswontv.setText(ai.getPunktezahl() + "");
        } else if (character == 'N') {
            playername = "Keiner";
        } else {
            playername = player1.getName();
            player1.setPunktezahl(player1.getPunktezahl() + 1);
            playergameswontv.setText(player1.getPunktezahl() + "");
        }

        Toast.makeText(getApplicationContext(), playername + " hat gewonnen!", Toast.LENGTH_SHORT).show();
        topleft.setBackgroundResource(R.drawable.button_border);
        topleft.setEnabled(true);
        top.setBackgroundResource(R.drawable.button_border);
        top.setEnabled(true);
        topright.setBackgroundResource(R.drawable.button_border);
        topright.setEnabled(true);
        midleft.setBackgroundResource(R.drawable.button_border);
        midleft.setEnabled(true);
        mid.setBackgroundResource(R.drawable.button_border);
        mid.setEnabled(true);
        midright.setBackgroundResource(R.drawable.button_border);
        midright.setEnabled(true);
        bottomleft.setBackgroundResource(R.drawable.button_border);
        bottomleft.setEnabled(true);
        bottom.setBackgroundResource(R.drawable.button_border);
        bottom.setEnabled(true);
        bottomright.setBackgroundResource(R.drawable.button_border);
        bottomright.setEnabled(true);

        for (int i = 0; i < botarray.length; i++) {
            for (int o = 0; o < botarray.length; o++) {
                botarray[i][o] = 'a';
            }
        }
    }

    private void bot() throws InterruptedException {
        Button button = null;
        while (button == null) {
            int a = ((int) (Math.random() * 3));
            int b = ((int) (Math.random() * 3));
            button = findcorrectButton(a, b);
            if (button.isEnabled()) {
                button.setBackgroundResource(R.drawable.o);
                button.setEnabled(false);
                botarray[a][b] = 'O';
            } else {
                button = null;
            }
        }
    }

    private Button findcorrectButton(int x, int y) {
        Button button = null;
        switch (x) {
            case 0:
                switch (y) {
                    case 0:
                        button = topleft;
                        break;
                    case 1:
                        button = top;
                        break;
                    case 2:
                        button = topright;
                        break;
                }
                break;
            case 1:
                switch (y) {
                    case 0:
                        button = midleft;
                        break;
                    case 1:
                        button = mid;
                        break;
                    case 2:
                        button = midright;
                        break;
                }
                break;
            case 2:
                switch (y) {
                    case 0:
                        button = bottomleft;
                        break;
                    case 1:
                        button = bottom;
                        break;
                    case 2:
                        button = bottomright;
                        break;
                }
                break;
        }
        return button;
    }

    @Override
    public void onBackPressed() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        final SQLiteStatement stmt = db.compileStatement(Schemaklasse.STMT_INSERT);
        db.beginTransaction();
        try {
            insertScore(stmt, player1.getName(), ai.getName(), player1.getPunktezahl(), ai.getPunktezahl());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        super.onBackPressed();
    }

    private void insertScore(SQLiteStatement stmt, String player1name, String ainame, int player1punktezahl, int aipunktezahl) {
        stmt.bindString(1, player1name);
        stmt.bindString(2, ainame);
        stmt.bindLong(3, player1punktezahl);
        stmt.bindLong(4, aipunktezahl);
        stmt.executeInsert();
    }
}
