package com.example.stefanbartos.toe_tac_tic;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by StefanBartos on 18.06.16.
 */
public class findBTDevices extends AppCompatActivity {
    BluetoothAdapter adapter;

    ArrayAdapter<String> pairedDeviceAdapter;
    ArrayList<String> pairedDeviceName;
    ArrayList<BluetoothDevice> pairedDeviceArrayList;

    ArrayAdapter<String> newDeviceAdapter;
    ArrayList<String> newDeviceName;
    ArrayList<BluetoothDevice> newDeviceArrayList;

    BluetoothDevice device;
    BluetoothConnect bluetoothConnect;
    ListView lvpaireddevices;
    ListView lvnewdevices;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_showdevices);
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(discoverableIntent);
        adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.startDiscovery();

        final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    newDeviceArrayList.add(device);
                    newDeviceName.add(device.getName());
                    newDeviceAdapter.notifyDataSetChanged();
                }
                else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    if(newDeviceArrayList.isEmpty()) {
                        Toast.makeText(findBTDevices.this, "Keine neuen Geräte gefunden! :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            pairedDeviceArrayList = new ArrayList<BluetoothDevice>();
            pairedDeviceName = new ArrayList<String>();

            for (BluetoothDevice device : pairedDevices) {
                pairedDeviceArrayList.add(device);
                pairedDeviceName.add(device.getName());
            }
            lvpaireddevices = (ListView) findViewById(R.id.lvpaireddevices);

            pairedDeviceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pairedDeviceName);
            lvpaireddevices.setAdapter(pairedDeviceAdapter);
            pairedDeviceAdapter.notifyDataSetChanged();

            lvpaireddevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    device = (BluetoothDevice) pairedDeviceArrayList.get(position);
                    unregisterReceiver(mReceiver);
                    bluetoothConnect = new BluetoothConnect(getApplicationContext(), device);
                    bluetoothConnect.start();
                }
            });
        }
        newDeviceArrayList = new ArrayList<BluetoothDevice>();
        newDeviceName = new ArrayList<String>();
        newDeviceAdapter = new ArrayAdapter<String>(findBTDevices.this, android.R.layout.simple_list_item_1, newDeviceName);
        lvnewdevices = (ListView) findViewById(R.id.lvnewdevices);
        lvnewdevices.setAdapter(newDeviceAdapter);

        lvnewdevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                device = (BluetoothDevice) newDeviceArrayList.get(position);
                unregisterReceiver(mReceiver);
                bluetoothConnect = new BluetoothConnect(getApplicationContext(), device);
                bluetoothConnect.start();
            }
        });


    }
}

