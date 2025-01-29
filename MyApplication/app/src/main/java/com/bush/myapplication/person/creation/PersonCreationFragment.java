package com.bush.myapplication.person.creation;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bush.myapplication.R;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.SQLCommands;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.person.Person;
import com.bush.myapplication.person.creation.button.AgeDatePicker;
import com.bush.myapplication.person.creation.button.LicenseDatePicker;
import com.bush.myapplication.person.creation.spinner.PersonPlaceActivity;
import com.bush.myapplication.person.creation.button.PersonLeftButtonHandler;
import com.bush.myapplication.person.creation.button.PersonRightButtonHandler;

import java.util.Calendar;


public class PersonCreationFragment extends Fragment
{
    private PersonCreationFragmentBinding binding;

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = PersonCreationFragmentBinding.inflate(inflater, container, false);
        PersonCreationFragmentArgs args = PersonCreationFragmentArgs.fromBundle(getArguments());

        Database database = new Database(getContext(), "RussianSubjects.db");
        Person driver = args.getDriver();

        try {
            System.out.println(driver);

            binding.nameInput.setText(driver.getName());
            binding.surnameInput.setText(driver.getSurname());

            Calendar tmpDate = driver.getBirthdayDate();
            binding.ageInput.setText(String.format("%02d.%02d.%02d",
                    tmpDate.get(Calendar.DAY_OF_MONTH),
                    tmpDate.get(Calendar.MONTH),
                    tmpDate.get(Calendar.YEAR)));
            tmpDate = driver.getDrivingLicenseRelease();
            binding.dlInput.setText(String.format("%02d.%02d.%02d",
                    tmpDate.get(Calendar.DAY_OF_MONTH),
                    tmpDate.get(Calendar.MONTH),
                    tmpDate.get(Calendar.YEAR)));


            binding.placeSpinner.setAdapter(database.ExecuteSQL(SQLCommands.Select,
                    "Place", new String[]{"Subject"}));
            binding.placeSpinner.setSelection(driver.getRegion());

            binding.kbmInput.setText(new Float(driver.getAccidentRate()).toString());
            binding.placeSpinner.setOnItemSelectedListener(
                    new PersonPlaceActivity(binding, database, driver));
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
            binding.placeSpinner.setAdapter(database.ExecuteSQL(SQLCommands.Select,
                "Place", new String[]{"Subject"}));

            binding.placeSpinner.setOnItemSelectedListener(
                    new PersonPlaceActivity(binding, database, null));
        }
        finally {
            binding.prev.setOnClickListener(new PersonLeftButtonHandler(this, driver));
            binding.next.setOnClickListener(new PersonRightButtonHandler(binding, this,
                    driver));


            binding.dlInput.setOnClickListener(new LicenseDatePicker(getActivity(), binding));
            binding.ageInput.setOnClickListener(new AgeDatePicker(getActivity(), binding));
        }
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}
