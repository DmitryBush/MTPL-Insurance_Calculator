package com.bush.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    ) {

        binding = InsuranceFragmentBinding.inflate(inflater, container, false);
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
