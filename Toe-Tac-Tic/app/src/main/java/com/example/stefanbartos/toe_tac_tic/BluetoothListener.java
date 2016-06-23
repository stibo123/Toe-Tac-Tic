package com.example.stefanbartos.toe_tac_tic;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothProfile;
import android.os.Build;

/**
 * Created by StefanBartos on 23.06.16.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public interface BluetoothListener extends BluetoothProfile.ServiceListener{
    
}
