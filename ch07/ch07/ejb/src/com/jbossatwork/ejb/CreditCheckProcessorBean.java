package com.jbossatwork.ejb;

import javax.ejb.*;
import javax.jms.*;

import com.jbossatwork.dto.*;
import com.jbossatwork.util.*;


/**
 * @ejb.bean name="CreditCheckProcessor"
 *     display-name="CreditCheckProcessorMDB"
 *     acknowledge-mode="Auto-acknowledge"
 *     destination-type="javax.jms.Queue"
 *     subscription-durability="NonDurable"
 *     transaction-type="Container"
 *
 * @jboss.destination-jndi-name
 *     name="queue/CreditCheckQueue"
 *
 */
public class CreditCheckProcessorBean implements MessageDrivenBean, MessageListener {

	private MessageDrivenContext ctx = null;

    public CreditCheckProcessorBean() {}


    public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
    	this.ctx = ctx;
    }

    /**
     * Required creation method for message-driven beans.
     *
     * @ejb.create-method
     */
    public void ejbCreate() {
        // no specific action required for message-driven beans
    }

     /** Required removal method for message-driven beans. */
	public void ejbRemove() {
    	ctx = null;
    }

    /**
     * Implements the business logic for the MDB.
     *
     * @param message The JMS message to be processed.
     */
	public void onMessage(Message message) {
    	System.out.println("CreditCheckProcessorBean.onMessage(): Received message.");

        try {
        	if (message instanceof ObjectMessage) {
            	ObjectMessage objMessage = (ObjectMessage) message;
                Object obj = objMessage.getObject();

				if (obj instanceof CreditCheckRequestDTO) {
					String result = null;
                    CreditCheckRequestDTO creditCheckReq = (CreditCheckRequestDTO) obj;

					System.out.println("Credit Check:");
					System.out.println("Name = [" + creditCheckReq.getName() + "]");
					System.out.println("SSN = [" + creditCheckReq.getSsn() + "]");
					System.out.println("Email = [" + creditCheckReq.getEmail() + "]");

					System.out.println("Verifying Credit ...");
					result = CreditVerificationService.verifyCredit(creditCheckReq);
					System.out.println("Credit Check Result = [" + result + "]");
             	} else {
                	System.err.println("Expecting CreditCheckRequestDTO in Message");
                }
        	} else {
            	System.err.println("Expecting Object Message");
        	}
        } catch (Throwable t) {
        	t.printStackTrace();
    	}
	}

}