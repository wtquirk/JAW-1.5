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
    
    public CarDTO findById(int id)
    {
        for(Iterator i = carList.iterator(); i.hasNext(); )
        {
            CarDTO car = (CarDTO) i.next();
            if(car.getId() == id)
            {
                return car;
            }
        }
        
        return null;
    }
    
    public void create(CarDTO car)
    {
        carList.add(car);
    }
    
    public void update(CarDTO car)
    {
        for(ListIterator i = carList.listIterator(); i.hasNext(); )
        {
            CarDTO tmpCar = (CarDTO) i.next();
            if(car.getId() == tmpCar.getId())
            {
                i.set(car);
                return;
            }
        }    
    }

 }