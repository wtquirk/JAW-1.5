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
        DataSource dataSource = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            dataSource = ServiceLocator.getDataSource(DATA_SOURCE);
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from CAR");

            while(rs.next())
            {
                CarDTO car = new CarDTO();
                car.setId(rs.getInt("ID"));
                car.setMake(rs.getString("MAKE"));
                car.setModel(rs.getString("MODEL"));
                car.setModelYear(rs.getString("MODEL_YEAR"));
                carList.add(car);
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
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

    public List filterByStatus(String status)
    {
        List carList = new ArrayList();
        DataSource dataSource = null;
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        try
        {
            dataSource = ServiceLocator.getDataSource(DATA_SOURCE);
            conn = dataSource.getConnection();
            prepStmt = conn.prepareStatement("select * from CAR where STATUS = ?");
            prepStmt.setString(1, status);
            rs = prepStmt.executeQuery();

            while(rs.next())
            {
                CarDTO car = new CarDTO();
                car.setId(rs.getInt("ID"));
                car.setMake(rs.getString("MAKE"));
                car.setModel(rs.getString("MODEL"));
                car.setModelYear(rs.getString("MODEL_YEAR"));
                car.setStatus(rs.getString("STATUS"));
                carList.add(car);
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                if(rs != null){rs.close();}
                if(prepStmt != null){prepStmt.close();}
                if(conn != null){conn.close();}
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

        return carList;
    }

    public CarDTO findById(int id)
    {
        DataSource dataSource = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            dataSource = ServiceLocator.getDataSource(DATA_SOURCE);
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from CAR where id=" + id);

            while(rs.next())
            {
                CarDTO car = new CarDTO();
                car.setId(rs.getInt("ID"));
                car.setMake(rs.getString("MAKE"));
                car.setModel(rs.getString("MODEL"));
                car.setModelYear(rs.getString("MODEL_YEAR"));
                return car;
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
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

        return null;
    }





    public void create(CarDTO car)
    {
        DataSource dataSource = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into CAR(MAKE, MODEL, MODEL_YEAR) values(?,?,?)";

        try
        {
            dataSource = ServiceLocator.getDataSource(DATA_SOURCE);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getModelYear());

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

    }


    public void update(CarDTO car)
    {
        DataSource dataSource = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update CAR set MAKE=?, MODEL=?, MODEL_YEAR=? where id=?";

        try
        {
            dataSource = ServiceLocator.getDataSource(DATA_SOURCE);
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getModelYear());
            pstmt.setInt(4, car.getId());

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

    }


    public void delete(String[] ids)
    {
        DataSource dataSource = null;
        Connection conn = null;
        Statement stmt = null;
        String sql = "delete from CAR where id in(";

        try
        {
            dataSource = ServiceLocator.getDataSource(DATA_SOURCE);
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            StringBuffer idList = new StringBuffer();
            for(int i = 0; i < ids.length; i++)
            {
                idList.append(ids[i]);

                if(i < (ids.length - 1))
                {
                    idList.append(",");
                }
            }

            stmt.executeUpdate(sql + idList.toString() + ")");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                if(stmt != null){stmt.close();}
                if(conn != null){conn.close();}
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

    }






 }