package com.bush.myapplication.databaseTests;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.bush.myapplication.database.Database;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SubjectTest {
    @Test
    public void subjectTest()
    {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Database database = new Database(context, "RussianSubjects.db");
        Cursor cursor = database.ExecuteSQL("select count(*) as subjects from Place");
        cursor.moveToFirst();
        int count= cursor.getInt(0);

        for (int i = 1; i <= count; i++)
        {
            cursor = database.ExecuteSQL("select cities.id as _id, * " +
                    "FROM cities inner join Place on Place.id = "
                    + i +
                    " AND cities.subject = " + i);
            if (cursor.getCount() <= 0)
                throw new SQLiteDatabaseCorruptException("An error has occuried at " + i + " id");
        }
    }
}
