package com.bush.myapplication.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.car.button.CarLeftButtonHandler;
import com.bush.myapplication.car.button.CarRightButtonHandler;
import com.bush.myapplication.databinding.CarFragmentBinding;
import com.bush.myapplication.validator.FloatValidator;

public class CarFragment extends Fragment
{
    private CarFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = CarFragmentBinding.inflate(inflater, container, false);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.typeCarArray,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.carTypeSpinner.setAdapter(adapter);

        binding.prev.setOnClickListener(new CarLeftButtonHandler(binding, this));
        binding.next.setOnClickListener(new CarRightButtonHandler(binding, this));

        binding.powerInput.addTextChangedListener(new FloatValidator(binding.powerText));

        LoadSavedData();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    private void LoadSavedData()
    {
        var mtpl = MTPL.getInstance();
        if (mtpl.getCar().getPower() != 0)
            binding.powerInput.setText(
                    String.valueOf(mtpl.getCar().getPower()));
        if (mtpl.getCar().getCarType() != null)
            binding.carTypeSpinner.setSelection(mtpl.getCar().getCarType().ordinal());
    }
}
