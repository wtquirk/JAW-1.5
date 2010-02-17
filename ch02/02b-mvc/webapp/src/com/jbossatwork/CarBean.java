package com.jbossatwork;

public class CarBean
{
    private String make;
    private String model;
    private String modelYear;

    public CarBean(String make, String model, String modelYear)
    {
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getModelYear()
    {
        return modelYear;
    }

    public void setModelYear(String modelYear)
    {
        this.modelYear = modelYear;
    }
}
