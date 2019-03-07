package com.capgemini.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;

import javax.persistence.EntityManager;
import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.exception.InsufficienBalanceException;
import com.capgemini.exception.MobileIsNullException;
import com.capgemini.exception.MobileNoAlreadyExistsException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.exception.NameIsNullException;
import com.capgemini.util.Util;

public class Data {
	
	Connection con;
	Statement stmt;
	EntityManager entitymanager ;
	
    public Data() {
		super();
		entitymanager = Util.getConnection();
	}

	public void newAccount(String name, int mobile, BigDecimal balance) throws MobileNoAlreadyExistsException, MobileIsNullException, NameIsNullException
	{
		if(mobile == 0)
			throw new MobileIsNullException();
		if(name == null)
			throw new NameIsNullException();
		
		Customer customer = find(mobile);
		System.out.println("found it");
		if(customer != null)
		{
			throw new MobileNoAlreadyExistsException();
		}
		System.out.println("Is null");
		
	    Wallet wallet = new Wallet(balance);
	    Customer customer1 = new Customer(name, mobile, wallet);
	    entitymanager.getTransaction().begin();
	    entitymanager.persist(customer1);
	    
	    entitymanager.getTransaction().commit();
	    
	}
	
	public void deposit(int mobile, BigDecimal balance) throws MobileNotFoundException
	{
        Customer customer = find(mobile);
		
		if(customer == null)
			throw new MobileNotFoundException();
	    Wallet wallet = customer.getWallet();
	    wallet.setBalance(wallet.getBalance().add(balance));
	    customer.setWallet(wallet);
	    entitymanager.getTransaction().begin();
	    entitymanager.persist(customer);
	    
	    
	    entitymanager.getTransaction().commit();
	}
	
	public void withdraw(int mobile, BigDecimal balance) throws MobileNotFoundException, InsufficienBalanceException
	{
        Customer customer = find(mobile);
		
		if(customer == null)
			throw new MobileNotFoundException();
		
		if(customer.getWallet().getBalance().subtract(balance).compareTo(new BigDecimal("0.00")) == 0)
			throw new InsufficienBalanceException();

		
	    Wallet wallet = customer.getWallet();
	    wallet.setBalance(wallet.getBalance().subtract(balance));
	    customer.setWallet(wallet);
	    entitymanager.getTransaction().begin();
	    entitymanager.persist(customer);
	    entitymanager.getTransaction().commit();
	}
	
	public void transfer(int mobile1, int mobile2, BigDecimal balance) throws MobileNotFoundException, InsufficienBalanceException
	{
        Customer customer1 = find(mobile1);
		
		if(customer1 == null)
			throw new MobileNotFoundException();
		
        Customer customer2 = find(mobile2);
		
		if(customer2 == null)
			throw new MobileNotFoundException();
		
		if(customer1.getWallet().getBalance().subtract(balance).compareTo(new BigDecimal("0.00")) == 0)
			throw new InsufficienBalanceException();

	
	 
	    Wallet wallet1 = customer1.getWallet();
	    wallet1.setBalance(wallet1.getBalance().subtract(balance));
	    customer1.setWallet(wallet1);
	    
	    Wallet wallet2 = customer2.getWallet();
	    wallet2.setBalance(wallet2.getBalance().add(balance));
	    customer2.setWallet(wallet2);
	    entitymanager.getTransaction().begin();
	    entitymanager.persist(customer1);
	    entitymanager.persist(customer2);
	    
	    
	    entitymanager.getTransaction().commit();
	      
	  
	}

	public Customer find(int mobile){
		entitymanager.getTransaction().begin();
		Customer customer = entitymanager.find(Customer.class, mobile);
		
		entitymanager.getTransaction().commit();
		
		return customer;
	}
	

}
