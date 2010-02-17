package com.jbossatwork.dao;

import java.util.*;
import javax.persistence.*;
import com.jbossatwork.dto.AccountingDTO;

public class HibernateAccountingDAO implements AccountingDAO
{
    private EntityManager manager;

    public HibernateAccountingDAO(EntityManager manager)
    {
	  this.manager = manager;
    }

    public void create(AccountingDTO accountingData)
    {

        try
        {
            manager.persist(accountingData);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

 }