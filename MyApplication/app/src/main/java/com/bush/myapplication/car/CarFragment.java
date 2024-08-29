package com.bush.myapplication.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.R;
import com.bush.myapplication.databinding.CarFragmentBinding;

public class CarFragment extends Fragment
{
    private CarFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        binding = CarFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.next.setOnClickListener(v ->
                NavHostFragment.findNavController(CarFragment.this)
                        .navigate(R.id.action_carFragment_to_personFragment)
        );

        binding.prev.setOnClickListener(v ->
                NavHostFragment.findNavController(CarFragment.this)
                        .navigate(R.id.action_carFragment_to_FirstFragment)
        );
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}
