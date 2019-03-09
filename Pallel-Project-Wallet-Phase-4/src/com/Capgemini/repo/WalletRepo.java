package com.Capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Capgemini.beans.Customer;

public interface WalletRepo extends JpaRepository<Customer, String> {
		
}
