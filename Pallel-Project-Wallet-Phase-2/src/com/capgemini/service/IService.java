package com.capgemini.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.db.Data;
import com.capgemini.exception.InsufficienBalanceException;
import com.capgemini.exception.MobileIsNullException;
import com.capgemini.exception.MobileNoAlreadyExistsException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.exception.NameIsNullException;

public interface IService {

	void createAccount(Data data, Statement stmt, String name, int mobile, BigDecimal balance)
			throws MobileNoAlreadyExistsException, SQLException, MobileIsNullException, NameIsNullException;

	void deposit(Data data, Statement stmt, int mobile, BigDecimal balance)
			throws MobileNotFoundException, SQLException;

	void Withdraw(Data data, Statement stmt, int mobile, BigDecimal balance)
			throws MobileNotFoundException, InsufficienBalanceException, SQLException;

	void Transfer(Data data, Statement stmt, int mobile1, int mobile2, BigDecimal balance)
			throws MobileNotFoundException, InsufficienBalanceException, SQLException;

	void Exit(Data data) throws SQLException;

	ResultSet find(Data data, Statement stmt, int mobile) throws SQLException;

}