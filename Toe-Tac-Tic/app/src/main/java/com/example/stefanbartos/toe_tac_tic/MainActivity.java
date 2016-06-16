package com.example.stefanbartos.toe_tac_tic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonOnePlayer;
    private Button buttonTwoPlayer;
    private Button buttonBTGame;
    private Button buttonStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initButtons();

    }

    public void initButtons() {
        buttonOnePlayer = (Button) findViewById(R.id.buttonOnePlayer);
        buttonTwoPlayer = (Button) findViewById(R.id.buttonTwoPlayer);
        buttonBTGame = (Button) findViewById(R.id.buttonBTGame);
        buttonStats = (Button) findViewById(R.id.buttonStats);

        buttonOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OPintent = new Intent(getApplicationContext(), OnePlayer.class);
                startActivity(OPintent);
            }
        });

        buttonTwoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TPintent = new Intent(getApplicationContext(), TwoPlayer.class);
                startActivity(TPintent);
            }
        });

        buttonBTGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bluetooth eingeschaltet? ;)", Toast.LENGTH_LONG).show();
                Intent BTGame = new Intent(getApplicationContext(), BTGame.class);
                startActivity(BTGame);
            }
        });

        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statsIntent = new Intent(getApplicationContext(), Stats.class);
                startActivity(statsIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                startActivity(new Intent(this, Preference_Activity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
