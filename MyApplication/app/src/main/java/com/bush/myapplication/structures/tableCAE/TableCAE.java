package com.bush.myapplication.structures.tableCAE;

import android.content.Context;
import android.database.Cursor;

import com.bush.myapplication.database.Database;

import java.util.ArrayList;
import java.util.List;

public class TableCAE
{
    private List<TableProperties> table = new ArrayList<>();

    private class TableProperties
    {
        private int minAge;
        private int maxAge;
        private int minExperience;
        private int maxExperience;
        private float coefficient;

        public TableProperties(int minAge, int maxAge, int minExperience,
                               int maxExperience, float coefficient)
        {
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.minExperience = minExperience;
            this.maxExperience = maxExperience;
            this.coefficient = coefficient;
        }

        public boolean matches(int age, int experience)
        {
            return (age >= minAge && age <= maxAge) &&
                    (experience >= minExperience && experience <= maxExperience);
        }
    }

    public TableCAE(Context context)
    {
        Database database = new Database(context, "TableCAE.db");
        Cursor cursor = database.ExecuteSQL("select * from cae");

        while (cursor.moveToNext())
            table.add(new TableProperties(cursor.getInt(1), cursor.getInt(2),
                            cursor.getInt(3), cursor.getInt(4), cursor.getFloat(5)));
    }

    public float GetCoefficient(int age, int experience)
    {
        for (TableProperties i: table)
        {
            if (i.matches(age, experience))
                return i.coefficient;
        }
        throw new RuntimeException(String.format("Incorrect ratio:\nage: %d\nexperience: %d", age, experience));
    }
}
