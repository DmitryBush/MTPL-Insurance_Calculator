package com.bush.myapplication;

import com.bush.myapplication.car.Car;
import com.bush.myapplication.person.Person;

import java.util.LinkedList;
import java.util.List;

public class MTPL
{
    private static volatile MTPL instance = null;

    private Car car;
    private List<Person> personList;

    private boolean driversLimit;
    private int carPeriod;

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
}