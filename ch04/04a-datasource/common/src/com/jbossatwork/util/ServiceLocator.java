package com.jbossatwork.util;

import javax.naming.*;
import javax.sql.*;

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

}
