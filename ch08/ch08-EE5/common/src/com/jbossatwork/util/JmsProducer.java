package com.jbossatwork.util;

import java.io.*;

import javax.jms.*;

import javax.naming.*;

/**
 * <code>JmsProducer</code> encapsulates sending a JMS Message.
 *
 */

public class JmsProducer {

    /**
     * Making the default (no arg) constructor private
     * ensures that this class cannnot be instantiated.
     */
    private JmsProducer() {}

    public static void sendMessage(ConnectionFactory connectionFactory, Queue queue, Serializable payload) throws JmsProducerException {
        try {

            Connection connection = null;
            Session session = null;
        	MessageProducer messageProducer = null;
        	ObjectMessage message = null;

        	connection = connectionFactory.createConnection();
        	session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        	messageProducer = session.createProducer(queue);

            message = session.createObjectMessage(payload);
            messageProducer.send(message);

        	messageProducer.close();
        	session.close();
        	connection.close();

        } catch (JMSException je) {
            throw new JmsProducerException(je);
        } catch (Exception e) {
            throw new JmsProducerException(e);
        } 

    }

}