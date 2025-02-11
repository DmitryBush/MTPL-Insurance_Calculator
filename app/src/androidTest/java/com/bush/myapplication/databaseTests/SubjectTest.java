package com.bush.myapplication.databaseTests;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.dao.CityDao;
import com.bush.myapplication.database.dao.QueryArgument;
import com.bush.myapplication.database.dao.SubjectDao;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;

@RunWith(AndroidJUnit4.class)
public class SubjectTest {
    @Test
    public void subjectTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        SubjectDao subjectDao = new SubjectDao();
        subjectDao.getClass()
                .getDeclaredMethod("createDatabase", Context.class)
                .invoke(subjectDao, context);

        CityDao cityDao = new CityDao();
        cityDao.getClass()
                .getDeclaredMethod("createDatabase", Context.class)
                .invoke(subjectDao, context);

        int count = subjectDao.findAll(new QueryArgument("subjects", null,
                null, null, null, null, null)).size();

        for (int i = 1; i <= count; i++)
        {
            var result = cityDao.findAll(new QueryArgument(
                    "cities inner join subjects on subjects._id = cities.f_key_subject",
                    "f_key_subject = ?",
                    new String[]{String.valueOf(i)},
                    null, null, null, null));
            if (result.size() <= 0)
                throw new SQLiteDatabaseCorruptException("An error has occuried at " + i + " id");
        }
    }
}
