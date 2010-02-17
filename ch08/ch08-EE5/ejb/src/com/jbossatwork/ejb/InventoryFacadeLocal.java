package com.jbossatwork.ejb;

import java.util.*;

import javax.ejb.Local;

import com.jbossatwork.dto.CarDTO;

@Local
public interface InventoryFacadeLocal {

    public CarDTO findCar(int id) throws javax.ejb.EJBException; 
    public List listAvailableCars() throws javax.ejb.EJBException;
    public void deleteCars(String[] ids) throws javax.ejb.EJBException;
    public void saveCar(CarDTO car) throws javax.ejb.EJBException; 
    public void buyCar(int carId, double price) throws javax.ejb.EJBException;

}
