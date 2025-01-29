package com.bush.myapplication.person.creation.button;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.View;

import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.button.ClickHandler;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.person.Person;
import com.bush.myapplication.person.creation.PersonCreationFragment;
import com.bush.myapplication.person.builder.PersonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class PersonRightButtonHandler implements ClickHandler, View.OnClickListener
{
    private PersonCreationFragmentBinding binding;
    private PersonCreationFragment fragment;
    private Person driver;

    public PersonRightButtonHandler(PersonCreationFragmentBinding binding,
                                   PersonCreationFragment fragment,
                                   Person driver)
    {
        this.binding = binding;
        this.fragment = fragment;
        this.driver = driver;
    }

    @Override
    public void OnClickHandler()
    {
        if (driver != null)
            MTPL.GetInstance().setDriver(MTPL.GetInstance().getPersonIndex(driver),
                            new PersonBuilder(driver)
                                    .SetName(binding.nameInput.getText().toString())
                                    .SetSurname(binding.surnameInput.getText().toString())
                                    .SetAge(ParseDate(binding.ageInput.getText().toString()))
                                    .SetDateLicenseRelease(ParseDate(binding.dlInput.getText().toString()))
                                    .SetRegion(binding.placeSpinner.getSelectedItemPosition())
                                    .SetCity(binding.placeConcrSpinner.getSelectedItemPosition())
                                    .SetAccidentRate(ParseFloatText(binding.kbmInput.getText().toString()))
                                    .Build(fragment.getContext()));
        else
            MTPL.GetInstance().AppendPerson(new PersonBuilder()
                    .SetName(binding.nameInput.getText().toString())
                    .SetSurname(binding.surnameInput.getText().toString())
                    .SetAge(ParseDate(binding.ageInput.getText().toString()))
                    .SetDateLicenseRelease(ParseDate(binding.dlInput.getText().toString()))
                    .SetRegion(binding.placeSpinner.getSelectedItemPosition())
                    .SetCity(binding.placeConcrSpinner.getSelectedItemPosition())
                    .SetAccidentRate(ParseFloatText(binding.kbmInput.getText().toString()))
                    .Build(fragment.getContext()));
        NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_personCreationFragment_to_personListFragment);
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
            throw new RuntimeException();
        else
            return Integer.valueOf(str.toString());
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
    public Calendar ParseDate(String str)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date date = formatter.parse(str);

            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
