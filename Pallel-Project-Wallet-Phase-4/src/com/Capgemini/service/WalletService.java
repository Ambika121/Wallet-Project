package com.Capgemini.service;

import java.math.BigDecimal;

import com.Capgemini.beans.Customer;
import com.Capgemini.exception.InsufficientBalanceException;
import com.Capgemini.exception.InvalidInputException;

public interface WalletService {

	Customer createAccount(Customer customer);

	Customer showBalance(String mobileNo);

	Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
			throws InvalidInputException, InsufficientBalanceException;

	Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException;

	Customer withdrawAmount(String mobileNo, BigDecimal amount)
			throws InvalidInputException, InsufficientBalanceException;

}