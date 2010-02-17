package com.jbossatwork.util;

import com.jbossatwork.dto.*;

public class CreditVerificationService {

    private static final String CREDIT_CHECK_PASS = "Pass Credit Check";
    private static final String CREDIT_CHECK_FAIL = "Fail Credit Check";

    /**
     * Making the default (no arg) constructor private
     * ensures that this class cannnot be instantiated.
     */
    private CreditVerificationService() {}

    /**
     * Emulates using an external credit verification service.
     *
     */
    public static String verifyCredit(CreditCheckRequestDTO creditCheckReq) {

        // Waste some time to simulate a long-running processs.
        for (int i = 0; i < Integer.MAX_VALUE; ++i) {
            ;
        }

        return genPassFail();
    }

    /**
     * Randomly generates a Pass/Fail status.
     *
     */
    private static String genPassFail() {
        String passFail;
        int result = (int) Math.round(Math.random());

        if (result == 0) {
            passFail = CREDIT_CHECK_FAIL;
        } else {
            passFail = CREDIT_CHECK_PASS;
        }

        return passFail;
    }

    public static void main(String[] args) {
            System.out.println(CreditVerificationService.verifyCredit(new CreditCheckRequestDTO()));
    }

}