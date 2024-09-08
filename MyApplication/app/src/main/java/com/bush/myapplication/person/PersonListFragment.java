package com.bush.myapplication.person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bush.myapplication.R;
import com.bush.myapplication.databinding.PersonListFragmentBinding;

public class PersonListFragment extends Fragment
{
    private PersonListFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = PersonListFragmentBinding.inflate(inflater, container, false);

        //PersonRecyclerView adapter = new PersonRecyclerView();

        binding.driversView.setAdapter(new PersonRecyclerView());
        binding.driversView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

//        binding.next.setOnClickListener(v ->
//                NavHostFragment.findNavController(PersonCreationFragment.this)
//                        .navigate(R.id.action_personFragment_to_insuranceFragment)
//        );
//
//        binding.prev.setOnClickListener(v ->
//                NavHostFragment.findNavController(PersonCreationFragment.this)
//                        .navigate(R.id.action_personFragment_to_carFragment)
//        );
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}
