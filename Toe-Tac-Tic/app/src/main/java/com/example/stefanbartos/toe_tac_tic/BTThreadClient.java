package com.example.stefanbartos.toe_tac_tic;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by StefanBartos on 16.06.16.
 */
public class BTThreadClient extends Thread {
    BTClient btClient = new BTClient();
    BluetoothSocket bluetoothSocket = null;
    BluetoothDevice bluetoothDevice = null;


    public BTThreadClient(BluetoothDevice device) {
        bluetoothDevice = device;

        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(btClient.uuid);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean success = false;
        try {
            bluetoothSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                bluetoothSocket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if(success){

        }else{
            //fail
        }
    }

    public void cancel() {


        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
