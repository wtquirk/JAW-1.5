package com.jbossatwork.util;

import javax.naming.*;
import javax.sql.*;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

/**
 * <code>ServiceLocator</code> encapsulates JNDI lookups to make it
 * easier to access JNDI-based resources (EJBs, DataSources,
 * JMS Destinations, and so on).
 *
 */
public class ServiceLocator {

    /**
     * Making the default (no arg) constructor private
     * ensures that this class cannnot be instantiated.  
     */
    private ServiceLocator() {}
    
    
    /**
     * Gets a <code>DataSource</code> using the given JNDI name.
     *
     * @param dataSourceJndiName The <code>DataSource</code>'s JNDI name.
     *
     * @return DataSource The <code>DataSource</code>.
     *
     * @throws ServiceLocatorException If there are JNDI lookup problems.
     *
     * @see javax.sql.DataSource
     */
    public static DataSource getDataSource(String dataSourceJndiName) throws ServiceLocatorException {
        DataSource dataSource = null;

        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup(dataSourceJndiName);
            
        } catch (ClassCastException cce) {
            throw new ServiceLocatorException(cce);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        }

        return dataSource;
    }


    public static SessionFactory getHibernateSessionFactory(String jndiSessionFactoryName) throws ServiceLocatorException {
        SessionFactory sessionFactory = null;

        try {
            Context ctx = new InitialContext();
            sessionFactory = (SessionFactory) ctx.lookup(jndiSessionFactoryName);
            
        } catch (ClassCastException cce) {
            throw new ServiceLocatorException(cce);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        }

        return sessionFactory;
    }
    
    public static Session getHibernateSession(String jndiSessionFactoryName) throws ServiceLocatorException {
        Session session = null;

        try 
        {
            session = getHibernateSessionFactory(jndiSessionFactoryName).openSession();            
        } 
        catch (Exception e) 
        {
            throw new ServiceLocatorException(e);
        }
        return session;
    }
    



}
