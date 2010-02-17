package com.jbossatwork.util;

import javax.ejb.*;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.*;
import javax.rmi.*;
import javax.sql.*;

import org.hibernate.*;
import org.hibernate.exception.*;

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

    /**
     * Gets an EJB Local Home interface using the given JNDI name.
     *
     * @param localHomeJndiName The EJB Local Home interface's JNDI
     *                                            name.
     *
     * @return EJBLocalHome The EJB Local Home interface.
     *
     * @throws ServiceLocatorException If there are JNDI lookup problems.
     *
     * @see javax.ejb.EJBLocalHome
     */
    public static EJBLocalHome getEjbLocalHome(String localHomeJndiName) throws ServiceLocatorException {
        EJBLocalHome localHome = null;

        try {
            Context ctx = new InitialContext();

            localHome = (EJBLocalHome) ctx.lookup(localHomeJndiName);
        } catch (ClassCastException cce) {
            throw new ServiceLocatorException(cce);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        }

        return localHome;
    }

    /**
     * Gets an EJB Remote Home interface using the given JNDI name.
     *
     * @param remoteHomeJndiName The EJB Remote Home interface's JNDI
     *                                                name.
     *
     * @param ejbRemoteHomeClass The EJB Remote Home interface's Class.
     *
     * @return EJBHome The EJB Remote Home interface.
     *
     * @throws ServiceLocatorException If there are JNDI lookup problems.
     *
     * @see javax.ejb.EJBHome
     */
    public static EJBHome getEjbRemoteHome(String remoteHomeJndiName, Class ejbRemoteHomeClass) throws ServiceLocatorException {
        EJBHome remoteHome = null;

        try {
            Context ctx = new InitialContext();
            Object narrowFromObj = ctx.lookup(remoteHomeJndiName);

            remoteHome = (EJBHome) PortableRemoteObject.narrow(narrowFromObj,
                                                                                                ejbRemoteHomeClass);
        } catch (ClassCastException cce) {
            throw new ServiceLocatorException(cce);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        }

        return remoteHome;
    }

    /**
     * Gets a JMS <code>ConnectionFactory</code> using the given JNDI name.
     *
     * @param jmsConnectionFactoryJndiName The JMS <code>ConnectionFactory</code>'s JNDI name.
     *
     * @return ConnectionFactory The JMS <code>ConnectionFactory</code>.
     *
     * @throws ServiceLocatorException If there are JNDI lookup problems.
     *
     * @see javax.jms.ConnectionFactory
     */
    public static ConnectionFactory getJmsConnectionFactory(String jmsConnectionFactoryJndiName) throws ServiceLocatorException {
        ConnectionFactory jmsConnectionFactory = null;

        try {
            Context ctx = new InitialContext();

            jmsConnectionFactory = (ConnectionFactory) ctx.lookup(jmsConnectionFactoryJndiName);
        } catch (ClassCastException cce) {
            throw new ServiceLocatorException(cce);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        }

        return jmsConnectionFactory;
    }

    /**
     * Gets a JMS <code>Destination</code> using the given JNDI name.
     *
     * @param jmsDestinationJndiName The JMS <code>Destination</code>'s JNDI name.
     *
     * @return Destination The JMS <code>Destination</code>.
     *
     * @throws ServiceLocatorException If there are JNDI lookup problems.
     *
     * @see javax.jms.Destination
     */
    public static Destination getJmsDestination(String jmsDestinationJndiName) throws ServiceLocatorException {
        Destination jmsDestination = null;

        try {
            Context ctx = new InitialContext();

            jmsDestination = (Destination) ctx.lookup(jmsDestinationJndiName);
        } catch (ClassCastException cce) {
            throw new ServiceLocatorException(cce);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        }

        return jmsDestination;
    }

}
