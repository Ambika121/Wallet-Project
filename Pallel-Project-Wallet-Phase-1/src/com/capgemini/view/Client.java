package com.capgemini.view;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capgemini.beans.Customer;
import com.capgemini.exception.InsufficienBalanceException;
import com.capgemini.exception.MobileNoAlreadyExistsException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.service.ServiceImplimentation;

public class Client {

	public static void main(String[] args) throws MobileNoAlreadyExistsException, MobileNotFoundException, InsufficienBalanceException, SQLException {
		// TODO Auto-generated method stub
		
		//create table Wallet(name varchar2(20), accNumber number(10), amount number(10));
		try
		{
			
		Customer customer;
		ServiceImplimentation service = new ServiceImplimentation();
		
		customer = service.createAccount("ABC", 98765, new BigDecimal(500));
		System.out.println("First Account created\n" + customer);
		customer = service.createAccount("DEF", 65478, new BigDecimal(800));
		System.out.println("\nSecond Account created\n" + customer);
		customer = service.Withdraw(98765, new BigDecimal(400));
		System.out.println("\nWithdrawl done\n" + customer);
		customer = service.deposit(65478, new BigDecimal(600));
		System.out.println("\nDeposit Done\n" + customer);
		service.Transfer(65478, 98765, new BigDecimal(1000));
		System.out.println("\nTransfer done");
		
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		

	}

}
