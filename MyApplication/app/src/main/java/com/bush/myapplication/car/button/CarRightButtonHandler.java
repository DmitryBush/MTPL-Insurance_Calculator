package com.bush.myapplication.car.button;

import android.view.View;

import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.button.ClickHandler;
import com.bush.myapplication.car.CarFragment;
import com.bush.myapplication.car.builder.CarBuilder;
import com.bush.myapplication.databinding.CarFragmentBinding;

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
                .SetPower(ParseNumericEditText())
                .SetCarType(binding.carTypeSpinner.getSelectedItemPosition())
                .Build());

        NavHostFragment.findNavController(fragment)
                .navigate(R.id.action_carFragment_to_personFragment);
    }

    @Override
    public void onClick(View view)
    {
        OnClickHandler();
    }
    @Override
    public int ParseNumericEditText()
    {
        if (binding.editTextNumberSigned.getText().toString().isEmpty())
            return 0;
        else
            return Integer.parseInt(binding.editTextNumberSigned.getText().toString());
    }
}
