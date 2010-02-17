package com.jbossatwork.dto;

import java.io.*;

/**
 * @hibernate.class
 *    table="CAR"
 */
public class CarDTO implements Serializable
{
	public static final String STATUS_AVAILABLE = "Available";
	public static final String STATUS_SOLD = "Sold";

    private int id;
    private String make;
    private String model;
    private String modelYear;
    private String status;

    public CarDTO()
    {
        this.id = -1;
        this.make = "";
        this.model = "";
        this.modelYear = "";
        this.status = CarDTO.STATUS_AVAILABLE;
    }

    public CarDTO(String make, String model, String modelYear)
    {
        this.id = -1;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.status = CarDTO.STATUS_AVAILABLE;
    }

    public CarDTO(int id, String make, String model, String modelYear)
    {
        this.id = id;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.status = CarDTO.STATUS_AVAILABLE;
    }


    /**
     * @hibernate.id
     *    generator-class="native"
     *    column="ID"
     */
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @hibernate.property
     *    column="MAKE"
     */
    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    /**
     * @hibernate.property
     *    column="MODEL"
     */
    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    /**
     * @hibernate.property
     *    column="MODEL_YEAR"
     */
    public String getModelYear()
    {
        return modelYear;
    }

    public void setModelYear(String modelYear)
    {
        this.modelYear = modelYear;
    }

    /**
     * @hibernate.property
     *    column="STATUS"
     */
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
