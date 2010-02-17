package com.jbossatwork;

import com.jbossatwork.dto.*;
import com.jbossatwork.dao.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @web.servlet
 *    name="Controller"
 *
 * @web.servlet-mapping
 *    url-pattern="/controller/*"
 *
 * @web.resource-ref *    name="jdbc/JBossAtWorkDS" *    type="javax.sql.DataSource" *    auth="Container"
 *
 * @jboss.resource-ref *    res-ref-name="jdbc/JBossAtWorkDS" *    jndi-name="java:/JBossAtWorkDS"
 *
 * @web.resource-ref *    name="hibernate/SessionFactory" *    type="net.sf.hibernate.SessionFactory" *    auth="Container"
 *
 * @jboss.resource-ref *    res-ref-name="hibernate/SessionFactory" *    jndi-name="java:/hibernate/SessionFactory"
 */

public class ControllerServlet extends HttpServlet
{
    private static final String ACTION_KEY = "action";
    private static final String VIEW_CAR_LIST_ACTION = "viewCarList";
    private static final String ADD_CAR_ACTION = "addCar";
    private static final String SAVE_CAR_ACTION = "saveCar";
    private static final String EDIT_CAR_ACTION = "editCar";
    private static final String DELETE_CAR_ACTION = "deleteCar";
    private static final String ERROR_KEY = "errorMessage";
    private static final String ERROR_PAGE="/error.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_KEY);
        String destinationPage = ERROR_PAGE;

        // perform action
        if(VIEW_CAR_LIST_ACTION.equals(actionName))
        {
            CarDAO carDAO = new HibernateCarDAO();
            request.setAttribute("carList", carDAO.findAll());
            destinationPage = "/carList.jsp";
        }
        else if(ADD_CAR_ACTION.equals(actionName))
        {
            request.setAttribute("car", new CarDTO());
            destinationPage = "/carForm.jsp";
        }  
        else if(EDIT_CAR_ACTION.equals(actionName))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            CarDAO carDAO = new HibernateCarDAO();
            request.setAttribute("car", carDAO.findById(id));
            destinationPage = "/carForm.jsp";
        }        
        else if(SAVE_CAR_ACTION.equals(actionName))
        {
            //build the car from the request parameters
            CarDTO car = new CarDTO();
            car.setId(Integer.parseInt(request.getParameter("id")));
            car.setMake(request.getParameter("make"));
            car.setModel(request.getParameter("model"));
            car.setModelYear(request.getParameter("modelYear"));
            
            //save the car
            CarDAO carDAO = new HibernateCarDAO();            
            if(car.getId() == -1)
            {
                carDAO.create(car);
            }
            else
            {
                carDAO.update(car);
            }
            
            //prepare the list
            request.setAttribute("carList", carDAO.findAll());
            destinationPage = "/carList.jsp";
        }  
        else if(DELETE_CAR_ACTION.equals(actionName))
        {
            //get list of ids to delete
            String[] ids = request.getParameterValues("id");
            
            //delete the list of ids
            CarDAO carDAO = new HibernateCarDAO();
            if(ids != null)
            {
                carDAO.delete(ids);
            }
                        
            //prepare the list
            request.setAttribute("carList", carDAO.findAll());
            destinationPage = "/carList.jsp";
        }                    
        else
        {
            String errorMessage = "[" + actionName + "] is not a valid action.";
            request.setAttribute(ERROR_KEY, errorMessage);
        }


        // Redirect to destination page.
        RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher(destinationPage);

        dispatcher.forward(request, response);
    }



}
