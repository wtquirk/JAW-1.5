package com.jbossatwork.ejb;

import java.util.*;

import javax.ejb.*;

import com.jbossatwork.dao.*;
import com.jbossatwork.dto.CarDTO;

/**
 * @ejb.bean
 *    name="InventoryFacade"
 *    display-name="InventoryFacadeSB"
 *    local-jndi-name="InventoryFacadeLocal"
 *    jndi-name="InventoryFacadeRemote"
 *    type="Stateless"
 *    transaction-type="Container"
 *
 * @ejb.resource-ref
 *    res-ref-name="hibernate/SessionFactory"
 *    res-type="org.hibernate.SessionFactory"
 *    res-auth="Container"
 *
 * @jboss.resource-ref
 *    res-ref-name="hibernate/SessionFactory"
 *    jndi-name="java:/hibernate/SessionFactory"
 *
 * @author Tom Marrs
 *
 */
public class InventoryFacadeBean implements SessionBean {

    private SessionContext sessionCtx;

	// EJB 2.1 mandated methods.

    public void setSessionContext(SessionContext sessionCtx) throws EJBException {
        this.sessionCtx = sessionCtx;
    }

    public void ejbCreate() throws CreateException {}
    public void ejbRemove() throws EJBException {}
    public void ejbActivate() throws EJBException {}
    public void ejbPassivate() throws EJBException {}

    // Business methods.

    /**
     * @ejb.interface-method
     *
     */
    public List listAvailableCars() throws EJBException {
    	CarDAO carDAO = new HibernateCarDAO();

        return carDAO.filterByStatus(CarDTO.STATUS_AVAILABLE);
    }

}
