package com.jbossatwork.ejb;

import javax.ejb.*;
import javax.jms.*;

import com.jbossatwork.dto.*;
import com.jbossatwork.util.*;

import org.apache.commons.logging.*;


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
 * @ejb.resource-ref
 *     res-ref-name="mail/JawJavaMailSession"
 *     res-type="javax.mail.Session"
 *     res-auth="Container"
 *
 * @jboss.resource-ref
 *     res-ref-name="mail/JawJavaMailSession"
 *     jndi-name="java:/Mail"
 *
 */
public class CreditCheckProcessorBean implements MessageDrivenBean, MessageListener {
	private Log log = LogFactory.getLog(CreditCheckProcessorBean.class);

	private static final String JAVAMAIL_SESSION = "java:comp/env/mail/JawJavaMailSession";
	private static final String JAW_MOTORS_EMAIL_ADDRESS = "credit.check@jbossatwork.com";
	private static final String CREDIT_VERIFICATION_RESULT = "Credit Check Result";

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
    	log.info("CreditCheckProcessorBean.onMessage(): Received message.");

        try {
        	if (message instanceof ObjectMessage) {
            	ObjectMessage objMessage = (ObjectMessage) message;
                Object obj = objMessage.getObject();

				if (obj instanceof CreditCheckRequestDTO) {
					String result = null;
                    CreditCheckRequestDTO creditCheckReq = (CreditCheckRequestDTO) obj;

					log.info("Credit Check:");
					log.info("Name = [" + creditCheckReq.getName() + "]");
					log.info("SSN = [" + creditCheckReq.getSsn() + "]");
					log.info("Email = [" + creditCheckReq.getEmail() + "]");

					log.info("Verifying Credit ...");
					result = CreditVerificationService.verifyCredit(creditCheckReq);
					log.info("Credit Check Result = [" + result + "]");
					sendNotificationEmail(creditCheckReq, result);
             	} else {
                	log.error("Expecting CreditCheckRequestDTO in Message");
                }
        	} else {
            	log.error("Expecting Object Message");
        	}
        } catch (Throwable t) {
        	t.printStackTrace();
    	}
	}

	private void sendNotificationEmail(CreditCheckRequestDTO creditCheckReq, String result) {
		javax.mail.Session javaMailSession = null;

		try {
			javaMailSession = ServiceLocator.getJavaMailSession(JAVAMAIL_SESSION);
			TextEmail email = new TextEmail(javaMailSession);

			email.setBody(result);
			email.setSender(JAW_MOTORS_EMAIL_ADDRESS);
			email.setSubject(CREDIT_VERIFICATION_RESULT);
			email.addRecipient(creditCheckReq.getEmail());
			log.info("Sending Email to [" + creditCheckReq.getEmail() +
					           "] ...");

			email.send();

        } catch (ServiceLocatorException sle) {
            log.error("Error Looking up JavaMail Session: " + sle);
            sle.printStackTrace();
        }
	}
}