package com.jbossatwork.dto;

import java.io.Serializable;

public class CarDTOArray implements Serializable {

    private CarDTO[] cars;

    public CarDTOArray() {}

    public CarDTO[] getCars() {
        return cars;
    }

    public void setCars(CarDTO[] cars) {
        this.cars = cars;
    }

}
