package com.jbossatwork.dto;

import java.io.*;

/**
 * @hibernate.class
 *    table="ACCOUNTING"
 */
public class AccountingDTO implements Serializable
{
    private int id;
    private int carId;
    private double price;
    private java.sql.Date saleDate;

    public AccountingDTO()
    {
        this.id = -1;
        this.carId = -1;
        this.price = 0.00;
        this.saleDate = new java.sql.Date(new java.util.Date().getTime());
    }

    public AccountingDTO(int carId, double price)
    {
        this.id = -1;
        this.carId = carId;
        this.price = price;
        this.saleDate = new java.sql.Date(new java.util.Date().getTime());
    }

    public AccountingDTO(int id, int carId, double price, java.sql.Date saleDate)
    {
        this.id = id;
        this.carId = carId;
        this.price = price;
        this.saleDate = saleDate;
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
     *    column="CAR_ID"
     */
    public int getCarId()
    {
        return carId;
    }

    public void setCarId(int carId)
    {
        this.carId = carId;
    }

    /**
     * @hibernate.property
     *    column="PRICE"
     */
    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * @hibernate.property
     *    column="SALE_DATE"
     */
    public java.sql.Date getSaleDate()
    {
        return saleDate;
    }

    public void setSaleDate(java.sql.Date saleDate)
    {
        this.saleDate = saleDate;
    }
}