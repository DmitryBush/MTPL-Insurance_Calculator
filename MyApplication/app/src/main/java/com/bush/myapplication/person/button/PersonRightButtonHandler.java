package com.bush.myapplication.person.button;

import android.database.Cursor;
import android.view.View;

import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.button.ClickHandler;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.person.PersonCreationFragment;
import com.bush.myapplication.person.builder.PersonBuilder;

import java.time.LocalDate;

public class PersonRightButtonHandler implements ClickHandler, View.OnClickListener
{
    private PersonCreationFragmentBinding binding;
    private PersonCreationFragment fragment;
    private PersonBuilder personBuilder;
    private Database database;

    public PersonRightButtonHandler(PersonCreationFragmentBinding binding,
                                   PersonCreationFragment fragment, PersonBuilder personBuilder,
                                    Database database)
    {
        this.binding = binding;
        this.fragment = fragment;
        this.personBuilder = personBuilder;
        this.database = database;
    }

    @Override
    public void OnClickHandler()
    {
        float coefficient = 0;
        Cursor cursor = database.ExecuteSQL("select cities.id as _id, * " +
                "FROM cities left join Place on Place.id = "
                + (binding.placeSpinner.getSelectedItemPosition() + 1) +
                " AND cities.subject = " + (binding.placeSpinner.getSelectedItemPosition() + 1)
                + " WHERE Place.Subject is NOT NULL");

        if (cursor.moveToFirst())
        {
            cursor.move(binding.placeConcrSpinner.getSelectedItemPosition());
            coefficient = cursor.getFloat(3);
        }
        MTPL.GetInstance().AppendPerson(personBuilder.SetName(binding.nameInput.getText().toString())
                .SetSurname(binding.surnameInput.getText().toString())
                .SetAge(ParseIntegerText(binding.ageText.getText().toString()))
                .SetDateLicenseRelease(ParseDate(binding.editTextDate.getText().toString()))
                .SetRegion(binding.placeSpinner.getSelectedItemPosition())
                .SetCity(binding.placeConcrSpinner.getSelectedItemPosition())
                .SetTerritorialCoefficient(coefficient)
                .SetAccidentRate(ParseFloatText(binding.KBMText.getText().toString()))
                .Build(fragment.getContext()));

        NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_personFragment_to_insuranceFragment);
    }

    @Override
    public void onClick(View view)
    {
        OnClickHandler();
    }
    @Override
    public int ParseIntegerText(String str)
    {
        if (str.isEmpty())
            return Integer.MIN_VALUE;
        else
            return Integer.parseInt(str.toString());
    }

    @Override
    public float ParseFloatText(String str)
    {
        if (str.isEmpty())
            return Float.MIN_VALUE;
        else
            return Float.parseFloat(str.toString());
    }

    @Override
    public boolean PearseBooleanValue(String str) {
        return false;
    }

    @Override
    public boolean PearseBooleanValue(int value) {
        return false;
    }

    @Override
    public LocalDate ParseDate(String str)
    {
        if (str.isEmpty())
            return LocalDate.of(1900, 1, 1);
        else
        {
            String[] strArray = str.split("[.]");

            return LocalDate.of(ParseIntegerText(strArray[2]), ParseIntegerText(strArray[1]),
                    ParseIntegerText(strArray[0]));
        }
    }
}
