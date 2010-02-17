package com.jbossatwork.ejb;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import com.jbossatwork.dao.*;
import com.jbossatwork.dto.CarDTO;
import com.jbossatwork.dto.AccountingDTO;

@Stateless
public class InventoryFacade implements InventoryFacadeLocal, InventoryFacadeRemote {
@PersistenceContext(unitName="jawEntityManager")
EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    public List listAvailableCars() throws EJBException {
    	CarDAO carDAO = new HibernateCarDAO(em);

        return carDAO.filterByStatus(CarDTO.STATUS_AVAILABLE);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    public CarDTO findCar(int id) throws EJBException {
    	CarDAO carDAO = new HibernateCarDAO(em);

    	return carDAO.findById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    public void deleteCars(String[] ids) throws EJBException {
		CarDAO carDAO = new HibernateCarDAO(em);

		if (ids != null) {
			carDAO.delete(ids);
		}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    public void saveCar(CarDTO car) throws EJBException {
		CarDAO carDAO = new HibernateCarDAO(em);

		if(car.getId() == -1) {
			carDAO.create(car);
		} else {
			carDAO.update(car);
		}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
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
