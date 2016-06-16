package com.example.stefanbartos.toe_tac_tic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by StefanBartos on 13.06.16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "toetactic.db";
    private final static int DB_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Schemaklasse.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Schemaklasse.SQL_DROP);
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
}
