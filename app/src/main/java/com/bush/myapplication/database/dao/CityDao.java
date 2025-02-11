package com.bush.myapplication.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.entity.City;
import com.bush.myapplication.database.entity.Subject;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CityDao implements DAO<City>{
    private static String[] columns = new String[]{"cities._id as city_id", "city",
            "coefficient", "f_key_subject", "Subject"};

    private static SQLiteDatabase db = null;
    @Override
    public List<City> findAll(QueryArgument arguments) {
        List<City> list = new LinkedList<>();
        try(Cursor cursor = db.query(arguments.table(),
                columns, arguments.filtration(), arguments.argsFiltration(), arguments.groupBy(),
                arguments.having(), arguments.orderBy(), arguments.limit())) {
            while (cursor.moveToNext())
                list.add(new City(
                        cursor.getInt(cursor.getColumnIndexOrThrow("city_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow(columns[1])),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(columns[2])),
                        new Subject(cursor.getInt(cursor.getColumnIndexOrThrow(columns[3])),
                                cursor.getString(cursor.getColumnIndexOrThrow(columns[4])))));
        }
        return list;
    }

    @Override
    public Optional<City> findById(QueryArgument arguments) {
        try(Cursor cursor = db.query(arguments.table(),
                columns, arguments.filtration(), arguments.argsFiltration(), arguments.groupBy(),
                arguments.having(), arguments.orderBy(), arguments.limit())) {
            City city = null;
            if (cursor.moveToFirst())
                city = new City(cursor.getInt(cursor.getColumnIndexOrThrow("city_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow(columns[1])),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(columns[2])),
                        new Subject(cursor.getInt(cursor.getColumnIndexOrThrow(columns[3])),
                                cursor.getString(cursor.getColumnIndexOrThrow(columns[4]))));
            return Optional.ofNullable(city);
        }
    }

    public CityDao() {
    }

    public static void createDatabase(Context context)
    {
        if (db == null)
        {
            synchronized (CaeDao.class)
            {
                if (db == null)
                    db = new Database(context, "RussianSubjects.db").open();
                var tmp = db.rawQuery("SELECT name from sqlite_sequence",
                        null);
                while (tmp.moveToNext())
                    System.out.println(tmp.getString(0));
                tmp.close();
            }
        }
    }
}
