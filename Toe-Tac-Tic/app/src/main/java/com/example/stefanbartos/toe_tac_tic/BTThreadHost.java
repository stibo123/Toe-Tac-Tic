package com.example.stefanbartos.toe_tac_tic;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

/**
 * Created by StefanBartos on 16.06.16.
 */
public class BTThreadHost extends Thread {

    private BluetoothServerSocket bluetoothServerSocket = null;
    BTGame btGame = new BTGame();

    public BTThreadHost() {
        try {
            bluetoothServerSocket =
                    btGame.mBluetoothAdapter.listenUsingRfcommWithServiceRecord(btGame.myName, btGame.uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BluetoothSocket bluetoothSocket = null;

        if (bluetoothServerSocket != null) {
            try {
                bluetoothSocket = bluetoothServerSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BluetoothDevice remoteDevice = bluetoothSocket.getRemoteDevice();
        }

    }
}

