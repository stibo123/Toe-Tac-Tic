package com.example.stefanbartos.toe_tac_tic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class BTGame extends AppCompatActivity {
    String bluetoothName = "ToeTacTic";
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket bluetoothSocket = null;
    BluetoothServerSocket bluetoothServerSocket = null;
    UUID uuid = UUID.fromString("ccd95e70-33ba-11e6-bdf4-0800200c9a66");
    String myName;
    BTThreadHost thread = new BTThreadHost();
    Button player1bt;
    Button player2bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btgame);
        initBluetoothAdapter();
    }


    private void connectToOtherDevice() {

    }

    public void initBluetoothAdapter() {
        player1bt = (Button) findViewById(R.id.player1bt);
        player2bt = (Button) findViewById(R.id.player2bt);
        player1bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
            }
        });
        player2bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
        myName = uuid.toString();
        mBluetoothAdapter.startDiscovery();
        bluetoothName = mBluetoothAdapter.getName();
        mBluetoothAdapter.setName("ToeTacTic");
    }

    @Override
    public void onBackPressed() {
        mBluetoothAdapter.setName(bluetoothName);
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        mBluetoothAdapter.setName(bluetoothName);
        super.onStop();
    }
}


