package com.bush.myapplication.person;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.bush.myapplication.database.Database;
import com.bush.myapplication.structures.tableCAE.TableCAE;

import java.io.Serializable;
import java.util.Calendar;

public class Person implements Serializable
{
    private static volatile TableCAE tableCAE = null;

    private String name;
    private String surname;
    private Calendar birthdayDate;
    private Calendar drivingLicenseRelease;
    private int region;
    private int city;

    private float CAECoefficient;
    private float territorialCoefficient;
    private float accidentRate;

    public Person()
    {
        name = null;
        surname = null;
        drivingLicenseRelease = null;

        region = 0;
        city = 0;
        territorialCoefficient = 0;
        accidentRate = 0;
        CAECoefficient = 0;
    }

    public float getAccidentRate() {
        return accidentRate;
    }

    public float getTerritorialCoefficient() {
        return territorialCoefficient;
    }

    public int getAge() {
        int age = Calendar.getInstance().get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);

        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) < birthdayDate.get(Calendar.DAY_OF_YEAR))
            age--;
        return age;
    }
    public Calendar getBirthdayDate() {return birthdayDate;}

    public int getCity() {
        return city;
    }

    public int getRegion() {
        return region;
    }

    public void setBirthdayDate(Calendar age) {
        birthdayDate = age;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Calendar getDrivingLicenseRelease() {
        return drivingLicenseRelease;
    }

    public void setDrivingLicenseRelease(Calendar drivingLicenseRelease) {
        this.drivingLicenseRelease = drivingLicenseRelease;
    }

    public int getExperience() {
        int experience = Calendar.getInstance().get(Calendar.YEAR)
                - drivingLicenseRelease.get(Calendar.YEAR);

        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
                < drivingLicenseRelease.get(Calendar.DAY_OF_YEAR))
            experience--;
        return experience;
    }

    public void setAccidentRate(float accidentRate) {
        this.accidentRate = accidentRate;
    }

    public float getCAECoefficient() {
        return CAECoefficient;
    }

    public void CalculateCAECoefficient(Context context)
    {
        if (tableCAE == null)
        {
            synchronized (TableCAE.class)
            {
                if (tableCAE == null)
                {
                    tableCAE = new TableCAE(context);
                }
            }
        }
        CAECoefficient = tableCAE.GetCoefficient(getAge(), getExperience());
    }

    public void CalculateTerritorialCoefficient(Context context)
    {
        Database database = new Database(context, "RussianSubjects.db");
        Cursor cursor = database.ExecuteSQL("select cities.id as _id, * " +
                "FROM cities inner join Place on Place.id = "
                + (region + 1) +
                " AND cities.subject = " + (region + 1));

        if (cursor.moveToFirst())
        {
            cursor.move(city);
            territorialCoefficient = cursor.getFloat(3);
        }
        cursor.close();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + getAge() +
                ", drivingLicenseRelease=" + String.format("%02d.%02d.%02d",
                drivingLicenseRelease.get(Calendar.DAY_OF_MONTH),
                drivingLicenseRelease.get(Calendar.MONTH),
                drivingLicenseRelease.get(Calendar.YEAR)) +
                ", region=" + region +
                ", city=" + city +
                ", CAECoefficient=" + CAECoefficient +
                ", territorialCoefficient=" + territorialCoefficient +
                ", accidentRate=" + accidentRate +
                ", experience=" + getExperience() +
                '}';
    }

}
