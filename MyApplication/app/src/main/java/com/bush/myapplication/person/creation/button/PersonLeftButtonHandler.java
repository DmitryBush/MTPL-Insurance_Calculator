package com.bush.myapplication.person.creation.button;

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

import java.time.LocalDate;
import java.util.Calendar;

public class PersonLeftButtonHandler implements ClickHandler, View.OnClickListener
{
    private PersonCreationFragment fragment;
    private Person driver;

    public PersonLeftButtonHandler(PersonCreationFragment fragment, Person driver)
    {
        this.fragment = fragment;
        this.driver = driver;
    }

    @Override
    public void OnClickHandler()
    {
        if (driver != null)
            NavHostFragment.findNavController(fragment)
                            .navigate(R.id.action_personCreationFragment_to_personListFragment);
        else
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_personFragment_to_carFragment);
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
    public Calendar ParseDate(String str)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        if (str.isEmpty())
            return calendar;
        else
        {
            String[] strArray = str.split("[.]");

            calendar.set(Integer.parseInt(strArray[2]),
                    Integer.parseInt(strArray[1]), Integer.parseInt(strArray[0]));
            return calendar;
        }
    }
}
