package com.example.stefanbartos.toe_tac_tic;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothSocket;
import android.os.Build;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by StefanBartos on 23.06.16.
 */
public class BluetoothInputStreamReader extends Thread{
        BluetoothSocket bluetoothSocket;
        InputStream inputStream;
        OutputStream outputStream;
    BluetoothGame bluetoothGame = new BluetoothGame();

        public BluetoothInputStreamReader(BluetoothSocket bluetoothSocket) {
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
                    bluetoothGame.setGetButtonString(bluetoothGame.getGetButtonString() + bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
