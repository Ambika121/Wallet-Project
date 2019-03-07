package com.capgemini.beans;

import java.math.BigDecimal;

public class Wallet {
	
	private BigDecimal Balance;

	public BigDecimal getBalance() {
		return Balance;
	}

	public void setBalance(BigDecimal balance) {
		Balance = balance;
	}

	public Wallet(BigDecimal balance) {
		super();
		Balance = balance;
	}
	
	@Override
	public String toString()
	{
		return(Balance.toString());
	}

}
