package com.capgemini.text;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.exception.*;

import com.capgemini.service.ServiceImplimentation;

public class WalletServiceTest {


	ServiceImplimentation service;
	@Before
	public void setUp() throws Exception {
		new ServiceImplimentation();
	}

	@Test(expected=com.capgemini.exception.MobileNoAlreadyExistsException.class)
	public void whenTheMobileNumberEnteredforCreatingTheAccountAlreadyExist() throws MobileNoAlreadyExistsException, MobileIsNullException, NameIsNullException 
	{

		service = new ServiceImplimentation();
		BigDecimal amount=new BigDecimal(500);
		service.createAccount("ABC", 96900, amount);
		service.createAccount("DEF", 96900, amount);
	}
	
	@Test(expected=com.capgemini.exception.InsufficienBalanceException.class)
	public void whenAtTheTimeOfFundTransferTheSourceWalletDoesNotHaveEnoughAmountToTransfer() throws MobileNotFoundException, InsufficienBalanceException, MobileNoAlreadyExistsException, MobileIsNullException, NameIsNullException 
	{

		service = new ServiceImplimentation();
		BigDecimal amount=new BigDecimal(500);
		BigDecimal am= new BigDecimal(600); 
		service.createAccount("ABC", 45675, amount);
		service.createAccount("DEF", 94558, amount);
		service.Transfer(45675, 94558, am);
	}
	
	@Test(expected=com.capgemini.exception.InsufficienBalanceException.class)
	public void atTheTimeOfWithdrawIfTheBalanceIsNotSufficient() throws MobileNotFoundException, InsufficienBalanceException, MobileNoAlreadyExistsException, MobileIsNullException, NameIsNullException 
	{

		service=new ServiceImplimentation();
		BigDecimal amount=new BigDecimal(500);
		BigDecimal am= new BigDecimal(600); 
		service.createAccount("ABC", 78945, amount);
		service.Withdraw(78945, am);
	}
	
	@Test(expected=com.capgemini.exception.MobileNotFoundException.class)
	public void atTheTimeOfDepositIfMobileNumberNotFound() throws MobileNotFoundException, MobileNoAlreadyExistsException, MobileIsNullException, NameIsNullException 
	{

		service = new ServiceImplimentation();
		BigDecimal amount=new BigDecimal(500);
		BigDecimal am= new BigDecimal(600); 
		service.createAccount("ABC", 85695, amount);
		service.deposit(74589, am);
	}
	
	@Test
	public void whenEveryInformationProvidedIsCorrect() throws MobileNotFoundException, InsufficienBalanceException, MobileNoAlreadyExistsException, MobileIsNullException, NameIsNullException 
	{

		service = new ServiceImplimentation();
		BigDecimal amount=new BigDecimal(500);
		service.createAccount("ABC", 85964, amount);
		service.deposit(85964, amount);
		service.Withdraw(85964, amount);
		
	}
}