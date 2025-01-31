package com.bush.myapplication.validator;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class TextValidator implements TextWatcher {
    private TextInputLayout layout;

    public TextValidator(TextInputLayout layout) {
        this.layout = layout;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String str = charSequence.toString();

        if (str.isEmpty())
            layout.setError("Обязательное поле");
        else
            layout.setError("");
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
