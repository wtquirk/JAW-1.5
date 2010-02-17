package com.jbossatwork.dao;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import com.jbossatwork.dto.CarDTO;
import com.jbossatwork.util.*;

public class JDBCCarDAO implements CarDAO 
{
    private List carList; 
    private static final String DATA_SOURCE="java:comp/env/jdbc/JBossAtWorkDS";
    
    public JDBCCarDAO() 
    {}
 
    public List findAll() 
    {
        List carList = new ArrayList();
        DataSource dataSource = null;        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;        try 
        {            dataSource = ServiceLocator.getDataSource(DATA_SOURCE);            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from CAR");
            
            while(rs.next())
            {
                CarDTO car = new CarDTO();
                car.setMake(rs.getString("MAKE"));
                car.setModel(rs.getString("MODEL"));
                car.setModelYear(rs.getString("MODEL_YEAR"));
                carList.add(car);                
            }
                    } 
        catch (Exception e) 
        {            System.out.println(e);        
        } 
        finally
        {
            try
            {
                if(rs != null){rs.close();}
                if(stmt != null){stmt.close();}
                if(conn != null){conn.close();}
            }
            catch(Exception e)
            {
                System.out.println(e);        
            }        
        }
                   
        return carList;
    }

 }