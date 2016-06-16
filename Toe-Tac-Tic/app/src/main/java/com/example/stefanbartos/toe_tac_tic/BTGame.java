package com.example.stefanbartos.toe_tac_tic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BTGame extends AppCompatActivity {
    private String bluetoothName = "ToeTacTic";
    private BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket bluetoothSocket = null;
    private BluetoothServerSocket bluetoothServerSocket = null;
    UUID uuid;
    String myName;
    //uuid:ccd95e70-33ba-11e6-bdf4-0800200c9a66


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btgame);
        initBluetoothAdapter();

    }


    private void connectToOtherDevice() {

    }

    public void initBluetoothAdapter() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
        bluetoothName = mBluetoothAdapter.getName();
        uuid = UUID.fromString("ec79da00-853f-11e4-b4a9-0800200c9a66");
        myName = uuid.toString();
        mBluetoothAdapter.setName("ToeTacTic");
        mBluetoothAdapter.startDiscovery();
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

    private class connectToOtherDevice extends Thread
    {
            bluetoothServerSocket = mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(myName,uuid);
    } catch (IOException e) {
        e.printStackTrace();
    }
        if(bluetoothServerSocket==null)
        {
            try {
                bluetoothSocket = bluetoothServerSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BluetoothDevice remoteDevice = bluetoothSocket.getRemoteDevice();
        }
    }

}


