package com.capgemini.beans;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Customer")
public class Customer {
	@Column(name = "name")
	String name;
	@Id
	@Column(name = "mobile")
	int mobile;
	@Column(name = "wallet")
	@Embedded Wallet wallet;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobile() {
		return mobile;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
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
