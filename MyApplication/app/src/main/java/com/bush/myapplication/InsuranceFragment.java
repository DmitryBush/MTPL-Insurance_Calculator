package com.bush.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.car.CarFragment;
import com.bush.myapplication.databinding.FragmentSecondBinding;
import com.bush.myapplication.databinding.InsuranceFragmentBinding;

public class InsuranceFragment extends Fragment
{
    private InsuranceFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
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
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.next.setOnClickListener(v ->
                NavHostFragment.findNavController(InsuranceFragment.this)
                        .navigate(R.id.action_insuranceFragment_to_newPersonFragment)
        );

        binding.prev.setOnClickListener(v ->
                NavHostFragment.findNavController(InsuranceFragment.this)
                        .navigate(R.id.action_insuranceFragment_to_personFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
