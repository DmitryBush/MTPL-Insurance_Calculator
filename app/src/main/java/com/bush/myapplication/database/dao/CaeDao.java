package com.bush.myapplication.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.entity.Cae;
import com.bush.myapplication.database.entity.City;
import com.bush.myapplication.database.entity.Subject;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CaeDao implements DAO<Cae> {
    private static String[] columns = new String[]{"_id", "minAge", "maxAge", "minExperience",
                                                        "maxExperience", "coefficient"};

    private static SQLiteDatabase db = null;

    public List<Cae> findAll(QueryArgument arguments)
    {
        List<Cae> list = new LinkedList<>();
        try(Cursor cursor = db.query(arguments.table(),
                columns, arguments.filtration(), arguments.argsFiltration(), arguments.groupBy(),
                arguments.having(), arguments.orderBy(), arguments.limit())) {
            while (cursor.moveToNext())
                list.add(new Cae(
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[0])),
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[1])),
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[2])),
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[3])),
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[4])),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(columns[5]))));
        }
        return list;
    }

    @Override
    public Optional<Cae> findById(QueryArgument arguments) {
        try(Cursor cursor = db.query(arguments.table(),
                columns, arguments.filtration(), arguments.argsFiltration(), arguments.groupBy(),
                arguments.having(), arguments.orderBy(), arguments.limit())) {
            Cae cae = null;
            if (cursor.moveToFirst())
                cae = new Cae(
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[0])),
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[1])),
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[2])),
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[3])),
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[4])),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(columns[5])));
            return Optional.ofNullable(cae);
        }
    }

    public CaeDao() {}

    public static void createDatabase(Context context)
    {
        if (db == null)
        {
            synchronized (CaeDao.class)
            {
                if (db == null)
                    db = new Database(context, "TableCAE.db").open();
            }
        }
    }
}
