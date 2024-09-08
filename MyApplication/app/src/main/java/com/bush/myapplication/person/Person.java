package com.bush.myapplication.person;

public class Person
{
    public static int id = 0;

    private String name;
    private String surname;
    private int age;
    private int region;
    private int city;

    private int CAECoefficient;
    private int territorialCoefficient;
    private float accidentRate;

    public Person()
    {
        name = new String();
        surname = new String();

        age = 0;
        region = 0;
        city = 0;
        CAECoefficient = 0;
        territorialCoefficient = 0;
        accidentRate = 0;
        id++;
    }

    public float getAccidentRate() {
        return accidentRate;
    }

    public int getTerritorialCoefficient() {
        return territorialCoefficient;
    }

    public int getCAECoefficient() {
        return CAECoefficient;
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
}
