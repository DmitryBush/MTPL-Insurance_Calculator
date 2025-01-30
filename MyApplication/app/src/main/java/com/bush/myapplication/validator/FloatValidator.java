package com.bush.myapplication.validator;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class FloatValidator implements TextWatcher {
    private TextInputLayout layout;

    public FloatValidator(TextInputLayout layout) {
        this.layout = layout;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String str = charSequence.toString();

        if (str.length() <= 0) {
            layout.setError("Обязательное поле");
            return;
        }

        try {
            Float.parseFloat(str);
            layout.setError("");
        }
        catch (Exception e) {
            layout.setError("Неккоректное число");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
