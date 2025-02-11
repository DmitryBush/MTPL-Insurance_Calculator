package com.bush.myapplication.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.entity.Subject;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SubjectDao implements DAO<Subject> {
    private static String[] columns = new String[]{"_id", "Subject"};

    private static SQLiteDatabase db = null;
    @Override
    public List<Subject> findAll(QueryArgument arguments) {
        List<Subject> list = new LinkedList<>();
        try(Cursor cursor = db.query("subjects",
                columns, arguments.filtration(), arguments.argsFiltration(), arguments.groupBy(),
                arguments.having(), arguments.orderBy(), arguments.limit())) {
            while (cursor.moveToNext())
                list.add(new Subject(
                        cursor.getInt(cursor.getColumnIndexOrThrow(columns[0])),
                        cursor.getString(cursor.getColumnIndexOrThrow(columns[1]))));
        }
        return list;
    }

    public Optional<Subject> findById(QueryArgument arguments, SQLiteDatabase db)
    {
        try(Cursor cursor = db.query("subjects",
                columns, arguments.filtration(), arguments.argsFiltration(), arguments.groupBy(),
                arguments.having(), arguments.orderBy(), arguments.limit())) {
            Subject subject = null;
            if (cursor.moveToFirst())
                subject = new Subject(cursor.getInt(cursor.getColumnIndexOrThrow(columns[0])),
                        cursor.getString(cursor.getColumnIndexOrThrow(columns[1])));
            return Optional.ofNullable(subject);
        }
    }

    @Override
    public Optional<Subject> findById(QueryArgument arguments) {
        try(Cursor cursor = db.query("subjects",
                columns, arguments.filtration(), arguments.argsFiltration(), arguments.groupBy(),
                arguments.having(), arguments.orderBy(), arguments.limit())) {
            Subject subject = null;
            if (cursor.moveToFirst())
                subject = new Subject(cursor.getInt(cursor.getColumnIndexOrThrow(columns[0])),
                        cursor.getString(cursor.getColumnIndexOrThrow(columns[1])));
            return Optional.ofNullable(subject);
        }
    }

    public SubjectDao() {
    }

    public static void createDatabase(Context context)
    {
        if (db == null)
        {
            synchronized (CaeDao.class)
            {
                if (db == null)
                    db = new Database(context, "RussianSubjects.db").open();
            }
        }
    }
}
