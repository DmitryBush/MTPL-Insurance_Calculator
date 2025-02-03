package com.bush.myapplication.person.builder;

import android.annotation.SuppressLint;
import android.content.Context;

import com.bush.myapplication.person.Person;
import com.bush.myapplication.person.exception.AccidentRateException;
import com.bush.myapplication.person.exception.AgeDriverException;
import com.bush.myapplication.person.exception.DrivingLicenseException;
import com.bush.myapplication.person.exception.NameDriverException;
import com.bush.myapplication.person.exception.SurnameDriverException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    public PersonBuilder SetAge(String age) throws AgeDriverException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date date = formatter.parse(age);
            calendar.setTime(date);
            instance.setBirthdayDate(calendar);
        } catch (ParseException e) {
            throw new AgeDriverException("Date is invalid for parsing");
        }
        return this;
    }
    public PersonBuilder SetDateLicenseRelease(String drivingLicense) throws DrivingLicenseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(0);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date date = formatter.parse(drivingLicense);
            calendar.setTime(date);
            instance.setDrivingLicenseRelease(calendar);
        } catch (ParseException e) {
            throw new DrivingLicenseException("Date is invalid for parsing");
        }
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
    public Person Build(Context context) throws NameDriverException, SurnameDriverException, AgeDriverException, DrivingLicenseException, AccidentRateException {
        if (instance.getName() == null || instance.getName().isEmpty()) {
            throw new NameDriverException("Name is required");
        }
        if (instance.getSurname() == null || instance.getSurname().isEmpty()) {
            throw new SurnameDriverException("Surname is required");
        }
        if (instance.getAge() < 18 || instance.getBirthdayDate().after(Calendar.getInstance()))
            throw new AgeDriverException("Age must be between 18 and 150, not: " + instance.getAge());

        if (instance.getDrivingLicenseRelease() == null
                || instance.getDrivingLicenseRelease().after(Calendar.getInstance()))
            throw new DrivingLicenseException("Driving license release date is invalid");
        else if (instance.getAge() - instance.getExperience() < 18)
            throw new DrivingLicenseException("Driving license release date is invalid");
        else if (instance.getAge() - instance.getExperience() == 18 &&
                instance.getDrivingLicenseRelease().get(Calendar.DAY_OF_YEAR)
                < instance.getBirthdayDate().get(Calendar.DAY_OF_YEAR))
            throw new DrivingLicenseException("Driving license release date is invalid");

        if (instance.getRegion() < 0) {
            throw new IllegalStateException("Region must be a positive number");
        }
        if (instance.getCity() < 0) {
            throw new IllegalStateException("City must be a positive number");
        }
        if (instance.getAccidentRate() <= 0) {
            throw new AccidentRateException("Accident rate cannot be negative or zero");
        }

        instance.CalculateCAECoefficient(context);
        instance.CalculateTerritorialCoefficient(context);

        return instance;
    }
}
