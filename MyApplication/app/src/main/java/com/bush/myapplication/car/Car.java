package com.bush.myapplication.car;

public class Car
{
    private int power = 0;
    private CarType carType;

    public int getPower() {
        return power;
    }

    public CarType getCarType() {
        return carType;
    }

    public float getPowerCoefficient()
    {
        if (power > 0 && power <= 50)
            return 0.6f;
        else if (50 < power && power <= 70)
            return 1;
        else if (70 < power && power <= 100)
            return 1.1f;
        else if (100 < power && power <= 120)
            return 1.2f;
        else if (120 < power && power <= 150)
            return 1.4f;
        else
            return 1.6f;
    }

    public float getBasePrice()
    {
        if (carType == CarType.MOTORBIKE)
            return (324 + 2536) / 2;
        else
            return (1646 + 7535) / 2;
    }

    public void setPower(Integer power)
    {
        this.power = power;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
