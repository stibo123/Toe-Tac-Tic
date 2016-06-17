package com.example.stefanbartos.toe_tac_tic;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by StefanBartos on 17.06.16.
 */
public class BluetoothGame extends AppCompatActivity {

    TextView player1name;
    TextView player2name;
    TextView player1stats;
    TextView player2stats;

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
    Player player2;

    Button buttonplayer2;
    Button buttonplayer1;
    boolean buttonplayer1Pressed = false;
    boolean buttonplayer2Pressed = false;
    BluetoothAdapter adapter;
    TextView gamesplayedButton;

    LinearLayout layoutLinear;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);
        init();
        new Thread(new BluetoothConnect(getApplicationContext())).start();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void game(BluetoothSocket socket) {
        Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
        if (socket.isConnected()) {
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "Verbunden mit: " + adapter.getName(),Toast.LENGTH_SHORT).show();
            if (buttonplayer1Pressed) {
                player1play(socket);
            }
            if (buttonplayer2Pressed) {
                player2play(socket);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Fehler!",Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void player1play(BluetoothSocket socket) {
        setButtonsVisible();
        
    }

    private void player2play(BluetoothSocket socket) {
        setButtonsVisible();

    }
    private void setButtonsVisible() {
        player1name.setVisibility(View.VISIBLE);
        player2name.setVisibility(View.VISIBLE);
        player1stats.setVisibility(View.VISIBLE);
        player2stats.setVisibility(View.VISIBLE);
        gamesplayedButton.setVisibility(View.VISIBLE);

        topleft.setVisibility(View.VISIBLE);
        top.setVisibility(View.VISIBLE);
        topright.setVisibility(View.VISIBLE);
        midleft.setVisibility(View.VISIBLE);
        mid.setVisibility(View.VISIBLE);
        midright.setVisibility(View.VISIBLE);
        bottomleft.setVisibility(View.VISIBLE);
        bottom.setVisibility(View.VISIBLE);
        bottomright.setVisibility(View.VISIBLE);

        buttonplayer1.setVisibility(View.INVISIBLE);
        buttonplayer2.setVisibility(View.INVISIBLE);
    }




    private void init() {

        layoutLinear = (LinearLayout) findViewById(R.id.LayoutLinear);
        layoutLinear.setVisibility(View.VISIBLE);

        adapter = BluetoothAdapter.getDefaultAdapter();

        gamesplayedButton = (TextView) findViewById(R.id.gamesplayed);
        gamesplayedButton.setVisibility(View.INVISIBLE);

        player1 = new Player("Stefan", 0);
        player1name = (TextView) findViewById(R.id.ainametv);
        player1stats = (TextView) findViewById(R.id.aigameswontv);
        //player1stats.setText(player1.getPunktezahl());

        player2name = (TextView) findViewById(R.id.playernametv);
        player2name.setText("");
        player2stats = (TextView) findViewById(R.id.playergameswontv);
        player2stats.setText("");

        player1name.setVisibility(View.INVISIBLE);
        player2name.setVisibility(View.INVISIBLE);
        player1stats.setVisibility(View.INVISIBLE);
        player2stats.setVisibility(View.INVISIBLE);

        topleft = (Button) findViewById(R.id.topleft);
        top = (Button) findViewById(R.id.top);
        topright = (Button) findViewById(R.id.topright);
        midleft = (Button) findViewById(R.id.midleft);
        mid = (Button) findViewById(R.id.mid);
        midright = (Button) findViewById(R.id.midright);
        bottomleft = (Button) findViewById(R.id.bottomleft);
        bottom = (Button) findViewById(R.id.bottom);
        bottomright = (Button) findViewById(R.id.bottomright);

        topleft.setVisibility(View.INVISIBLE);
        top.setVisibility(View.INVISIBLE);
        topright.setVisibility(View.INVISIBLE);
        midleft.setVisibility(View.INVISIBLE);
        mid.setVisibility(View.INVISIBLE);
        midright.setVisibility(View.INVISIBLE);
        bottomleft.setVisibility(View.INVISIBLE);
        bottom.setVisibility(View.INVISIBLE);
        bottomright.setVisibility(View.INVISIBLE);


        buttonplayer1 = (Button) findViewById(R.id.buttonplayer1);
        buttonplayer2 = (Button) findViewById(R.id.buttonplayer2);

        buttonplayer1.setVisibility(View.VISIBLE);
        buttonplayer2.setVisibility(View.VISIBLE);
        buttonplayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonplayer1Pressed = true;
                buttonplayer2.setEnabled(false);
                buttonplayer1.setEnabled(false);
                dialog = ProgressDialog.show(BluetoothGame.this, "Wartet...", "Wartet auf Verbindung...", false, false);
            }
        });
        buttonplayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonplayer2Pressed = true;
                buttonplayer2.setEnabled(false);
                buttonplayer1.setEnabled(false);
                dialog = ProgressDialog.show(BluetoothGame.this, "Wartet...", "Wartet auf Verbindung...", false, false);
            }
        });


    }
}
