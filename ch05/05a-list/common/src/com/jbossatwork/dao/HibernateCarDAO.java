package com.jbossatwork.dao;

import java.util.*;
import org.hibernate.*;
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
        finally 
        {
            try 
            {
                if (session != null && session.isOpen()) {session.close();}
            } 
            catch (Exception e) 
            {
                System.out.println(e);
            }
        }
        
        return carList;
    }



 }