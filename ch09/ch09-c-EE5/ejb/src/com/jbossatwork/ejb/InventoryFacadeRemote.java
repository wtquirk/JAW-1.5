package com.jbossatwork.ejb;

import java.util.*;

import javax.ejb.Remote;

import com.jbossatwork.dto.CarDTO;

@Remote
public interface InventoryFacadeRemote {

    public CarDTO findCar(int id) throws javax.ejb.EJBException; 
    public List listAvailableCars() throws javax.ejb.EJBException;
    public void deleteCars(String[] ids) throws javax.ejb.EJBException;
    public void saveCar(CarDTO car) throws javax.ejb.EJBException; 
    public void buyCar(int carId, double price) throws javax.ejb.EJBException;

}
