package com.bush.myapplication.insurance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.databinding.InsuranceFragmentBinding;
import com.bush.myapplication.insurance.buttons.InsuranceLeftButtonHandler;
import com.bush.myapplication.insurance.buttons.InsuranceRightButtonHandler;

public class InsuranceFragment extends Fragment
{
    private InsuranceFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        binding = InsuranceFragmentBinding.inflate(inflater, container, false);

        ArrayAdapter<CharSequence> restrictionsAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.driversRestrictions,
                android.R.layout.simple_spinner_item);
        restrictionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> vehiclePeriodAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.vechiclePeriod,
                android.R.layout.simple_spinner_item);
        vehiclePeriodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerUsers.setAdapter(restrictionsAdapter);
        binding.spinnerPer.setAdapter(vehiclePeriodAdapter);

        binding.prev.setOnClickListener(new InsuranceLeftButtonHandler(this, binding));
        binding.next.setOnClickListener(new InsuranceRightButtonHandler(this, binding));

        LoadSavedData();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void LoadSavedData()
    {
        var mtpl = MTPL.getInstance();
        if (mtpl.isDriversLimit())
            binding.spinnerUsers.setSelection(1);
        else
            binding.spinnerUsers.setSelection(0);

        binding.spinnerPer.setSelection(mtpl.getCarPeriod());
    }
}
