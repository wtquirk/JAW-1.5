package com.jbossatwork;

import com.jbossatwork.dto.*;
import com.jbossatwork.ejb.*;
import java.io.IOException;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.InitialContext;

public class ControllerServlet extends HttpServlet
{
    @EJB InventoryFacadeLocal inventory;

    private static final String ACTION_KEY = "action";
    private static final String VIEW_CAR_LIST_ACTION = "viewCarList";
    private static final String ADD_CAR_ACTION = "addCar";
    private static final String SAVE_CAR_ACTION = "saveCar";
    private static final String EDIT_CAR_ACTION = "editCar";
    private static final String DELETE_CAR_ACTION = "deleteCar";
    private static final String VIEW_BUY_CAR_FORM_ACTION = "viewBuyCarForm";
    private static final String BUY_CAR_ACTION = "buyCar";
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
		String actionName = "";
		String pathInfo = request.getPathInfo();
            String destinationPage = ERROR_PAGE;
   
        if (inventory == null)
        {
		String errorMessage = "Inventory EJB reference is null.";
            request.setAttribute(ERROR_KEY, errorMessage);
        }
	  else
        {

	  	if (pathInfo != null) {
		 	 actionName = pathInfo.substring(1); // Get the action name from the HTTP Request.
	  	}

       	 // perform action
       	 if(VIEW_CAR_LIST_ACTION.equals(actionName))
       	 {
            	request.setAttribute("carList", inventory.listAvailableCars());
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
            	request.setAttribute("car", inventory.findCar(id));
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
            	inventory.saveCar(car);

            	//prepare the list
            	request.setAttribute("carList", inventory.listAvailableCars());
            	destinationPage = "/carList.jsp";
        	}
        	else if(DELETE_CAR_ACTION.equals(actionName))
        	{
            	//get list of ids to delete
            	String[] ids = request.getParameterValues("id");

            	//delete the list of ids

            	inventory.deleteCars(ids);

            	//prepare the list
           		request.setAttribute("carList", inventory.listAvailableCars());
            	destinationPage = "/carList.jsp";
        	}
        	else if(VIEW_BUY_CAR_FORM_ACTION.equals(actionName))
        	{
			int id = Integer.parseInt(request.getParameter("id"));
            	request.setAttribute("car", inventory.findCar(id));
			destinationPage = "/buyCarForm.jsp";
		}
        	else if(BUY_CAR_ACTION.equals(actionName))
        	{
			int carId = Integer.parseInt(request.getParameter("id"));
			double price;

			// Use $5000.00 as the default price if the user enters bad data.
			try
			{
				price = Double.parseDouble(request.getParameter("price"));
			}
			catch (NumberFormatException nfe)
			{
				price = 5000.00;
			}

			System.out.println("carId = [" + carId + "], price = [" + price + "]");

			//mark the car as sold
			inventory.buyCar(carId, price);

            	//prepare the list
            	request.setAttribute("carList", inventory.listAvailableCars());
            	destinationPage = "/carList.jsp";
		}
        	else
        	{
            	String errorMessage = "[" + actionName + "] is not a valid action.";
            	request.setAttribute(ERROR_KEY, errorMessage);
        	}
        
        }


        // Redirect to destination page.
        RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher(destinationPage);

        dispatcher.forward(request, response);
    }



}