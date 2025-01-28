package com.bush.myapplication.person.creation;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.SQLCommands;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.button.DatePickerButton;
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

//    public void setDate(View v)
//    {
//        new DatePickerDialog(getContext(), dateSetListener,
//                MTPL.GetInstance().getPersonList().get(0)
//                        .getDrivingLicenseRelease().get(Calendar.YEAR),
//                MTPL.GetInstance().getPersonList().get(0)
//                        .getDrivingLicenseRelease().get(Calendar.YEAR),
//                MTPL.GetInstance().getPersonList().get(0)
//                        .getDrivingLicenseRelease().get(Calendar.YEAR))
//                .show();
//    }

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


        binding.dlInput.setOnClickListener(new LicenseDatePicker(getActivity(), binding));
        binding.ageInput.setOnClickListener(new AgeDatePicker(getActivity(), binding));
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
