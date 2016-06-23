package com.example.stefanbartos.toe_tac_tic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyReceiver extends BroadcastReceiver {
    BluetoothGame btgame;
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        btgame = new BluetoothGame();
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        if(extras==null)
        {
            return;
        }
        switch (action)
        {
            case "android.bluetooth.adapter.action.STATE_CHANGED":
                bluetoothStateChanged(extras.getInt("android.bluetooth.adapter.extra.STATE"));
                break;
            case "android.bluetooth.device.action.BOND_STATE_CHANGED":
                bondStateChanged(extras.getInt("android.bluetooth.device.extra.BOND_STATE"), (BluetoothDevice)extras.get("android.bluetooth.device.extra.DEVICE"));
                break;
            case "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED":
                a2dpStateChanged(extras.getInt("android.bluetooth.profile.extra.STATE"), (BluetoothDevice)extras.get("android.bluetooth.device.extra.DEVICE"));
                break;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void a2dpStateChanged(int state, BluetoothDevice bluetoothDevice) {
        switch (state) {
            case BluetoothProfile.STATE_DISCONNECTED:
                break;
            case BluetoothProfile.STATE_CONNECTING:
                break;
            case BluetoothProfile.STATE_CONNECTED:
                btgame.setWaittillconnected(false);
                break;
            case BluetoothProfile.STATE_DISCONNECTING:
                break;
        }
    }

    private void bondStateChanged(int state, BluetoothDevice bluetoothDevice) {
        switch (state) {
            case BluetoothDevice.BOND_NONE:
                break;
            case BluetoothDevice.BOND_BONDING:
                break;
            case BluetoothDevice.BOND_BONDED:
                break;
        }
    }

    private void bluetoothStateChanged(int state) {
        switch (state) {
            case BluetoothAdapter.STATE_OFF:
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                break;
            case BluetoothAdapter.STATE_ON:
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                break;
        }
    }
}
