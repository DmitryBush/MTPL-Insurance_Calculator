package com.bush.myapplication.person.creation.button;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.View;

import androidx.navigation.fragment.NavHostFragment;

import com.bush.myapplication.MTPL;
import com.bush.myapplication.R;
import com.bush.myapplication.button.ClickHandler;
import com.bush.myapplication.database.Database;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;
import com.bush.myapplication.person.Person;
import com.bush.myapplication.person.creation.PersonCreationFragment;
import com.bush.myapplication.person.builder.PersonBuilder;
import com.bush.myapplication.person.exception.AccidentRateException;
import com.bush.myapplication.person.exception.AgeDriverException;
import com.bush.myapplication.person.exception.DrivingLicenseException;
import com.bush.myapplication.person.exception.NameDriverException;
import com.bush.myapplication.person.exception.SurnameDriverException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class PersonRightButtonHandler implements ClickHandler, View.OnClickListener
{
    private PersonCreationFragmentBinding binding;
    private PersonCreationFragment fragment;
    private Person driver;

    public PersonRightButtonHandler(PersonCreationFragmentBinding binding,
                                   PersonCreationFragment fragment,
                                   Person driver)
    {
        this.binding = binding;
        this.fragment = fragment;
        this.driver = driver;
    }

    @Override
    public void OnClickHandler()
    {
        try {
            if (driver != null)
                MTPL.GetInstance().setDriver(MTPL.GetInstance().getPersonIndex(driver),
                        new PersonBuilder(driver)
                                .SetName(binding.nameInput.getText().toString())
                                .SetSurname(binding.surnameInput.getText().toString())
                                .SetAge(binding.ageInput.getText().toString())
                                .SetDateLicenseRelease(binding.dlInput.getText().toString())
                                .SetRegion(binding.placeSpinner.getSelectedItemPosition())
                                .SetCity(binding.placeConcrSpinner.getSelectedItemPosition())
                                .SetAccidentRate(ParseFloatText(binding.kbmInput.getText().toString()))
                                .Build(fragment.getContext()));
            else
                MTPL.GetInstance().AppendPerson(new PersonBuilder()
                        .SetName(binding.nameInput.getText().toString())
                        .SetSurname(binding.surnameInput.getText().toString())
                        .SetAge(binding.ageInput.getText().toString())
                        .SetDateLicenseRelease(binding.dlInput.getText().toString())
                        .SetRegion(binding.placeSpinner.getSelectedItemPosition())
                        .SetCity(binding.placeConcrSpinner.getSelectedItemPosition())
                        .SetAccidentRate(ParseFloatText(binding.kbmInput.getText().toString()))
                        .Build(fragment.getContext()));
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_personCreationFragment_to_personListFragment);
        } catch (NameDriverException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());

            builder.setTitle("Некорректный ввод имени");
            builder.setMessage("Введено пустое имя");
            builder.setCancelable(false);
            builder.setPositiveButton("Ок",
                    (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
                    });
            builder.create().show();
        } catch (SurnameDriverException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());

            builder.setTitle("Некорректный ввод фамилии");
            builder.setMessage("Введена пустая фамилия");
            builder.setCancelable(false);
            builder.setPositiveButton("Ок",
                    (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
            builder.create().show();
        } catch (AgeDriverException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());

            builder.setTitle("Некорректный ввод даты рождения");
            builder.setMessage("Введена неккоректная дата");
            builder.setCancelable(false);
            builder.setPositiveButton("Ок",
                    (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
            builder.create().show();
        } catch (AccidentRateException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());

            builder.setTitle("Некорректный ввод коэффицента КБМ");
            builder.setMessage("Введена неккоректная дата");
            builder.setCancelable(false);
            builder.setPositiveButton("Ок",
                    (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
            builder.create().show();
        } catch (DrivingLicenseException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());

            builder.setTitle("Некорректный ввод даты выдачи ВУ");
            builder.setMessage("Введена неккоректная дата");
            builder.setCancelable(false);
            builder.setPositiveButton("Ок",
                    (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
            builder.create().show();
        }
    }

    @Override
    public void onClick(View view)
    {
        OnClickHandler();
    }
    @Override
    public int ParseIntegerText(String str)
    {
        if (str.isEmpty())
            throw new RuntimeException();
        else
            return Integer.valueOf(str.toString());
    }

    @Override
    public float ParseFloatText(String str)
    {
        if (str.isEmpty())
            return Float.MIN_VALUE;
        else
            return Float.parseFloat(str.toString());
    }

    @Override
    public boolean PearseBooleanValue(String str) {
        return false;
    }

    @Override
    public boolean PearseBooleanValue(int value) {
        return false;
    }


    public Calendar ParseDate(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        Date date = formatter.parse(str);

        calendar.setTime(date);
        return calendar;
    }
}
