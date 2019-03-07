package com.capgemini.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.exception.InsufficienBalanceException;
import com.capgemini.exception.MobileIsNullException;
import com.capgemini.exception.MobileNoAlreadyExistsException;
import com.capgemini.exception.MobileNotFoundException;
import com.capgemini.exception.NameIsNullException;

public class Data implements IData {
	
	Connection con;
	Statement stmt;

	/* (non-Javadoc)
	 * @see com.capgemini.db.IData#createConnection()
	 */
	@Override
	public Statement createConnection() throws ClassNotFoundException, SQLException {
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");	
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Capgemini123");	
	    stmt=con.createStatement();
	    return stmt;
		
	}

	/* (non-Javadoc)
	 * @see com.capgemini.db.IData#newAccount(java.sql.Statement, java.lang.String, int, java.math.BigDecimal)
	 */
	@Override
	public void newAccount(Statement stmt, String name, int mobile, BigDecimal balance) throws SQLException, MobileNoAlreadyExistsException, MobileIsNullException, NameIsNullException
	{
		if(mobile == 0)
			throw new MobileIsNullException();
		if(name == null)
			throw new NameIsNullException();
		
		ResultSet rs = find(stmt, mobile);
		
		if(rs.next())
			throw new MobileNoAlreadyExistsException();
		else
		    stmt.executeUpdate("Insert into Wallet values('"+name+"', '"+mobile+"','"+balance+"')");
	    
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.db.IData#deposit(java.sql.Statement, int, java.math.BigDecimal)
	 */
	@Override
	public void deposit(Statement stmt, int mobile, BigDecimal balance) throws SQLException, MobileNotFoundException
	{
        ResultSet rs = find(stmt, mobile);
		
		if(rs.next())
			throw new MobileNotFoundException();
		else
		    stmt.executeUpdate("update Wallet set amount = amount + '"+balance+"' where accNumber = '"+mobile+"'");
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.db.IData#withdraw(java.sql.Statement, int, java.math.BigDecimal)
	 */
	@Override
	public void withdraw(Statement stmt, int mobile, BigDecimal balance) throws SQLException, MobileNotFoundException, InsufficienBalanceException
	{
        ResultSet rs = find(stmt, mobile);
		
		if(rs.next())
			throw new MobileNotFoundException();
		
		if(rs.getBigDecimal(3).subtract(balance).compareTo(new BigDecimal("0.00")) == 0)
			throw new InsufficienBalanceException();

		stmt.executeUpdate("update Wallet set amount = amount - '"+balance+"' where accNumber = '"+mobile+"'");
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.db.IData#closeConnection()
	 */
	@Override
	public void closeConnection() throws SQLException {
		con.close();
	}

	/* (non-Javadoc)
	 * @see com.capgemini.db.IData#find(java.sql.Statement, int)
	 */
	@Override
	public ResultSet find(Statement stmt, int mobile) throws SQLException {
		ResultSet rs = stmt.executeQuery("select * from Wallet where accNumber = '"+mobile+"' ");
		while(rs.next())
		    System.out.println(rs.getInt(3));
		return rs;
	}
	

}
