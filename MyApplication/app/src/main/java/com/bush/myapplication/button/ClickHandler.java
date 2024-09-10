package com.bush.myapplication.button;

import java.time.LocalDate;

public interface ClickHandler
{
    public void OnClickHandler();
    public int ParseNumericEditText(String str);
    public float ParseFloatText(String str);
    public boolean PearseBooleanValue(String str);
    public boolean PearseBooleanValue(int value);
    public LocalDate ParseDate(String str);
}
