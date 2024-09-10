package com.bush.myapplication.car;

public class Car
{
    private float powerCoefficient;
    private float basePrice;

    private int power;
    private int carType;

    public Car()
    {
        this.power = 0;
        this.carType = 0;
        this.powerCoefficient = 0;
        this.basePrice = 0;
    }

    public int getPower() {
        return power;
    }

    public int getCarType() {
        return carType;
    }

    private void CalculatePowerCoefficient()
    {
        if (power > 0 && power <= 50)
            powerCoefficient = 0.6f;
        else if (50 < power && power <= 70)
            powerCoefficient = 1;
        else if (70 < power && power <= 100)
            powerCoefficient = 1.1f;
        else if (100 < power && power <= 120)
            powerCoefficient = 1.2f;
        else if (120 < power && power <= 150)
            powerCoefficient = 1.4f;
        else
            powerCoefficient = 1.6f;
    }

    private void CalculateBasePrice()
    {
        if (carType == 0)
            basePrice = (324 + 2536) / 2;
        else
            basePrice = (1646 + 7535) / 2;
    }

    public void setPower(Integer power)
    {
        this.power = power;
        CalculatePowerCoefficient();
    }

    public void setCarType(int carType) {
        this.carType = carType;
        CalculateBasePrice();
    }

    public float getBasePrice() {
        return basePrice;
    }

    public float getPowerCoefficient() {
        return powerCoefficient;
    }
}
