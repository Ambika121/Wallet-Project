package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.exception.InsufficienBalanceException;
import com.capgemini.exception.MobileNoAlreadyExistsException;
import com.capgemini.exception.MobileNotFoundException;

public interface Service {

	Customer createAccount(String name, int mobile, BigDecimal balance) throws MobileNoAlreadyExistsException;

	Customer deposit(int mobile, BigDecimal balance) throws MobileNotFoundException;

	Customer Withdraw(int mobile, BigDecimal balance) throws MobileNotFoundException, InsufficienBalanceException;

	void Transfer(int mobile1, int mobile2, BigDecimal balance)
			throws MobileNotFoundException, InsufficienBalanceException;

}