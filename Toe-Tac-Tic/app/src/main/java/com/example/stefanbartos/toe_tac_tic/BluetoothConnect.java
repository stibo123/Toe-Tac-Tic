package com.example.stefanbartos.toe_tac_tic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by StefanBartos on 16.06.16.
 */
public class BluetoothConnect extends Thread {
    public BluetoothSocket bluetoothSocket;
    private static final UUID uuid = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    BluetoothGame bluetoothGame = new BluetoothGame();
    BluetoothAdapter bluetoothAdapter;
    Context context;

    public BluetoothConnect(Context context1, BluetoothDevice bluetoothDevice) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        context = context1;
        BluetoothSocket tmp = null;
        try {
            tmp = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
        }
        bluetoothSocket = tmp;
    }

    public void run() {
        bluetoothAdapter.cancelDiscovery();
        try {
            bluetoothSocket.connect();
        } catch (IOException e) {
            try {
                bluetoothSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


}
