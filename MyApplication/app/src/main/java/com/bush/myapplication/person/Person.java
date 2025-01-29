package com.bush.myapplication.person;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.bush.myapplication.structures.tableCAE.TableCAE;

import java.io.Serializable;
import java.util.Calendar;

public class Person implements Serializable
{
    private static volatile TableCAE tableCAE = null;

    private String name;
    private String surname;
    private int age;
    private Calendar drivingLicenseRelease;
    private int region;
    private int city;

    private float CAECoefficient;
    private float territorialCoefficient;
    private float accidentRate;
    private int experience;

    public Person()
    {
        name = null;
        surname = null;
        drivingLicenseRelease = null;

        age = 0;
        region = 0;
        city = 0;
        territorialCoefficient = 0;
        accidentRate = 0;
        experience = 0;
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

    public void setAge(Calendar age) {
        this.age = Calendar.getInstance().get(Calendar.YEAR) - age.get(Calendar.YEAR);

        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) < age.get(Calendar.DAY_OF_YEAR))
            this.age--;
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
        CalculateExperience();
    }

    private void CalculateExperience()
    {
        experience = Calendar.getInstance().get(Calendar.YEAR)
                - drivingLicenseRelease.get(Calendar.YEAR);

        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
                < drivingLicenseRelease.get(Calendar.DAY_OF_YEAR))
            experience--;
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

//    @Override
//    public int describeContents() {
//        return 0;
//    }

//    @Override
//    public void writeToParcel(@NonNull Parcel parcel, int i) {
//        parcel.writeString(name);
//        parcel.writeString(surname);
//        parcel.writeInt(age);
//        parcel.writeInt(region);
//        parcel.writeInt(city);
//        parcel.writeFloat(CAECoefficient);
//        parcel.writeFloat(territorialCoefficient);
//        parcel.writeFloat(accidentRate);
//        parcel.writeInt(experience);
//        parcel.writeSerializable(drivingLicenseRelease);
//    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", drivingLicenseRelease=" + String.format("%02d.%02d.%02d",
                drivingLicenseRelease.get(Calendar.DAY_OF_MONTH),
                drivingLicenseRelease.get(Calendar.MONTH),
                drivingLicenseRelease.get(Calendar.YEAR)) +
                ", region=" + region +
                ", city=" + city +
                ", CAECoefficient=" + CAECoefficient +
                ", territorialCoefficient=" + territorialCoefficient +
                ", accidentRate=" + accidentRate +
                ", experience=" + experience +
                '}';
    }
}
