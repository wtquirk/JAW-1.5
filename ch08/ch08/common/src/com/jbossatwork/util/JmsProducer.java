package com.jbossatwork.util;

import java.io.*;

import javax.jms.*;

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

    public static void sendMessage(Serializable payload, String connectionFactoryJndiName, String destinationJndiName) throws JmsProducerException {
        try {
            ConnectionFactory connectionFactory = null;
            Connection connection = null;
            Session session = null;
        	Destination destination = null;
        	MessageProducer messageProducer = null;
        	ObjectMessage message = null;

        	connectionFactory = ServiceLocator.getJmsConnectionFactory(
				                                     connectionFactoryJndiName);

        	connection = connectionFactory.createConnection();
        	session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        	destination = ServiceLocator.getJmsDestination(destinationJndiName);
        	messageProducer = session.createProducer(destination);

            message = session.createObjectMessage(payload);
            messageProducer.send(message);

        	messageProducer.close();
        	session.close();
        	connection.close();
        } catch (JMSException je) {
            throw new JmsProducerException(je);
        } catch (ServiceLocatorException sle) {
            throw new JmsProducerException(sle);
        }
    }

}