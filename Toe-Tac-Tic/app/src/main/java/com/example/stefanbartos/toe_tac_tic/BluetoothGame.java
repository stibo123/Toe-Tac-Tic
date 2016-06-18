package com.example.stefanbartos.toe_tac_tic;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by StefanBartos on 17.06.16.
 */
public class BluetoothGame extends AppCompatActivity {
    byte [] buffer = new byte[1024];
    int bytes;
    BluetoothDevice device;

    final OutputStream outputStream = new OutputStream() {
        @Override
        public void write(int oneByte) throws IOException {
            this.write(buffer);
            return;
        }
    };
    final InputStream inputStream = new InputStream() {
        @Override
        public int read(){
            try {
                bytes = this.read(buffer);
            } catch (IOException e) {
                return 0;
            }
            return 0;
        }
    };

    TextView player1tvname;
    TextView player2tvname;
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

    TextView showplayerturn;

    Player player1;
    Player player2;

    String player1name = "Player1";
    String player2name = "Player2";

    Button buttonplayer2;
    Button buttonplayer1;
    boolean buttonplayer1Pressed = false;
    boolean buttonplayer2Pressed = false;
    BluetoothAdapter adapter;
    TextView gamesplayedButton;

    LinearLayout layoutLinear;

    ProgressDialog dialog;
    String player1turnString = "Dein Zug: X";
    String player2turnString = "Dein Zug: O";
    boolean player1turn = false;
    Character[][] botarray = new Character[3][3];
    Button buttontosend;

    ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    ArrayList<BluetoothDevice> pairedDeviceArrayList;

    AlertDialog.Builder alertDialog;
    AlertDialog OptionDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);
        init();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void game(android.bluetooth.BluetoothSocket socket) {
        if (socket.isConnected()) {
            dialog.dismiss();
            //Toast.makeText(getApplicationContext(), "Verbunden mit: " + ,Toast.LENGTH_SHORT).show();
            if (buttonplayer1Pressed) {
                player1.setName(adapter.getName());
                player1play(socket);
            }
            if (buttonplayer2Pressed) {
                OptionDialog.dismiss();
                player2.setName(adapter.getName());
                player2play(socket);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Fehler!",Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void player1play(android.bluetooth.BluetoothSocket socket) {
        setButtonsVisible();
        showplayerturn.setText(player1turnString);
        while (player1turn==false)
        {

        }
        try {

            //outputStream.write(new OutputStreamWriter(new Ou));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void player2play(android.bluetooth.BluetoothSocket socket) {
        setButtonsVisible();

    }
    private void setButtonsVisible() {
        player1tvname.setVisibility(View.VISIBLE);
        player2tvname.setVisibility(View.VISIBLE);
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
        showplayerturn.setVisibility(View.VISIBLE);
    }




    private void init() {
        showplayerturn = (TextView) findViewById(R.id.tvshowturn);

        layoutLinear = (LinearLayout) findViewById(R.id.LayoutLinear);
        layoutLinear.setVisibility(View.VISIBLE);

        adapter = BluetoothAdapter.getDefaultAdapter();

        gamesplayedButton = (TextView) findViewById(R.id.gamesplayed);
        gamesplayedButton.setVisibility(View.INVISIBLE);

        player1 = new Player(adapter.getName(), 0);
        player2 = new Player("Player2", 0);
        player1tvname = (TextView) findViewById(R.id.ainametv);
        player1stats = (TextView) findViewById(R.id.aigameswontv);
        //player1stats.setText(player1.getPunktezahl());

        player2tvname = (TextView) findViewById(R.id.playernametv);
        player2tvname.setText("");
        player2stats = (TextView) findViewById(R.id.playergameswontv);
        player2stats.setText("");

        player1tvname.setVisibility(View.INVISIBLE);
        player2tvname.setVisibility(View.INVISIBLE);
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

        topleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1turn==false) {
                    player1turn = true;
                    buttontosend = topleft;
                    findcorrectBotArray(topleft);
                    topleft.setEnabled(false);
                }
            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1turn==false) {
                    player1turn = true;
                    buttontosend = top;
                    findcorrectBotArray(top);
                    top.setEnabled(false);
                }
            }
        });

        for (int i = 0; i < botarray.length; i++) {
            for (int o = 0; o < botarray.length; o++) {
                botarray[i][o] = 'a';
            }
        }

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
                new Thread(new BluetoothSocket1(getApplicationContext())).start();
            }
        });
        buttonplayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonplayer2Pressed = true;
                buttonplayer2.setEnabled(false);
                buttonplayer1.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Sucht nach Geräte...", Toast.LENGTH_SHORT).show();
                findDevices();
            }
        });
    }

    private void findDevices() {
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            pairedDeviceArrayList = new ArrayList<BluetoothDevice>();

            for (BluetoothDevice device : pairedDevices) {
                pairedDeviceArrayList.add(device);
            }
            alertDialog = new AlertDialog.Builder(BluetoothGame.this);
            LayoutInflater inflater = getLayoutInflater();
            View convertView = (View) inflater.inflate(R.layout.activity_stats, null);
            alertDialog.setView(convertView);
            alertDialog.setTitle("BluetoothScan");
            ListView lv = (ListView) convertView.findViewById(R.id.listView);

            pairedDeviceAdapter = new ArrayAdapter<BluetoothDevice>(this, android.R.layout.simple_list_item_1, pairedDeviceArrayList);
            lv.setAdapter(pairedDeviceAdapter);
            pairedDeviceAdapter.notifyDataSetChanged();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    BluetoothDevice device = (BluetoothDevice) parent.getItemAtPosition(position);
                    new Thread(new BluetoothConnect(getApplicationContext(), device)).start();
                }
            });
            alertDialog.show();
        }
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
        String playername = "";
        if (character == 'O') {
            playername = player2.getName();
            player2.setPunktezahl(player2.getPunktezahl() + 1);
            player2stats.setText(player2.getPunktezahl() + "");
        } else if (character == 'N') {
            playername = "Keiner";
        } else {
            playername = player1.getName();
            player1.setPunktezahl(player1.getPunktezahl() + 1);
            player1stats.setText(player1.getPunktezahl() + "");
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

}
