package com.jbossatwork.dto;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name="ACCOUNTING")
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


    @Column(name="CAR_ID")
    public int getCarId()
    {
        return carId;
    }

    public void setCarId(int carId)
    {
        this.carId = carId;
    }


    @Column(name="PRICE")
    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }


    @Column(name="SALE_DATE")
    public java.sql.Date getSaleDate()
    {
        return saleDate;
    }

    public void setSaleDate(java.sql.Date saleDate)
    {
        this.saleDate = saleDate;
    }
}