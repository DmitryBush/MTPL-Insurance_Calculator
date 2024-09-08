package com.bush.myapplication.car.builder;

import com.bush.myapplication.car.Car;

public class CarBuilder
{
    private Car car;

    public CarBuilder()
    { car = new Car(); }

    public CarBuilder SetPower(int power)
    {
        car.setPower(power);
        return this;
    }

    public CarBuilder SetCarType(int type)
    {
        car.setCarType(type);
        return this;
    }

    public Car Build()
    {
        return car;
    }
}
