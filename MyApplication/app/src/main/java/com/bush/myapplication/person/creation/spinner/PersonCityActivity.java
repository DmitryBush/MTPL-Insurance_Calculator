package com.bush.myapplication.person.creation.spinner;

import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.person.builder.PersonBuilder;

public class PersonCityActivity implements AdapterView.OnItemSelectedListener
{
    private PersonCreationFragmentBinding binding;
    private Database database;
    private PersonBuilder personBuilder;

    public PersonCityActivity(PersonCreationFragmentBinding binding,
                              Database database, PersonBuilder personBuilder) {
        this.binding = binding;
        this.database = database;
        this.personBuilder = personBuilder;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Cursor cursor = database.ExecuteSQL("select cities.id as _id, * " +
                "FROM cities left join Place on Place.id = " + (i + 1) +
                " AND cities.subject = " + (i + 1) + " WHERE Place.Subject is NOT NULL");
        if (cursor.moveToFirst())
        {
            cursor.move(i);
            personBuilder.SetCity(i).SetTerritorialCoefficient(cursor.getFloat(3));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
