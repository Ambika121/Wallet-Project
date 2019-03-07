package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.exception.InsufficienBalanceException;
import com.capgemini.exception.MobileNoAlreadyExistsException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.repo.RepositoryImplimentation;

public class ServiceImplimentation implements Service {
	
	
	RepositoryImplimentation repository = new RepositoryImplimentation();
	
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.Service#createAccount(java.lang.String, int, java.math.BigDecimal)
	 */
	@Override
	public Customer createAccount(String name, int mobile, BigDecimal balance) throws MobileNoAlreadyExistsException
	{
		Customer customer = new Customer(name, mobile, new Wallet(balance));
		
		if(repository.find(mobile) != null)
		{
			throw new MobileNoAlreadyExistsException();
		}
		
		repository.save(customer);
		return customer;
	}
	
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.Service#deposit(int, java.math.BigDecimal)
	 */
	@Override
	public Customer deposit(int mobile, BigDecimal balance) throws MobileNotFoundException
	{
		if(mobile == 0)
		{
			throw new MobileNotFoundException();
		}
		Customer customer = repository.find(mobile);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(wallet.getBalance().add(balance));
		customer.setWallet(wallet);
		return customer;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.Service#Withdraw(int, java.math.BigDecimal)
	 */
	@Override
	public Customer Withdraw(int mobile, BigDecimal balance) throws MobileNotFoundException, InsufficienBalanceException
	{
		
		if(mobile == 0)
		{
			throw new MobileNotFoundException();
		}
		Customer customer = repository.find(mobile);
		Wallet wallet = customer.getWallet();
		
		if(wallet.getBalance().subtract(balance).compareTo(new BigDecimal("0.00")) == 0)
		{
			throw new InsufficienBalanceException();
		}
		wallet.setBalance(wallet.getBalance().subtract(balance));
		customer.setWallet(wallet);
		return customer;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.Service#Transfer(int, int, java.math.BigDecimal)
	 */
	@Override
	public void Transfer(int mobile1, int mobile2, BigDecimal balance) throws MobileNotFoundException, InsufficienBalanceException
	{
		
		if(mobile1 == 0 || mobile2 == 0)
		{
			throw new MobileNotFoundException();
		}
		Customer customer1 = repository.find(mobile1);
		Wallet wallet1 = customer1.getWallet();
		Customer customer2 = repository.find(mobile2);
		Wallet wallet2 = customer1.getWallet();
		
		if(wallet1.getBalance().subtract(balance).compareTo(new BigDecimal("0.00")) == 0)
		{
			throw new InsufficienBalanceException();
		}
		wallet1.setBalance(wallet1.getBalance().subtract(balance));
		wallet2.setBalance(wallet2.getBalance().subtract(balance));
		customer1.setWallet(wallet1);
		customer2.setWallet(wallet2);
		
	}

}
