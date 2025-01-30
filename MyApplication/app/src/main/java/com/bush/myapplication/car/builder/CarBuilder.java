package com.bush.myapplication.car.builder;

import com.bush.myapplication.car.Car;
import com.bush.myapplication.car.CarType;
import com.bush.myapplication.car.exception.IncorrectPowerException;
import com.bush.myapplication.car.exception.UnknownCarTypeException;

public class CarBuilder
{
    private Car car;

    public CarBuilder()
    { car = new Car(); }

    public CarBuilder SetPower(int power) throws IncorrectPowerException {
        if (power <= 0)
            throw new IncorrectPowerException("Incorrect vehicle power " + power);
        car.setPower(power);
        return this;
    }

    public CarBuilder SetCarType(int type) throws UnknownCarTypeException {
        if (type >= 0 && type < CarType.values().length)
            car.setCarType(CarType.values()[type]);
        else
            throw new UnknownCarTypeException("Unknown type of car received " + type);
        return this;
    }

    public Car Build()
    {
        return car;
    }
}
