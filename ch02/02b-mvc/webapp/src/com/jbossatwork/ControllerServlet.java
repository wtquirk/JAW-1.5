package com.jbossatwork;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class ControllerServlet extends HttpServlet
{
    private static final String ACTION_KEY = "action";
    private static final String VIEW_CAR_LIST_ACTION = "viewCarList";
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
            List carList = new ArrayList();
            carList.add(new CarBean("Toyota", "Camry", "2005"));
            carList.add(new CarBean("Toyota", "Corolla", "1999"));
            carList.add(new CarBean("Ford", "Explorer", "2005"));
            request.setAttribute("carList", carList);

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
