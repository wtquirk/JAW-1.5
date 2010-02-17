package com.jbossatwork.dao;

import java.util.*;
import com.jbossatwork.dto.CarDTO;

public class InMemoryCarDAO implements CarDAO
{
    private List carList; 
    
    public InMemoryCarDAO() 
    {
        carList = new ArrayList();
        carList.add(new CarDTO(99, "Toyota", "Camry", "2005"));
        carList.add(new CarDTO(100, "Toyota", "Corolla", "1999"));
        carList.add(new CarDTO(101, "Ford", "Explorer", "2005"));
    }
 
    public List findAll() 
    {
        return carList;
    }
    
    public void create(CarDTO car)
    {}
    
 }