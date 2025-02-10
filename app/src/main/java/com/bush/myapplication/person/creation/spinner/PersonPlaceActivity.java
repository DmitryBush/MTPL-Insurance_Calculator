package com.bush.myapplication.person.creation.spinner;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.dao.CityDao;
import com.bush.myapplication.database.dao.QueryArgument;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.di.Autowired;
import com.bush.myapplication.person.Person;

import java.util.stream.Collectors;

public class PersonPlaceActivity implements AdapterView.OnItemSelectedListener
{
    private PersonCreationFragmentBinding binding;
    private CityDao database = MTPL.getCityDao();
    private Person driver;

    public PersonPlaceActivity(PersonCreationFragmentBinding binding,
                               Person driver)
    {
        this.binding = binding;
        this.driver = driver;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        binding.placeConcrSpinner.setAdapter(
                new ArrayAdapter<String>(adapterView.getContext(), android.R.layout.simple_spinner_item,
                        database.findAll(
                        new QueryArgument("cities inner join subjects on " +
                                "subjects._id = cities.f_key_subject",
                                "f_key_subject = ?", new String[]{String.valueOf(i + 1)},
                                null, null, null, null)).stream()
                        .map(city -> city.city()).collect(Collectors.toList()))
        );
        if (driver != null)
            binding.placeConcrSpinner.setSelection(driver.getCity());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
