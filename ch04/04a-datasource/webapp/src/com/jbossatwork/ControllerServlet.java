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
 */

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
            CarDAO carDAO = new JDBCCarDAO();
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
