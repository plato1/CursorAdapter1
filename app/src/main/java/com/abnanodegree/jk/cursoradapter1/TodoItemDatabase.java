package com.abnanodegree.jk.cursoradapter1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jk on 1/9/2017.
 */

public class TodoItemDatabase extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "file1.db";
    static final int DATABASE_VERSION = 2;
    static final String TABLE_NAME = "table1";
    static public String ROW_ID = "id";
    static public String ROW_NAME ="name";

    public TodoItemDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // These is where we need to write create table statements.
    // This is called when database is created.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL for creating the tables
    }

    // This method is called when database is upgraded like
    // modifying the table structure,
    // adding constraints to database, etc
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        // SQL for upgrading the tables
    }
}
