package com.example.stefanbartos.toe_tac_tic;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by odieplinger on 09.06.2016.
 */
public class OnePlayer extends AppCompatActivity{
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
    int moves = 0;
    Character [][]botarray = new Character[3][3];
    String playername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initVariables();
        player1 = new Player("Stefan");
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
                playerTurn(topleft, botarray[0][0]);

            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(top, botarray[0][1]);
            }
        });
        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(topright, botarray[0][2]);
            }
        });
        midleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(midleft, botarray[1][0]);
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(mid, botarray[1][1]);
            }
        });
        midright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(midright, botarray[1][2]);
            }
        });
        bottomleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bottomleft, botarray[2][0]);
            }
        });
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bottom, botarray[2][1]);
            }
        });
        bottomright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn(bottomright, botarray[2][2]);
            }
        });

        for (int x = 0;x<botarray.length;x++)
        {
            for(int y = 0;y<botarray[0].length;y++) {
                botarray[x][y]='1';
            }
            }
    }
        public void playerTurn(Button button,Character character)
        {
            if(moves % 2==0)
            {
                character = 'X';
                button.setBackgroundResource(R.drawable.x);
            }
            else{
                bot();
            }
            button.setEnabled(false);
            moves++;
            if(botarray[0][0]=='X'&&botarray[0][0]=='X'&&botarray[0][0]=='X')
            {
                gameWon(botarray[0][0]);
            }
        }

    private void gameWon(Character character) {
        if(character=='O')
        {
            playername = "Der Gegner";
        }
        else
        {
            playername = player1.getName();
        }
        Toast.makeText(getApplicationContext(), playername + " hat gewonnen!", Toast.LENGTH_SHORT);
        topleft.setBackgroundResource(0);
        top.setBackgroundResource(0);
        topright.setBackgroundResource(0);
        midleft.setBackgroundResource(0);
        mid.setBackgroundResource(0);
        midright.setBackgroundResource(0);
        bottomleft.setBackgroundResource(0);
        bottom.setBackgroundResource(0);
        bottomright.setBackgroundResource(0);
    }

    private void bot() {
        Button button = null;
        for (int x = 0;x<botarray.length-1;x++)
        {
            for(int y = 0;y<botarray[0].length-1;y++)
            {
                if (botarray[x][y]=='X'||botarray[x][y]=='O')
                {
                    if(botarray[x+1][y]=='X'||botarray[x+1][y]=='O')
                    {
                        button = findcorrectButton(x+1,y);
                        button.setBackgroundResource(R.drawable.o);
                        botarray[x+1][y]='O';
                    }else {
                        button = findcorrectButton(x,y+1);
                        button.setBackgroundResource(R.drawable.o);
                        botarray[x][y+1]='O';
                    }
                }
                else{
                    if(botarray[x+1][y]=='X'||botarray[x+1][y]=='O')
                    {
                        button = findcorrectButton(x, y);
                        button.setBackgroundResource(R.drawable.o);
                        botarray[x][y]='O';
                    }
                    else {
                        button = findcorrectButton(x+1, y);
                        button.setBackgroundResource(R.drawable.o);
                        botarray[x+1][y]='O';
                    }
                }
            }
        }

    }

    private Button findcorrectButton(int x, int y) {
        Button button = null;
        switch (x)
        {
            case 0:switch (y){
                case 0: button = topleft;break;
                case 1: button = top;break;
                case 2: button = topright;break;
            }break;
            case 1:switch (y){
                case 0: button = midleft;break;
                case 1: button = mid;break;
                case 2: button = midright;break;
            }break;
            case 2:switch (y){
                case 0: button = bottomleft;break;
                case 1: button = bottom;break;
                case 2: button = bottomright;break;
            }break;
        }
        return button;
    }


}
