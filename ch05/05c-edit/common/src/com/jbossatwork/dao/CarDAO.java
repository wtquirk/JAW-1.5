package com.jbossatwork.dao;

import java.util.*;
import com.jbossatwork.dto.*;

public interface CarDAO 
{
    public List findAll(); 
    public CarDTO findById(int id);
    public void create(CarDTO car);
    public void update(CarDTO car);
}