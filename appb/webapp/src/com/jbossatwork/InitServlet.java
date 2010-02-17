package com.jbossatwork;

import javax.servlet.*;

import java.io.*;

import org.apache.commons.logging.*;

import com.jbossatwork.util.*;

/**
 * InitServlet sets up Log4J for the application.
 *
 * @web.servlet
 *     name="InitServlet"
 *     load-on-startup="1"
 *
 */
public class InitServlet extends GenericServlet {
	private Log log = LogFactory.getLog(InitServlet.class);

    private ServletContext servletContext;

    public void init() {
		servletContext = getServletContext();
		SystemPropertiesUtil.addToSystemPropertiesFromPropsFile("log4j.extra.properties");
		Log4jConfigurator.setup("jbossatwork-log4j.xml");
		log.info("Testing Logging Setup ...");
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    }
}