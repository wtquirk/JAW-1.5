package com.jbossatwork.util;

import java.net.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jConfigurator {

    /**
     * Making the default (no arg) constructor private
     * ensures that this class cannnot be instantiated.
     */
    private Log4jConfigurator() {}

    /**
     * Configures Log4J for an application using the specified Log4J XML configuration file.
     *
     * @param log4jXmlFileName The specified Log4J XML configuration file.
     */
    public static void setup(String log4jXmlFileName) {
		URL url = ResourceLoader.getAsUrl(log4jXmlFileName);

        if (url != null) {

            // An URL (from the CLASSPATH)  that points to the Log4J XML configuration
            // file was provided, so use Log4J’s DOMConfigurator with the URL to
            // initialize Log4J with the contents of the Log4J XML configuration file.

            DOMConfigurator.configure(url);
        } else {

            // An URL that points to the Log4J XML configuration file wasn’t provided,
            // so use Log4J’s BasicConfigurator to initialize Log4J.

            BasicConfigurator.configure();
        }
    }
}