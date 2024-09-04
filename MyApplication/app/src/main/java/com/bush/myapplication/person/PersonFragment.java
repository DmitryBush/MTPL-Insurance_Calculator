package com.bush.myapplication.person;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.R;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.DatabaseOpenner;
import com.bush.myapplication.database.SQLCommands;
import com.bush.myapplication.databinding.PersonFragmentBinding;
import com.bush.myapplication.person.spinner.PersonPlaceActivity;

public class PersonFragment extends Fragment implements AdapterView.OnItemSelectedListener
{
    private PersonFragmentBinding binding;
    private PersonPlaceActivity spinnerActivity;
    //private Database database;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = PersonFragmentBinding.inflate(inflater, container, false);

        Database database = new Database(getContext(), "RussianSubjects.db");

        binding.placeSpinner.setAdapter(database.ExecuteSQL(SQLCommands.Select,
                "Place", new String[]{"Subject"}));
        spinnerActivity = new PersonPlaceActivity(binding, database);
        binding.placeSpinner.setOnItemSelectedListener(spinnerActivity);

        binding.placeConcrSpinner.setAdapter(
                database.ExecuteSQL(
                        "select cities.id as _id, * " +
                                "FROM cities left join Place on 2 = Place.id " +
                                "AND cities.subject = 2 WHERE Place.Subject is NOT NULL",
                        new String[]{"city", "subject"}));
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.next.setOnClickListener(v ->
                NavHostFragment.findNavController(PersonFragment.this)
                        .navigate(R.id.action_personFragment_to_insuranceFragment)
        );

        binding.prev.setOnClickListener(v ->
                NavHostFragment.findNavController(PersonFragment.this)
                        .navigate(R.id.action_personFragment_to_carFragment)
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
