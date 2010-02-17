package com.jbossatwork.dao;

import java.util.*;
import javax.persistence.*;
import com.jbossatwork.dto.CarDTO;


public class HibernateCarDAO implements CarDAO
{

    private EntityManager manager;

    public HibernateCarDAO(EntityManager manager)
    {
        this.manager = manager;
    }

    public List findAll()
    {

	  List carList = new ArrayList();

        try
        {

		Query query = manager.createQuery("select c from CarDTO c"); 
		List<CarDTO> results = (List<CarDTO>)query.getResultList(); 
		for(CarDTO c : results)
 		{ 
			carList.add(c);
		} 


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + e.getStackTrace());
        }

        return carList;
    }


    public List filterByStatus(String status)
    {
        List availableCarList = new ArrayList();

        try
        {

		Query query = manager.createQuery("select c from CarDTO c where Status = :status"); 
		query.setParameter("status", status);
		List<CarDTO> results = (List<CarDTO>)query.getResultList(); 
		for(CarDTO c : results)
 		{ 
			availableCarList.add(c);
		} 

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
		e.printStackTrace();
        }

        return availableCarList;
    }

    public CarDTO findById(int id)
    {
        CarDTO car = null;

        try
        {
            car = (CarDTO) manager.find(CarDTO.class, new Integer(id));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + e.getStackTrace());
        }

        return car;
    }

    public void create(CarDTO car)
    {

        try
        {
            manager.persist(car);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + e.getStackTrace());
        }

    }

    public void update(CarDTO car)
    {

        try
        {
            manager.merge(car);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + e.getStackTrace());
        }

    }

    public void delete(String[] ids)
    {

        try
        {
            for(int i=0; i < ids.length; i++)
            {
                CarDTO car = (CarDTO) manager.find(CarDTO.class, new Integer(ids[i]));
                manager.remove(car);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + e.getStackTrace());
        }

    }

 }