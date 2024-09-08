package com.bush.myapplication.person.spinner;

import android.view.View;
import android.widget.AdapterView;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;

public class PersonPlaceActivity implements AdapterView.OnItemSelectedListener
{
    private PersonCreationFragmentBinding binding;
    private Database database;

    public PersonPlaceActivity(PersonCreationFragmentBinding binding, Database database)
    {
        this.binding = binding;
        this.database = database;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        binding.placeConcrSpinner.setAdapter(
                database.ExecuteSQL(
                        "select cities.id as _id, * " +
                                "FROM cities left join Place on Place.id = " + (i + 1) +
                                " AND cities.subject = " + (i + 1) + " WHERE Place.Subject is NOT NULL",
                        new String[]{"city", "subject"}));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
