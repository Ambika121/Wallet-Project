package com.capgemini.repo;

import java.util.Iterator;
import java.util.LinkedList;

import com.capgemini.beans.Customer;

public class RepositoryImplimentation implements Repository {
	
	LinkedList<Customer> linkedlist = new LinkedList<Customer>();
	
	/* (non-Javadoc)
	 * @see com.capgemini.repo.Repository#save(com.capgemini.beans.Customer)
	 */
	@Override
	public boolean save(Customer customer)
	{
		return linkedlist.add(customer);
	}
	
	/* (non-Javadoc)
	 * @see com.capgemini.repo.Repository#find(int)
	 */
	@Override
	public Customer find(int mobile)
	{
		Iterator<Customer> iterator = linkedlist.iterator();
		
		while(iterator.hasNext())
		{
			Customer customer = iterator.next();
			if(customer.getMobile() == mobile)
			{
				return customer;
			}
		}
		return null;
	}

}
