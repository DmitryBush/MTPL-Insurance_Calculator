package com.bush.myapplication.database;

import android.app.Service;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

public class Database
{
    private DatabaseOpenner dataBaseOpenner;
    private Context context;

    public Database(Context context, String fileName)
    {
        this.context = context;
        dataBaseOpenner = new DatabaseOpenner(context, fileName);
        dataBaseOpenner.create_db();
    }

    public SimpleCursorAdapter ExecuteSQL(SQLCommands command, String table, String[] headers)
    {
        SQLiteDatabase db = dataBaseOpenner.open();
        String query = new String();
        Cursor cursor;

        switch (command)
        {
            case Select:
                query += " select ";
                cursor = db.rawQuery(query + " id as _id, * from " + table, null);
                break;
            default:
                throw new RuntimeException();
        }

        return new SimpleCursorAdapter(context, android.R.layout.simple_spinner_item,
                cursor, headers, new int[]{android.R.id.text1}, 0);
    }

    public SimpleCursorAdapter ExecuteSQL(String command, String[] headers)
    {
        SQLiteDatabase db = dataBaseOpenner.open();
        Cursor cursor;

        cursor = db.rawQuery(command, null);

        return new SimpleCursorAdapter(context, android.R.layout.simple_spinner_item,
                cursor, headers, new int[]{android.R.id.text1}, 0);
    }

    public Cursor ExecuteSQL(String command)
    {
        SQLiteDatabase db = dataBaseOpenner.open();

        return db.rawQuery(command, null);
    }
}
