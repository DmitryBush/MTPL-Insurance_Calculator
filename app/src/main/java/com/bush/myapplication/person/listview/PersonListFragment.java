package com.bush.myapplication.person.listview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.databinding.PersonListFragmentBinding;

public class PersonListFragment extends Fragment implements PersonRecyclerView.OnItemClickListener
{
    private PersonListFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = PersonListFragmentBinding.inflate(inflater, container, false);

        binding.driversView.setAdapter(new PersonRecyclerView(this));
        binding.driversView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.addDriverButton.setOnClickListener(view1 ->
                    NavHostFragment.findNavController(this)
                            .navigate(PersonListFragmentDirections
                                    .actionPersonListFragmentToPersonFragment(null)));

        binding.prev.setOnClickListener(view1 ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_personListFragment_to_carFragment));

        binding.next.setOnClickListener(view1 ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_personListFragment_to_insuranceFragment));
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        var mtpl = MTPL.getInstance();
        NavDirections action = PersonListFragmentDirections
                .actionPersonListFragmentToPersonFragment(
                        mtpl.getDriver(position));
        NavHostFragment.findNavController(this).navigate(action);
    }
}
