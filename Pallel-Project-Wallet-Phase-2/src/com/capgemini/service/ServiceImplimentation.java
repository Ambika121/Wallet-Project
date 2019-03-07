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

public class ServiceImplimentation implements IService {
	
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.IService#createAccount(com.capgemini.db.Data, java.sql.Statement, java.lang.String, int, java.math.BigDecimal)
	 */
	@Override
	public void createAccount(Data data, Statement stmt, String name, int mobile, BigDecimal balance) throws MobileNoAlreadyExistsException, SQLException, MobileIsNullException, NameIsNullException {
		// TODO Auto-generated method stub
		data.newAccount(stmt, name, mobile, balance);
	}

	
	/* (non-Javadoc)
	 * @see com.capgemini.service.IService#deposit(com.capgemini.db.Data, java.sql.Statement, int, java.math.BigDecimal)
	 */
	@Override
	public void deposit(Data data, Statement stmt, int mobile, BigDecimal balance) throws MobileNotFoundException, SQLException {
		// TODO Auto-generated method stub
		data.deposit(stmt, mobile, balance);
	}

	
	/* (non-Javadoc)
	 * @see com.capgemini.service.IService#Withdraw(com.capgemini.db.Data, java.sql.Statement, int, java.math.BigDecimal)
	 */
	@Override
	public void Withdraw(Data data, Statement stmt, int mobile, BigDecimal balance)
			throws MobileNotFoundException, InsufficienBalanceException, SQLException {
		// TODO Auto-generated method stub
		data.withdraw(stmt, mobile, balance);
	}

	
	/* (non-Javadoc)
	 * @see com.capgemini.service.IService#Transfer(com.capgemini.db.Data, java.sql.Statement, int, int, java.math.BigDecimal)
	 */
	@Override
	public void Transfer(Data data, Statement stmt, int mobile1, int mobile2, BigDecimal balance)
			throws MobileNotFoundException, InsufficienBalanceException, SQLException {
		// TODO Auto-generated method stub
		data.withdraw(stmt, mobile1, balance);
		data.deposit(stmt, mobile2, balance);
		
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.IService#Exit(com.capgemini.db.Data)
	 */
	@Override
	public void Exit(Data data) throws SQLException
	{
		data.closeConnection();
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.IService#find(com.capgemini.db.Data, java.sql.Statement, int)
	 */
	@Override
	public ResultSet find(Data data, Statement stmt, int mobile) throws SQLException
	{
		ResultSet rs = data.find(stmt, mobile);
		return rs;
	}

}
