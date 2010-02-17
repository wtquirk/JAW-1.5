package com.jbossatwork.ejb;

import java.util.*;

import javax.ejb.*;

import com.jbossatwork.dao.*;
import com.jbossatwork.dto.CarDTO;
import com.jbossatwork.dto.AccountingDTO;

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
 *    res-type="net.sf.hibernate.SessionFactory"
 *    res-auth="Container"
 *
 * @jboss.resource-ref
 *    res-ref-name="hibernate/SessionFactory"
 *    jndi-name="java:/hibernate/SessionFactory"
 *
 * @ejb.security-role-ref
 *    role-name="Manager"
 *    role-link="Manager"
 *
 * @ejb.security-role-ref
 *    role-name="guest"
 *    role-link="guest"
 *
 * @author Tom Marrs
 *
 */
public class InventoryFacadeBean implements SessionBean {

    private SessionContext sessionCtx;

    public void setSessionContext(SessionContext sessionCtx) throws EJBException {
        this.sessionCtx = sessionCtx;
    }

	// EJB 2.1 mandated methods.

    /**
     * @ejb.create-method
     * @ejb.permission
     *    role-name="guest,Manager"
     *
     */
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
     * @ejb.permission
     *    role-name="guest,Manager"
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
     * @ejb.permission
     *    role-name="guest,Manager"
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
     * @ejb.permission
     *    role-name="Manager"
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
     * @ejb.permission
     *    role-name="Manager"
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

    /**
     * @ejb.interface-method
     * @ejb.transaction
     *    type="Required"
     *
     * @ejb.permission
     *    role-name="guest,Manager"
     *
     */
    public void buyCar(int carId, double price) throws EJBException {
		CarDAO carDAO = new HibernateCarDAO();
		CarDTO car;
		AccountingDAO accountingDAO = new HibernateAccountingDAO();
		AccountingDTO accountingData;

		car = carDAO.findById(carId);
		car.setStatus(CarDTO.STATUS_SOLD);
		carDAO.update(car);

		accountingData = new AccountingDTO(carId, price);
		accountingDAO.create(accountingData);
    }

}
