package com.bush.myapplication.car.button;

import android.view.View;

import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.button.ClickHandler;
import com.bush.myapplication.car.CarFragment;
import com.bush.myapplication.car.CarFragmentDirections;
import com.bush.myapplication.car.builder.CarBuilder;
import com.bush.myapplication.databinding.CarFragmentBinding;
import com.bush.myapplication.person.Person;

import java.util.Calendar;

public class CarRightButtonHandler implements ClickHandler, View.OnClickListener
{
    private CarFragmentBinding binding;
    private CarFragment fragment;

    public CarRightButtonHandler(CarFragmentBinding binding, CarFragment fragment) {
        this.binding = binding;
        this.fragment = fragment;
    }

    @Override
    public void OnClickHandler()
    {
        MTPL.GetInstance().SetCar(new CarBuilder()
                .SetPower(ParseIntegerText(binding.powerInput.getText().toString()))
                .SetCarType(binding.carTypeSpinner.getSelectedItemPosition())
                .Build());

        if (MTPL.GetInstance().getPersonListSize() > 0)
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_carFragment_to_personListFragment);
        else {
            NavDirections action = CarFragmentDirections
                    .actionCarFragmentToPersonFragment(null);
            NavHostFragment.findNavController(fragment).navigate(action);
        }
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
            return 0;
        else
            return Integer.parseInt(str);
    }

    @Override
    public float ParseFloatText(String str) {
        return 0;
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
    public Calendar ParseDate(String str) {
        return null;
    }
}
