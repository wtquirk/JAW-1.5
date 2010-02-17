package com.jbossatwork.dto;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name="CAR")
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


    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO) 
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }


    @Column(name="MAKE")
    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    @Column(name="MODEL")
    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }


    @Column(name="MODEL_YEAR")
    public String getModelYear()
    {
        return modelYear;
    }

    public void setModelYear(String modelYear)
    {
        this.modelYear = modelYear;
    }


    @Column(name="STATUS")
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
