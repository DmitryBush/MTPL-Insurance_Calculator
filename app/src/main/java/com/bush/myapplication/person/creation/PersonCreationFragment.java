package com.bush.myapplication.person.creation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.database.SQLCommands;
import com.bush.myapplication.database.dao.QueryArgument;
import com.bush.myapplication.database.dao.SubjectDao;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.di.Autowired;
import com.bush.myapplication.di.DiContainer;
import com.bush.myapplication.person.Person;
import com.bush.myapplication.person.creation.button.AgeDatePicker;
import com.bush.myapplication.person.creation.button.LicenseDatePicker;
import com.bush.myapplication.person.creation.spinner.PersonPlaceActivity;
import com.bush.myapplication.person.creation.button.PersonLeftButtonHandler;
import com.bush.myapplication.person.creation.button.PersonRightButtonHandler;
import com.bush.myapplication.validator.DateValidator;
import com.bush.myapplication.validator.FloatValidator;
import com.bush.myapplication.validator.TextValidator;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;


public class PersonCreationFragment extends Fragment
{
    private PersonCreationFragmentBinding binding;

    @SuppressLint({"DefaultLocale", "SimpleDateFormat"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = PersonCreationFragmentBinding.inflate(inflater, container, false);
        PersonCreationFragmentArgs args = PersonCreationFragmentArgs.fromBundle(getArguments());

        var database = MTPL.getSubjectDao();
        Person driver = args.getDriver();

        try {
            System.out.println(driver);

            binding.nameInput.setText(driver.getName());
            binding.surnameInput.setText(driver.getSurname());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            binding.ageInput.setText(
                    simpleDateFormat.format(driver.getBirthdayDate().getTime()));
            binding.dlInput.setText(
                    simpleDateFormat.format(driver.getDrivingLicenseRelease().getTime()));

            binding.placeSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_item, database.findAll(
                    new QueryArgument("subjects", null,
                            null, null, null,
                            null, null)).stream()
                    .map(subject -> subject.subject()).collect(Collectors.toList())));
            binding.placeSpinner.setSelection(driver.getRegion());

            binding.kbmInput.setText(new Float(driver.getAccidentRate()).toString());
            binding.placeSpinner.setOnItemSelectedListener(
                    new PersonPlaceActivity(binding, driver));
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
            binding.placeSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_item, database.findAll(
                            new QueryArgument("subjects", null,
                                    null, null, null,
                                    null, null)).stream()
                    .map(subject -> subject.subject()).collect(Collectors.toList())));

            binding.placeSpinner.setOnItemSelectedListener(
                    new PersonPlaceActivity(binding, null));
        }
        finally {
            binding.prev.setOnClickListener(new PersonLeftButtonHandler(this, driver));
            binding.next.setOnClickListener(new PersonRightButtonHandler(binding, this,
                    driver));


            binding.dlInput.setOnClickListener(new LicenseDatePicker(getActivity(), binding));
            binding.ageInput.setOnClickListener(new AgeDatePicker(getActivity(), binding));

            binding.nameInput.addTextChangedListener(new TextValidator(binding.nameText));
            binding.surnameInput.addTextChangedListener(new TextValidator(binding.surnameText));

            binding.ageInput.addTextChangedListener(new DateValidator(binding.ageText));
            binding.dlInput.addTextChangedListener(new DateValidator(binding.dlText));
            binding.kbmInput.addTextChangedListener(new FloatValidator(binding.kbmText));
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
