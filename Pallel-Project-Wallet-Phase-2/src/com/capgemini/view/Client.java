package com.capgemini.view;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.db.Data;
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
			
		Data data = new Data();
        Statement stmt = data.createConnection();
		ServiceImplimentation service = new ServiceImplimentation();
		
		service.createAccount(data, stmt, "ABC", 98765, new BigDecimal(500));
		System.out.println("First Account created");
		service.createAccount(data, stmt, "DEF", 65478, new BigDecimal(800));
		System.out.println("Second Account created");
		service.Withdraw(data, stmt, 98765, new BigDecimal(400));
		System.out.println("Withdrawl done");
		service.deposit(data, stmt, 65478, new BigDecimal(600));
		System.out.println("Deposit Done");
		service.Transfer(data, stmt, 65478, 98765, new BigDecimal(1000));
		System.out.println("Transfer done");
		
		service.find(data, stmt, 98765);
		service.find(data, stmt, 65478);
		service.Exit(data);
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		

	}

}
