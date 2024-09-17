package com.bush.myapplication.person.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.SQLCommands;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.person.creation.spinner.PersonPlaceActivity;
import com.bush.myapplication.person.builder.PersonBuilder;
import com.bush.myapplication.person.creation.button.PersonLeftButtonHandler;
import com.bush.myapplication.person.creation.button.PersonRightButtonHandler;

public class PersonCreationFragment extends Fragment
{
    private PersonCreationFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = PersonCreationFragmentBinding.inflate(inflater, container, false);

        Database database = new Database(getContext(), "RussianSubjects.db");
        PersonBuilder human = new PersonBuilder();

        binding.placeSpinner.setAdapter(database.ExecuteSQL(SQLCommands.Select,
                "Place", new String[]{"Subject"}));
        binding.placeSpinner.setOnItemSelectedListener(
                new PersonPlaceActivity(binding, database, human));

        binding.placeConcrSpinner.setAdapter(
                database.ExecuteSQL(
                        "select cities.id as _id, * " +
                                "FROM cities left join Place on 2 = Place.id " +
                                "AND cities.subject = 2 WHERE Place.Subject is NOT NULL",
                        new String[]{"city", "subject"}));

        binding.prev.setOnClickListener(new PersonLeftButtonHandler(binding, this,
                human, database));
        binding.next.setOnClickListener(new PersonRightButtonHandler(binding, this,
                human, database));
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
