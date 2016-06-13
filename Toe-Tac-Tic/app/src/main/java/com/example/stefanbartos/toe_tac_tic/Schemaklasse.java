package com.example.stefanbartos.toe_tac_tic;

/**
 * Created by StefanBartos on 13.06.16.
 */
public class Schemaklasse {
    public final static String TABLE_NAME = "toetactic";
    public final static String id = "id";
    public final static String Player1 = "Player1";
    public final static String Player2 = "Player2";
    public final static String player1score = "player1score";
    public final static String player2score = "player2score";
    public final static String[]All_COLUMNS = new String[] {id + " AS _id", Player1, Player2, player1score, player2score};

    public final static String SELECT = "SELECT "+id+" AS _id,"+Player1+", "+Player2+", "+player1score+", "+player2score+" FROM "+TABLE_NAME;

    public final static String SQL_DROP = "DROP TABLE IF EXISTS "+ TABLE_NAME;
    public final static String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    id + " INTEGER PRIMARY KEY," +
                    Player1 + " TEXT NOT NULL," +
                    Player2 + " TEXT NOT NULL," +
                    player1score + " INT NOT NULL," +
                    player2score + " INT NOT NULL" +
                    ")";
    String STMT_DELETE = "DELETE FROM " + TABLE_NAME;
    public final static String STMT_INSERT =
            "INSERT INTO " + TABLE_NAME +
                    " ("+ Player1 + "," + Player2 + "," + player1score + "," + player2score +") "
                    + "VALUES (?,?,?,?)";
}
