package com.bush.myapplication.validator;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator implements TextWatcher {
    private TextInputLayout layout;

    public DateValidator(TextInputLayout layout) {
        this.layout = layout;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String str = charSequence.toString();
        if (str.length() <= 0){
            layout.setError("Обязательное поле");
            return;
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try
        {
            dateFormat.parse(str);
        } catch (ParseException e) {
            layout.setError("Неккоректный формат даты");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
