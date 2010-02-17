package com.jbossatwork.util;


public class JmsProducerException extends RuntimeException {


    public JmsProducerException() {}


    /**
     * @param message
     */
    public JmsProducerException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public JmsProducerException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public JmsProducerException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}