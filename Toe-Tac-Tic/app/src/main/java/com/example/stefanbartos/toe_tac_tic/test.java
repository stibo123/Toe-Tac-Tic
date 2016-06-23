package com.example.stefanbartos.toe_tac_tic;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by StefanBartos on 17.06.16.
 */
public class test extends AppCompatActivity {
    BluetoothReader bluetoothReader;
    byte [] buffer = new byte[1024];
    int bytes;
    BluetoothDevice device;
    OutputStream outputStream = null;

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
    Button buttonfromp2;


    String textforsend = "";
    String getButtonString = "";

    boolean gameEnd = false;
    BluetoothConnect bluetoothConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
            //Toast.makeText(getApplicationContext(), "Verbunden mit: " + socket.getRemoteDevice().getName(),Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
            }


    private void player1play(BluetoothSocket socket) {
        bluetoothReader = new BluetoothReader(socket);
        bluetoothReader.start();
        player1.setName(adapter.getName());
        while(gameEnd==false) {
            setButtonsVisible();
            showplayerturn.setText(player1turnString);
            while (player1turn == false) {

            }
            try {
                buttontosend.setBackgroundResource(R.drawable.x);
                findcorrectBotArray(buttontosend, 'X');
                textforsend = buttontosend.getId() + " ";
                buffer = textforsend.getBytes();
                outputStream.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!checkiffull()) {
                if (!checkifwon()) {
                    int code = 0;
                    showplayerturn.setText("Gegner: O");
                    if(getButtonString!=null) {
                        buttonfromp2.setId(Integer.parseInt(getButtonString));
                        buttonfromp2.setEnabled(false);
                        buttonfromp2.setBackgroundResource(R.drawable.o);
                        findcorrectBotArray(buttonfromp2, 'O');
                    }
                    }
                }
                player1turn = false;
            }
            checkifwon();
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
                    top.setEnabled(false);
                }
            }
        });
        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1turn==false) {
                    player1turn = true;
                    buttontosend = topright;
                    topright.setEnabled(false);
                }
            }
        });
        midleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1turn==false) {
                    player1turn = true;
                    buttontosend = midleft;
                    midleft.setEnabled(false);
                }
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1turn==false) {
                    player1turn = true;
                    buttontosend = mid;
                    mid.setEnabled(false);
                }
            }
        });
        midright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1turn==false) {
                    player1turn = true;
                    buttontosend = midright;
                    midright.setEnabled(false);
                }
            }
        });
        bottomleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1turn == false) {
                    player1turn = true;
                    buttontosend = bottomleft;
                    bottomleft.setEnabled(false);
                }
            }
        });
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1turn==false) {
                    player1turn = true;
                    buttontosend = bottom;
                    bottom.setEnabled(false);
                }
            }
        });
        bottomright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1turn==false) {
                    player1turn = true;
                    buttontosend = bottomright;
                    bottomright.setEnabled(false);
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
    }


    private void findcorrectBotArray(Button button, Character character) {
        if (button == topleft) {
            botarray[0][0] = character;
        }
        if (button == top) {
            botarray[0][1] = character;
        }
        if (button == topright) {
            botarray[0][2] = character;
        }
        if (button == midleft) {
            botarray[1][0] = character;
        }
        if (button == mid) {
            botarray[1][1] = character;
        }
        if (button == midright) {
            botarray[1][2] = character;
        }
        if (button == bottomleft) {
            botarray[2][0] = character;
        }
        if (button == bottom) {
            botarray[2][1] = character;
        }
        if (button == bottomright) {
            botarray[2][2] = character;
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

    @Override
    public void onBackPressed() {
        gameEnd = true;
        outputStream = null;
        adapter = null;
        device = null;
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        final SQLiteStatement stmt = db.compileStatement(Schemaklasse.STMT_INSERT);
        db.beginTransaction();
        try {
            insertScore(stmt, player1.getName(), player2.getName(), player1.getPunktezahl(), player2.getPunktezahl());
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

    public static class BluetoothSocket1 extends Thread{
        private String NAME = "";
        private final UUID MY_UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
        private BluetoothAdapter mAdapter = null;
        private BluetoothServerSocket mmServerSocket = null;
        BluetoothGame bluetoothGame = new BluetoothGame();
        private int mState;
        public static final int STATE_NONE = 0;       // we're doing nothing
        public static final int STATE_LISTEN = 1;     // now listening for incoming connections
        public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
        public static final int STATE_CONNECTED = 3;  // now connected to a remote device

        Context context;

        public BluetoothSocket1(Context context1){
            mAdapter = BluetoothAdapter.getDefaultAdapter();
            NAME = mAdapter.getName();
            mState = STATE_NONE;
            context = context1;
            BluetoothServerSocket tmp = null;

            try {
                tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            } catch (IOException e) {
            }
            mmServerSocket = tmp;
        }

        public void run() {
            android.bluetooth.BluetoothSocket socket = null;
            // Listen to the server socket if we're not connected
            while (mState != STATE_CONNECTED) {
                try {

                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }

                if (socket != null) {

                }
            }
        }

    }
    public class BluetoothReader extends Thread
    {
        BluetoothSocket bluetoothSocket;
        InputStream inputStream;
        OutputStream outputStream;

        public BluetoothReader(BluetoothSocket bluetoothSocket) {
            InputStream inputStream1 = null;
            try {
                inputStream1 = bluetoothSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = inputStream1;
        }

        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public void run()
        {
            byte[] buffer = new byte[1024];
            int bytes;
            while (bluetoothSocket.isConnected())
            {
                try {
                    bytes = inputStream.read(buffer);
                    getButtonString = getButtonString + bytes;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

