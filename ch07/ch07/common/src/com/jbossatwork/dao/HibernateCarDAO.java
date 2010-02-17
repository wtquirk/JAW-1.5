package com.jbossatwork.dao;

import java.util.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import com.jbossatwork.dto.CarDTO;
import com.jbossatwork.util.*;

public class HibernateCarDAO implements CarDAO
{
    private List carList;
    private static final String HIBERNATE_SESSION_FACTORY="java:comp/env/hibernate/SessionFactory";

    public HibernateCarDAO()
    {}

    public List findAll()
    {
        List carList = new ArrayList();
        Session session = null;

        try
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY);
            Criteria criteria = session.createCriteria(CarDTO.class);
            carList = criteria.list();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return carList;
    }


    public List filterByStatus(String status)
    {
        List availableCarList = new ArrayList();
        Session session = null;

        try
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY);

            Criteria criteria = session.createCriteria(CarDTO.class)
                                       .add( Restrictions.eq("status", status));

            availableCarList = criteria.list();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return availableCarList;
    }

    public CarDTO findById(int id)
    {
        CarDTO car = null;
        Session session = null;

        try
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY);
            car = (CarDTO) session.get(CarDTO.class, new Integer(id));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return car;
    }

    public void create(CarDTO car)
    {
        Session session = null;

        try
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY);
            session.save(car);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void update(CarDTO car)
    {
        Session session = null;

        try
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY);
            session.update(car);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void delete(String[] ids)
    {
        Session session = null;

        try
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY);
            for(int i=0; i < ids.length; i++)
            {
                CarDTO car = (CarDTO) session.get(CarDTO.class, new Integer(ids[i]));
                session.delete(car);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

 }