package com.bush.myapplication.person.builder;

import android.content.Context;

import com.bush.myapplication.person.Person;

import java.time.LocalDate;
import java.util.Calendar;

public class PersonBuilder
{
    private Person instance;

    public PersonBuilder()
    {
        instance = new Person();
    }

    public PersonBuilder SetName(String name)
    {
        instance.setName(name);
        return this;
    }
    public PersonBuilder SetSurname(String surname)
    {
        instance.setSurname(surname);
        return this;
    }
    public PersonBuilder SetAge(int age)
    {
        instance.setAge(age);
        return this;
    }
    public PersonBuilder SetDateLicenseRelease(Calendar date)
    {
        instance.setDrivingLicenseRelease(date);
        return this;
    }
    public PersonBuilder SetRegion(int region)
    {
        instance.setRegion(region);
        return this;
    }
    public PersonBuilder SetCity(int city)
    {
        instance.setCity(city);
        return this;
    }
    public PersonBuilder SetTerritorialCoefficient(float coefficient)
    {
        instance.setTerritorialCoefficient(coefficient);
        return this;
    }
    public PersonBuilder SetAccidentRate(float rate)
    {
        instance.setAccidentRate(rate);
        return this;
    }
    public Person Build(Context context)
    {
        if (instance.getAge() != Integer.MIN_VALUE && instance.getExperience() != Float.MIN_VALUE)
            instance.CalculateCAECoefficient(context);
        return instance;
    }
}
