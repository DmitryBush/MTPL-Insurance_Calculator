package com.bush.myapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

public class Database
{
    private DatabaseOpenner dataBaseOpenner;

    public Database(Context context, String fileName)
    {
        dataBaseOpenner = new DatabaseOpenner(context, fileName);
    }

    public SQLiteDatabase open()
    {
        return dataBaseOpenner.open();
    }

    public Cursor ExecuteSQL(String command)
    {
        SQLiteDatabase db = dataBaseOpenner.open();

        return db.rawQuery(command, null);
    }
}
