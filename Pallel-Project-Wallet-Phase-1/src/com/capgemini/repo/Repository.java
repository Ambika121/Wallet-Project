package com.capgemini.repo;

import com.capgemini.beans.Customer;

public interface Repository {

	boolean save(Customer customer);

	Customer find(int mobile);

}