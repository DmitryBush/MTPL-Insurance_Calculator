package com.bush.myapplication;

import android.content.Context;

import com.bush.myapplication.car.Car;
import com.bush.myapplication.person.Person;
import com.bush.myapplication.structures.tableCAE.TableCAE;

import java.util.LinkedList;
import java.util.List;

public class MTPL
{
    private static volatile MTPL instance = null;

    private Car car;
    private List<Person> personList;

    private boolean driversLimit;
    private int carPeriod;
    private float limitingCoefficient;
    private float seasonalityCoefficient;

    public MTPL()
    {
        car = new Car();
        personList = new LinkedList<>();
    }

    public static MTPL GetInstance()
    {
        if (instance == null)
        {
            synchronized (MTPL.class)
            {
                if (instance == null)
                {
                    return instance = new MTPL();
                }
            }
        }
        return instance;
    }

    public void AppendPerson(Person human)
    {
        personList.add(human);
    }

    public Car getCar()
    {
        return car;
    }

    public void SetCar(Car car)
    {
        this.car = car;
    }

    public List<Person> getPersonList()
    {
        return personList;
    }

    public boolean isDriversLimit() {
        return driversLimit;
    }

    public void setDriversLimit(boolean driversLimit) {
        this.driversLimit = driversLimit;
        CalculateDriversLimitations();
    }

    private void CalculateDriversLimitations()
    {
        if (isDriversLimit())
            limitingCoefficient = 1;
        else
            limitingCoefficient = 2.32f;
    }

    public int getCarPeriod() {
        return carPeriod;
    }

    public void setCarPeriod(int carPeriod) {
        this.carPeriod = carPeriod;
        CalculateSeasonalityCoefficient();
    }

    private void CalculateSeasonalityCoefficient()
    {
        switch (carPeriod)
        {
            case 0:
                seasonalityCoefficient = 0.5f;
                break;
            case 1:
                seasonalityCoefficient = 0.6f;
                break;
            case 2:
                seasonalityCoefficient = 0.65f;
                break;
            case 3:
                seasonalityCoefficient = 0.7f;
                break;
            case 4:
                seasonalityCoefficient = 0.8f;
                break;
            case 5:
                seasonalityCoefficient = 0.9f;
                break;
            case 6:
                seasonalityCoefficient = 0.95f;
                break;
            case 7:
                seasonalityCoefficient = 1;
                break;
        }
    }

    public float CalculateMTPL()
    {
        Person resultPerson = new Person();
        for (Person i : personList)
        {
            resultPerson.setCAECoefficient(
                    Float.max(i.getCAECoefficient(), resultPerson.getCAECoefficient()));
            resultPerson.setAccidentRate(
                    Float.max(i.getAccidentRate(), resultPerson.getAccidentRate()));
            resultPerson.setTerritorialCoefficient(Float.max(
                            i.getTerritorialCoefficient(), resultPerson.getTerritorialCoefficient()));
        }
        System.out.println(car.getBasePrice());
        System.out.println(resultPerson.getTerritorialCoefficient());
        System.out.println(resultPerson.getAccidentRate());
        System.out.println(limitingCoefficient);
        System.out.println(resultPerson.getCAECoefficient());
        System.out.println(car.getPowerCoefficient());
        System.out.println(seasonalityCoefficient);

        return car.getBasePrice() * resultPerson.getTerritorialCoefficient()
                * resultPerson.getAccidentRate()
                * limitingCoefficient
                * resultPerson.getCAECoefficient()
                * car.getPowerCoefficient()
                * seasonalityCoefficient;
    }
}