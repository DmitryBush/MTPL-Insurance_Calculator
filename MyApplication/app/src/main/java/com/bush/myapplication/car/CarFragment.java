package com.bush.myapplication.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.R;
import com.bush.myapplication.databinding.CarFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

public class CarFragment extends Fragment implements AdapterView.OnItemSelectedListener
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
        binding.carTypeSpinner.setOnItemSelectedListener(this);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        System.out.println(i);
        System.out.println("Something...");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
