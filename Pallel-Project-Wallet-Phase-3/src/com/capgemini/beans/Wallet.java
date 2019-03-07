package com.capgemini.beans;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Wallet {
	
	@Column(name = "Balance")
	private BigDecimal Balance;

	public BigDecimal getBalance() {
		return Balance;
	}

	public void setBalance(BigDecimal balance) {
		Balance = balance;
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
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
