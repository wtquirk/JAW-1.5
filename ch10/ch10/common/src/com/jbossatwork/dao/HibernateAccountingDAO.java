package com.jbossatwork.dao;

import java.util.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import com.jbossatwork.dto.AccountingDTO;
import com.jbossatwork.util.*;

public class HibernateAccountingDAO implements AccountingDAO
{
    private static final String HIBERNATE_SESSION_FACTORY="java:comp/env/hibernate/SessionFactory";

    public HibernateAccountingDAO()
    {}


    public void create(AccountingDTO accountingData)
    {
        Session session = null;

        try
        {
            session = ServiceLocator.getHibernateSession(HIBERNATE_SESSION_FACTORY);
            session.save(accountingData);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

 }