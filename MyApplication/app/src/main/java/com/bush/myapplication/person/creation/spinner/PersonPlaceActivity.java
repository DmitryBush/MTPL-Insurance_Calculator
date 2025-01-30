package com.bush.myapplication.person.creation.spinner;

import android.view.View;
import android.widget.AdapterView;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.person.Person;

public class PersonPlaceActivity implements AdapterView.OnItemSelectedListener
{
    private PersonCreationFragmentBinding binding;
    private Database database;
    private Person driver;

    public PersonPlaceActivity(PersonCreationFragmentBinding binding,
                               Database database,
                               Person driver)
    {
        this.binding = binding;
        this.database = database;
        this.driver = driver;
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
        if (driver != null)
            binding.placeConcrSpinner.setSelection(driver.getCity());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
