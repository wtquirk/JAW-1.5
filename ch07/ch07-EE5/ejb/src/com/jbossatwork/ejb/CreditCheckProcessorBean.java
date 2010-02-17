package com.jbossatwork.ejb;

import javax.ejb.*;
import javax.jms.*;

import javax.annotation.Resource;

import com.jbossatwork.dto.*;
import com.jbossatwork.util.*;

@MessageDriven(name="CreditCheckProcessorMDB",
activationConfig=
{ @ActivationConfigProperty(
    propertyName="destinationType",
    propertyValue="javax.jms.Queue"),
  @ActivationConfigProperty(
    propertyName="destination", 
    propertyValue="queue/CreditCheckQueue")
})
public class CreditCheckProcessorBean implements MessageListener {

    @Resource
    private MessageDrivenContext ctx ;

    public CreditCheckProcessorBean() {}

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