package com.capgemini.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.exception.InsufficienBalanceException;
import com.capgemini.exception.MobileIsNullException;
import com.capgemini.exception.MobileNoAlreadyExistsException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.exception.NameIsNullException;

public interface IData {

	Statement createConnection() throws ClassNotFoundException, SQLException;

	void newAccount(Statement stmt, String name, int mobile, BigDecimal balance)
			throws SQLException, MobileNoAlreadyExistsException, MobileIsNullException, NameIsNullException;

	void deposit(Statement stmt, int mobile, BigDecimal balance) throws SQLException, MobileNotFoundException;

	void withdraw(Statement stmt, int mobile, BigDecimal balance)
			throws SQLException, MobileNotFoundException, InsufficienBalanceException;

	void closeConnection() throws SQLException;

	ResultSet find(Statement stmt, int mobile) throws SQLException;

}