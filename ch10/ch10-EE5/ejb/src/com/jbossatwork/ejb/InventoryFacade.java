package com.jbossatwork.ejb;

import java.util.*;

import javax.ejb.*;
import javax.jws.*; 
import javax.persistence.*;
import javax.annotation.security.*;
import org.jboss.wsf.spi.annotation.WebContext;

import com.jbossatwork.dao.*;
import com.jbossatwork.dto.CarDTO;
import com.jbossatwork.dto.AccountingDTO;

@Stateless
@DeclareRoles({"guest","Manager"})
@WebService
@WebContext( contextRoot = "/jbossatwork-ws" , urlPattern="/InventoryService" )
public class InventoryFacade implements InventoryFacadeLocal, InventoryFacadeRemote {
@PersistenceContext(unitName="jawEntityManager")
EntityManager em;

    @WebMethod()
    public CarDTO[] findAvailableCars() throws EJBException {
    	CarDTO[] cars = (CarDTO[]) listAvailableCars().toArray(new CarDTO[0]);

        return cars;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    @RolesAllowed({"guest","Manager"})
    public List listAvailableCars() throws EJBException {
    	CarDAO carDAO = new HibernateCarDAO(em);

        return carDAO.filterByStatus(CarDTO.STATUS_AVAILABLE);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    @RolesAllowed({"guest","Manager"})
    public CarDTO findCar(int id) throws EJBException {
    	CarDAO carDAO = new HibernateCarDAO(em);

    	return carDAO.findById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    @RolesAllowed("Manager")
    public void deleteCars(String[] ids) throws EJBException {
		CarDAO carDAO = new HibernateCarDAO(em);

		if (ids != null) {
			carDAO.delete(ids);
		}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    @RolesAllowed("Manager")
    public void saveCar(CarDTO car) throws EJBException {
		CarDAO carDAO = new HibernateCarDAO(em);

		if(car.getId() == -1) {
			carDAO.create(car);
		} else {
			carDAO.update(car);
		}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    @RolesAllowed({"guest","Manager"})
    public void buyCar(int carId, double price) throws EJBException {
		CarDAO carDAO = new HibernateCarDAO(em);
		CarDTO car;
		AccountingDAO accountingDAO = new HibernateAccountingDAO(em);
		AccountingDTO accountingData;

		car = carDAO.findById(carId);
		car.setStatus(CarDTO.STATUS_SOLD);
		carDAO.update(car);

		accountingData = new AccountingDTO(carId, price);
		accountingDAO.create(accountingData);
    }

}
