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
        return car;                               
    }




    public void create(CarDTO car)
    {
        Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY); 
            tx = session.beginTransaction();           
            session.save(car);
            tx.commit();
        } 
        catch (Exception e) 
        {
            try{tx.rollback();} 
            catch(Exception e2){System.out.println(e2);}
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
    }


    public void update(CarDTO car)
    {
        Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY); 
            tx = session.beginTransaction();           
            session.update(car);
            tx.commit();
        } 
        catch (Exception e) 
        {
            try{tx.rollback();} 
            catch(Exception e2){System.out.println(e2);}
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
    }


    public void delete(String[] ids)
    {
        Session session = null;
        Transaction tx = null;
        
        try 
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY); 
            tx = session.beginTransaction();                       
            for(int i=0; i < ids.length; i++)
            {
                CarDTO car = (CarDTO) session.get(CarDTO.class, new Integer(ids[i]));
                session.delete(car);
            }
            tx.commit();
        } 
        catch (Exception e) 
        {
            try{tx.rollback();} 
            catch(Exception e2){System.out.println(e2);}
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
    }




 }