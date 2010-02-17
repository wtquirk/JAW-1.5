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
     * @ejb.transaction
     *    type="Required"
     *
     */
    public List listAvailableCars() throws EJBException {
    	CarDAO carDAO = new HibernateCarDAO();

        return carDAO.filterByStatus(CarDTO.STATUS_AVAILABLE);
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction
     *    type="Required"
     *
     */
    public CarDTO findCar(int id) throws EJBException {
    	CarDAO carDAO = new HibernateCarDAO();

    	return carDAO.findById(id);
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction
     *    type="Required"
     *
     */
    public void deleteCars(String[] ids) throws EJBException {
		CarDAO carDAO = new HibernateCarDAO();

		if (ids != null) {
			carDAO.delete(ids);
		}
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction
     *    type="Required"
     *
     */
    public void saveCar(CarDTO car) throws EJBException {
		CarDAO carDAO = new HibernateCarDAO();

		if(car.getId() == -1) {
			carDAO.create(car);
		} else {
			carDAO.update(car);
		}
    }

}
