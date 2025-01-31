package com.bush.myapplication.button;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

public class DatePickerButton extends DialogFragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener
{
    private FragmentActivity activity;

    public DatePickerButton(FragmentActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of TimePickerDialog and return it.
        return new DatePickerDialog(getContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {}

    @Override
    public void onClick(View view)
    {
        show(activity.getSupportFragmentManager(), "datePicker");
    }
}
