package com.capgemini.beans;

public class Customer {
	
	String name;
	int mobile;
	Wallet wallet;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public Customer(String name, int mobile, Wallet wallet) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.wallet = wallet;
	}
	
	public String toString()
	{
		return("Name: " + getName() + "\nMobile: " + getMobile() + "\nWallet: " + getWallet());
	}

}
