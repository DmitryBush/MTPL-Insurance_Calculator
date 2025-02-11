package com.bush.myapplication;

import com.bush.myapplication.car.Car;
import com.bush.myapplication.database.dao.CaeDao;
import com.bush.myapplication.database.dao.CityDao;
import com.bush.myapplication.database.dao.SubjectDao;
import com.bush.myapplication.di.Autowired;
import com.bush.myapplication.di.Component;
import com.bush.myapplication.person.Person;

import java.util.LinkedList;
import java.util.List;

@Component
public class MTPL
{
    private static volatile MTPL instance = null;
    @Autowired
    private static CaeDao cae;
    @Autowired
    private static CityDao cityDao;
    @Autowired
    private static SubjectDao subjectDao;

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

    public static MTPL getInstance()
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

    public int getPersonIndex(Person person) { return personList.indexOf(person); }

    public int getPersonListSize() {return personList.size();}

    public void setDriver(int index, Person driver) {personList.set(index, driver);}

    public Person getDriver(int index) {return personList.get(index);}

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
        float CAECoefficient = 0, accidentRate = 0, territorialCoefficient = 0;
        for (Person i : personList)
        {
            CAECoefficient = Float.max(i.getCAECoefficient(), CAECoefficient);
            accidentRate = Float.max(i.getAccidentRate(), accidentRate);
            territorialCoefficient = Float.max(i.getTerritorialCoefficient(), territorialCoefficient);
        }
        System.out.println(car.getBasePrice());
        System.out.println(territorialCoefficient);
        System.out.println(accidentRate);
        System.out.println(limitingCoefficient);
        System.out.println(CAECoefficient);
        System.out.println(car.getPowerCoefficient());
        System.out.println(seasonalityCoefficient);

        return car.getBasePrice() * territorialCoefficient
                * accidentRate
                * limitingCoefficient
                * CAECoefficient
                * car.getPowerCoefficient()
                * seasonalityCoefficient;
    }

    public static CityDao getCityDao() {
        return cityDao;
    }

    public static CaeDao getCae() {
        return cae;
    }

    public static SubjectDao getSubjectDao() {
        return subjectDao;
    }
}