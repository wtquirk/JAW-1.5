package com.jbossatwork.dto;


/**
 * @hibernate.class
 *    table="CAR"
 */
public class CarDTO
{
    private int id;
    private String make;
    private String model;
    private String modelYear;

    public CarDTO()
    {
        this.id = -1;
        this.make = "";
        this.model = "";
        this.modelYear = "";        
    }

    public CarDTO(String make, String model, String modelYear)
    {
        this.id = -1;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
    }

    public CarDTO(int id, String make, String model, String modelYear)
    {
        this.id = id;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
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
}
