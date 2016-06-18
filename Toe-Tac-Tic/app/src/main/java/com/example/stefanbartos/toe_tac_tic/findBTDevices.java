package com.example.stefanbartos.toe_tac_tic;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by StefanBartos on 18.06.16.
 */
public class findBTDevices extends AppCompatActivity {
    BluetoothAdapter adapter;
    ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    ArrayList<BluetoothDevice> pairedDeviceArrayList;
    BluetoothDevice device;
    BluetoothConnect bluetoothConnect;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_stats);
        adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            pairedDeviceArrayList = new ArrayList<BluetoothDevice>();

            for (BluetoothDevice device : pairedDevices) {
                pairedDeviceArrayList.add(device);
            }
            ListView lv = (ListView) findViewById(R.id.listView);

            pairedDeviceAdapter = new ArrayAdapter<BluetoothDevice>(this, android.R.layout.simple_list_item_1, pairedDeviceArrayList);
            lv.setAdapter(pairedDeviceAdapter);
            pairedDeviceAdapter.notifyDataSetChanged();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    device = (BluetoothDevice) parent.getItemAtPosition(position);
                    bluetoothConnect = new BluetoothConnect(getApplicationContext(), device);
                    bluetoothConnect.start();
                }
            });
        }
    }
}

