package com.jbossatwork.ejb;

import java.util.*;

import javax.ejb.*;

import com.jbossatwork.dao.*;
import com.jbossatwork.dto.AccountingDTO;
import com.jbossatwork.dto.CarDTO;
import com.jbossatwork.dto.CarDTOArray;

/**
 * @ejb.bean
 *    name="InventoryFacade"
 *    display-name="InventoryFacadeSB"
 *    local-jndi-name="InventoryFacadeLocal"
 *    jndi-name="InventoryFacadeRemote"
 *    type="Stateless"
 *    transaction-type="Container"
 *    view-type="all"
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
 * @ejb.interface
 *    service-endpoint-class="com.jbossatwork.ws.InventoryEndpoint"
 *
 * @wsee.port-component
 *    name="Inventory"
 *    wsdl-port="InventoryEndpointPort"
 *    service-endpoint-interface="com.jbossatwork.ws.InventoryEndpoint"
 *    service-endpoint-bean="com.jbossatwork.ejb.InventoryFacadeBean"
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

    /**
     * @ejb.create-method
     *
     */
    public void ejbCreate() throws CreateException {}
    public void ejbRemove() throws EJBException {}
    public void ejbActivate() throws EJBException {}
    public void ejbPassivate() throws EJBException {}

    // Business methods.

    /**
     * @ejb.interface-method
     *    view-type="all"
     *
     * @ejb.transaction
     *    type="Required"
     *
     */
    public CarDTOArray findAvailableCars() throws EJBException {
        CarDTOArray carDTOArray = new CarDTOArray();
        CarDTO[] cars = (CarDTO[]) listAvailableCars().toArray(new CarDTO[0]);

        carDTOArray.setCars(cars);

        return carDTOArray;
    }

    /**
     * @ejb.interface-method
     *    view-type="both"
     *
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
     *    view-type="both"
     *
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
     *    view-type="both"
     *
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
     *    view-type="both"
     *
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

    /**
     * @ejb.interface-method
     *    view-type="both"
     *
     * @ejb.transaction
     *    type="Required"
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
