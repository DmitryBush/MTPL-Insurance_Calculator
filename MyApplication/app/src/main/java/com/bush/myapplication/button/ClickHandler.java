package com.bush.myapplication.button;

import java.text.ParseException;
import java.util.Calendar;

public interface ClickHandler
{
    public void OnClickHandler();
    public int ParseIntegerText(String str);
    public float ParseFloatText(String str);
    public boolean PearseBooleanValue(String str);
    public boolean PearseBooleanValue(int value);
    public Calendar ParseDate(String str) throws ParseException;
}
