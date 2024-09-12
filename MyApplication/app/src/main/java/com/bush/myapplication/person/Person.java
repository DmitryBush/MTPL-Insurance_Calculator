package com.bush.myapplication.person;

import android.content.Context;

import com.bush.myapplication.structures.tableCAE.TableCAE;

import java.time.LocalDate;
import java.time.Period;

public class Person
{
    private static volatile TableCAE tableCAE = null;

    private String name;
    private String surname;
    private int age;
    private LocalDate drivingLicenseRelease;
    private int region;
    private int city;

    private float CAECoefficient;
    private float territorialCoefficient;
    private float accidentRate;
    private int experience;

    public Person()
    {
        name = new String();
        surname = new String();
        drivingLicenseRelease = null;

        age = Integer.MIN_VALUE;
        region = 0;
        city = 0;
        territorialCoefficient = 0;
        accidentRate = 0;
        experience = Integer.MIN_VALUE;
        CAECoefficient = 0;
    }

    public float getAccidentRate() {
        return accidentRate;
    }

    public float getTerritorialCoefficient() {
        return territorialCoefficient;
    }

    public int getAge() {
        return age;
    }

    public int getCity() {
        return city;
    }

    public int getRegion() {
        return region;
    }

    public void setAge(int age) {
        this.age = age;
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

    public LocalDate getDrivingLicenseRelease() {
        return drivingLicenseRelease;
    }

    public void setDrivingLicenseRelease(LocalDate drivingLicenseRelease) {
        this.drivingLicenseRelease = drivingLicenseRelease;
        CalculateExperience();
    }

    private void CalculateExperience()
    {
        experience = Period.between(drivingLicenseRelease, LocalDate.now()).getYears();
    }

    public int getExperience() {
        return experience;
    }

    public void setTerritorialCoefficient(float territorialCoefficient) {
        this.territorialCoefficient = territorialCoefficient;
    }

    public void setAccidentRate(float accidentRate) {
        this.accidentRate = accidentRate;
    }

    public float getCAECoefficient() {
        return CAECoefficient;
    }

    public void setCAECoefficient(float CAECoefficient) {
        this.CAECoefficient = CAECoefficient;
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
        CAECoefficient = tableCAE.GetCoefficient(age, experience);
    }
}
