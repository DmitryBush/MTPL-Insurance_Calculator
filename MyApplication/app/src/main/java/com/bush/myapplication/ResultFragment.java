package com.bush.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.databinding.ResultFragmentBinding;

public class ResultFragment extends Fragment
{
    private ResultFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ResultFragmentBinding.inflate(inflater, container, false);
        binding.result.setText(String.valueOf(MTPL.GetInstance().CalculateMTPL(getContext())));

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.prev.setOnClickListener(v ->
                NavHostFragment.findNavController(ResultFragment.this)
                        .navigate(R.id.action_resultFragment_to_newPersonFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
