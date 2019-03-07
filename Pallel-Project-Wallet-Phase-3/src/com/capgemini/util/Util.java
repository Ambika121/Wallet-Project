package com.capgemini.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Util {
	
	static EntityManagerFactory emfactory;
	static EntityManager entitymanager;
	
	public static EntityManager getConnection()
	{
		emfactory = Persistence.createEntityManagerFactory("Customer");
	    entitymanager = emfactory.createEntityManager();
	    return entitymanager;
	}
	
	public static void close()
	{
		entitymanager.close();
	    emfactory.close();
	}

}
