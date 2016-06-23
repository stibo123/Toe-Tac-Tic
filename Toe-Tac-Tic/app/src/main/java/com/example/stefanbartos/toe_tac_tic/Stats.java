package com.example.stefanbartos.toe_tac_tic;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    ListView listView;
    ArrayList<String> al = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        listView = (ListView) findViewById(R.id.listView);
        initVariables();
        initListData();
        displayItems();
    }

    private void initVariables(){
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                entrySelected(position);
            }
        });
    }

    private void entrySelected(final int pos)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final LinearLayout vDialog = (LinearLayout) this.getLayoutInflater().inflate(R.layout.stats_dialog, null);
            alert.setView(vDialog);
            alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    al.remove(pos);
                    displayItems();
                }
            });

            alert.setNegativeButton("NO", null);
            alert.show();

        }

                private void initListData() {
                    Cursor cursor = db.query(Schemaklasse.TABLE_NAME, Schemaklasse.All_COLUMNS, null, null, null, null, null);
                    int Player1 = cursor.getColumnIndex(Schemaklasse.Player1);
                    int Player2 = cursor.getColumnIndex(Schemaklasse.Player2);
                    int player1score = cursor.getColumnIndex(Schemaklasse.player1score);
                    int player2score = cursor.getColumnIndex(Schemaklasse.player2score);
                    while (cursor.moveToNext()) {
                        al.add(cursor.getString(Player1) + ":" + cursor.getString(Player2) + "  \t " + cursor.getInt(player1score) + ":" + cursor.getInt(player2score));
                    }
                    cursor.close();
                    db.close();
                }

                private void displayItems() {
                    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
                    listView.setAdapter(adapter);
                }

                @Override
                public boolean onOptionsItemSelected(MenuItem item) {
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.deleteallstats:
                            deleteallStats();
                    }
                    return super.onOptionsItemSelected(item);
                }

                private void deleteallStats() {
                    db.delete(Schemaklasse.TABLE_NAME, null, null);
                    al.clear();
                    adapter.notifyDataSetChanged();
                }

                @Override
                public boolean onCreateOptionsMenu(Menu menu) {
                    getMenuInflater().inflate(R.menu.menu_stats, menu);
                    return super.onCreateOptionsMenu(menu);
                }
            }
