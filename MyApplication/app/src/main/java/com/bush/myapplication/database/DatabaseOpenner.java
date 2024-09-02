package com.bush.myapplication.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseOpenner extends SQLiteOpenHelper
{
    private String path;
    private String name;
    private Context context;

    public DatabaseOpenner(@Nullable Context context, String name)
    {
        super(context, name, null, 1);
        this.context = context;
        this.name = name;
        path = context.getFilesDir().getPath() + name;
    }

    public SQLiteDatabase open() throws SQLException
    {
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void create_db() {
        File file = new File(path);
        if (!file.exists()) {
            //получаем локальную бд как поток
            try (InputStream myInput = context.getAssets().open(name);
                 // Открываем пустую бд
                 OutputStream myOutput = new FileOutputStream(path)) {

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            } catch (IOException ex) {
                Log.d("Database", ex.getMessage());
            }
        }
    }
}
