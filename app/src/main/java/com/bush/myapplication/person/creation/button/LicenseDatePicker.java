package com.bush.myapplication.person.creation.button;

import android.annotation.SuppressLint;
import android.widget.DatePicker;

import androidx.fragment.app.FragmentActivity;

import com.bush.myapplication.button.DatePickerButton;
import com.bush.myapplication.databinding.PersonCreationFragmentBinding;

public class LicenseDatePicker extends DatePickerButton
{
    private PersonCreationFragmentBinding binding;
    public LicenseDatePicker(FragmentActivity activity, PersonCreationFragmentBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        super.onDateSet(datePicker, i, i1, i2);
        binding.dlInput.setText(String.format("%02d.%02d.%02d", i2, i1 + 1, i));
    }
}
