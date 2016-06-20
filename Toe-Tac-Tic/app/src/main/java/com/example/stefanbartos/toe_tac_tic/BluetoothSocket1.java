package com.example.stefanbartos.toe_tac_tic;

import android.content.Intent;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by StefanBartos on 16.06.16.
 */
public class BluetoothSocket1 extends Thread{
    private static  String NAME = "";
    private static final UUID MY_UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
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
                    Intent i = new Intent(context, test.class);
                    context.startActivity(i);
                    //bluetoothGame.game(socket);
                }
            }
        }

    }
