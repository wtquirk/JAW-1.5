package com.jbossatwork.dao;

import java.util.*;
import com.jbossatwork.dto.CarDTO;

public class CarDAO 
{
    private List carList; 
    
    public CarDAO() 
    {
        carList = new ArrayList();
        carList.add(new CarDTO("Toyota", "Camry", "2005"));
        carList.add(new CarDTO("Toyota", "Corolla", "1999"));
        carList.add(new CarDTO("Ford", "Explorer", "2005"));
    }
 
    public List findAll() 
    {
        return carList;
    }

 }