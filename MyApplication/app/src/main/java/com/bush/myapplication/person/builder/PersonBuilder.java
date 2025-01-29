package com.bush.myapplication.person.builder;

import android.content.Context;

import com.bush.myapplication.person.Person;

import java.util.Calendar;

public class PersonBuilder
{
    private Person instance;

    public PersonBuilder()
    {
        instance = new Person();
    }

    public PersonBuilder(Person instance) {
        this.instance = instance;
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
    public PersonBuilder SetAge(Calendar age)
    {
        instance.setBirthdayDate(age);
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
    public PersonBuilder SetAccidentRate(float rate)
    {
        instance.setAccidentRate(rate);
        return this;
    }
    public Person Build(Context context)
    {
        if (instance.getName() == null || instance.getName().isEmpty()) {
            throw new IllegalStateException("Name is required");
        }
        if (instance.getSurname() == null || instance.getSurname().isEmpty()) {
            throw new IllegalStateException("Surname is required");
        }
        if (instance.getAge() < 18 || instance.getAge() > 150) {
            throw new IllegalStateException("Age must be between 18 and 150, not: " + instance.getAge());
        }
        if (instance.getDrivingLicenseRelease() == null || instance.getDrivingLicenseRelease().after(Calendar.getInstance())) {
            throw new IllegalStateException("Driving license release date is invalid");
        }
        if (instance.getRegion() <= 0) {
            throw new IllegalStateException("Region must be a positive number");
        }
        if (instance.getCity() <= 0) {
            throw new IllegalStateException("City must be a positive number");
        }
        if (instance.getAccidentRate() < 0) {
            throw new IllegalStateException("Accident rate cannot be negative");
        }
        instance.CalculateCAECoefficient(context);
        instance.CalculateTerritorialCoefficient(context);

        return instance;
    }
}
