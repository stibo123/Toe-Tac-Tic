package com.example.stefanbartos.toe_tac_tic;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by StefanBartos on 17.06.16.
 */
public class BluetoothGame extends AppCompatActivity {
    boolean waittillconnected = true;

    public boolean isWaittillconnected() {
        return waittillconnected;
    }

    public void setWaittillconnected(boolean waittillconnected) {
        this.waittillconnected = waittillconnected;
    }

    BluetoothSocket socket;
    byte[] buffer = new byte[1024];
    int bytes;
    BluetoothDevice device;

    OutputStream outputStream = new OutputStream() {
        @Override
        public void write(int oneByte) throws IOException {
            this.write(buffer);
            return;
        }
    };
    InputStream inputStream = new InputStream() {
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public int read() {
            try {
                bytes = this.read(buffer);
                String str = new String(buffer, StandardCharsets.UTF_8);
                getButtonString = str;
            } catch (IOException e) {
                return 0;
            }
            return 1;
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

    TextView gamestv;
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

    ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    ArrayList<BluetoothDevice> pairedDeviceArrayList;

    AlertDialog.Builder alertDialog;
    AlertDialog OptionDialog;

    String textforsend = "";
    String getButtonString = "";

    boolean gameEnd = false;
    BluetoothSocket1 bluetoothSocket1;
    BluetoothConnect bluetoothConnect;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe_layout);
        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public String getGetButtonString() {
        return getButtonString;
    }

    public void setGetButtonString(String getButtonString) {
        this.getButtonString = getButtonString;
    }

    private void player1play() {
        while (gameEnd == false) {
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
                    boolean buttontrue = true;
                    while (buttontrue) {
                        if (buttonfromp2.equals(bottom)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)) {
                            buttonfromp2.setId(Integer.parseInt(getButtonString));
                            buttonfromp2.setEnabled(false);
                            buttonfromp2.setBackgroundResource(R.drawable.x);
                            findcorrectBotArray(buttonfromp2, 'X');
                            buttontrue = false;
                            getButtonString = "";
                        }
                    }
                }
                player1turn = false;
            }
            checkifwon();
        }
    }

    private void player2play() {
        while (gameEnd == false) {
            setButtonsVisible();
            player1turn = true;
            if (!checkiffull()) {
                if (!checkifwon()) {
                    int code = 0;
                    showplayerturn.setText("Gegner: X");
                    boolean buttontrue = true;
                    while (buttontrue) {
                        if (buttonfromp2.equals(bottom)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)
                                || buttonfromp2.equals(bottomleft)) {
                            buttonfromp2.setId(Integer.parseInt(getButtonString));
                            buttonfromp2.setEnabled(false);
                            buttonfromp2.setBackgroundResource(R.drawable.x);
                            findcorrectBotArray(buttonfromp2, 'X');
                            buttontrue = false;
                        }


                    }
                }
            }
            player1turn = false;
            checkifwon();
            showplayerturn.setText(player2turnString);
            while (player1turn == false) {

            }
            try {
                buttontosend.setBackgroundResource(R.drawable.o);
                findcorrectBotArray(buttontosend, 'O');
                textforsend = buttontosend.getId() + " ";
                buffer = textforsend.getBytes();
                outputStream.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void setButtonsVisible() {
        player1tvname.setVisibility(View.VISIBLE);
        player2tvname.setVisibility(View.VISIBLE);
        player1stats.setVisibility(View.VISIBLE);
        player2stats.setVisibility(View.VISIBLE);
        gamesplayedButton.setVisibility(View.VISIBLE);
        gamestv.setVisibility(View.VISIBLE);

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

        gamestv = (TextView) findViewById(R.id.GamesTextview);
        gamestv.setVisibility(View.INVISIBLE);

        gamesplayedButton = (TextView) findViewById(R.id.playersTextview);
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
                if (player1turn == false) {
                    player1turn = true;
                    buttontosend = topleft;
                    topleft.setEnabled(false);
                }
            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1turn == false) {
                    player1turn = true;
                    buttontosend = top;
                    top.setEnabled(false);
                }
            }
        });
        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1turn == false) {
                    player1turn = true;
                    buttontosend = topright;
                    topright.setEnabled(false);
                }
            }
        });
        midleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1turn == false) {
                    player1turn = true;
                    buttontosend = midleft;
                    midleft.setEnabled(false);
                }
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1turn == false) {
                    player1turn = true;
                    buttontosend = mid;
                    mid.setEnabled(false);
                }
            }
        });
        midright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1turn == false) {
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
                if (player1turn == false) {
                    player1turn = true;
                    buttontosend = bottom;
                    bottom.setEnabled(false);
                }
            }
        });
        bottomright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1turn == false) {
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
        buttonplayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonplayer1Pressed = true;
                buttonplayer2.setEnabled(false);
                buttonplayer1.setEnabled(false);
                Intent discoverableIntent = new
                        Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivity(discoverableIntent);
                //dialog = ProgressDialog.show(BluetoothGame.this, "Wartet...", "Wartet auf Verbindung...", false, false);
                bluetoothSocket1 = new BluetoothSocket1(BluetoothGame.this);
                bluetoothSocket1.start();
                while (waittillconnected) {
                }
                if (waittillconnected = false) {
                    player1play();
                }

            }
        });
        buttonplayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonplayer2Pressed = true;
                buttonplayer2.setEnabled(false);
                buttonplayer1.setEnabled(false);
                findDevices();
                while (waittillconnected) {
                }
                if (waittillconnected = false) {
                    player2play();
                }
            }
        });
    }

    private void findDevices() {
        Intent i = new Intent(this, findBTDevices.class);
        startActivity(i);
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
        inputStream = null;
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "BluetoothGame Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.stefanbartos.toe_tac_tic/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "BluetoothGame Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.stefanbartos.toe_tac_tic/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
