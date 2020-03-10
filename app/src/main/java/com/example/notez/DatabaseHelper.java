package com.example.notez;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "note.db";
    private static final int DATABASE_VERSION = 1;
    // Lo statement SQL di creazione del database
    private static final String DATABASE_CREATE = "CREATE TABLE Note (_id integer primary key autoincrement, autore text not null, titolo text not null, testo text not null, data text not null)";

    // Costruttore
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Questo metodo viene chiamato durante la creazione del database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    public void onUpgrade(SQLiteDatabase database,int vecchiaVersione, int nuovaVersione) {
        //TODO Mettere upgrade

    }

}

