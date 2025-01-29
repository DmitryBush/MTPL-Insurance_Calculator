package com.bush.myapplication.person.creation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.SQLCommands;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.person.Person;
import com.bush.myapplication.person.creation.button.AgeDatePicker;
import com.bush.myapplication.person.creation.button.LicenseDatePicker;
import com.bush.myapplication.person.creation.spinner.PersonPlaceActivity;
import com.bush.myapplication.person.builder.PersonBuilder;
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
        //Person driver = args.getDriver();
        Database database = new Database(getContext(), "RussianSubjects.db");
        PersonBuilder human = new PersonBuilder();

        try {
            Person driver = args.getDriver();
            System.out.println(driver);

            binding.nameInput.setText(driver.getName());
            binding.surnameInput.setText(driver.getSurname());
            //binding.ageInput.setText();
            Calendar tmp = driver.getDrivingLicenseRelease();
            binding.dlInput.setText(String.format("%02d.%02d.%02d",
                    tmp.get(Calendar.DAY_OF_MONTH),
                    tmp.get(Calendar.MONTH),
                    tmp.get(Calendar.YEAR)));
            binding.placeConcrSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("Item selected: " + position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    System.out.println("Nothing selected");
                }
            });

            binding.placeSpinner.setAdapter(database.ExecuteSQL(SQLCommands.Select,
                    "Place", new String[]{"Subject"}));
            binding.placeSpinner.setSelection(driver.getRegion());

            binding.kbmInput.setText(new Float(driver.getAccidentRate()).toString());
            binding.placeSpinner.setOnItemSelectedListener(
                    new PersonPlaceActivity(binding, database, human));
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
            binding.placeSpinner.setAdapter(database.ExecuteSQL(SQLCommands.Select,
                "Place", new String[]{"Subject"}));
//            binding.placeSpinner.setOnItemSelectedListener(
//                    new PersonPlaceActivity(binding, database, human));

            binding.placeSpinner.setOnItemSelectedListener(
                    new PersonPlaceActivity(binding, database, human));
        }
        finally {
//            binding.placeSpinner.setOnItemSelectedListener(
//                    new PersonPlaceActivity(binding, database, human));

            binding.prev.setOnClickListener(new PersonLeftButtonHandler(binding, this,
                    human, database));
            binding.next.setOnClickListener(new PersonRightButtonHandler(binding, this,
                    human, database));


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
