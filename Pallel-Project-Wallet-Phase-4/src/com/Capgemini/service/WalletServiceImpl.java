package com.Capgemini.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Capgemini.beans.Customer;
import com.Capgemini.repo.WalletRepo;
import com.Capgemini.exception.InsufficientBalanceException;
import com.Capgemini.exception.InvalidInputException;
@Service("ser")
@Component(value="ser")
public class WalletServiceImpl implements WalletService{
	
	@Autowired
	private WalletRepo repo;
	
	public WalletServiceImpl() {
		super();
	}

	public WalletServiceImpl(WalletRepo repo) {
		super();
		this.repo = repo;
	}



	/* (non-Javadoc)
	 * @see com.cg.mypaymentapp.service.WalletService#createAccount(com.cg.mypaymentapp.beans.Customer)
	 */
	@Override
	public Customer createAccount(Customer customer) {

		return repo.save(customer);
		}
	
	 
	/* (non-Javadoc)
	 * @see com.cg.mypaymentapp.service.WalletService#showBalance(java.lang.String)
	 */
	@Override
	public Customer showBalance(String mobileNo)  {
		Customer customer=repo.findOne(mobileNo);
		
			return customer;	
	}
	/* (non-Javadoc)
	 * @see com.cg.mypaymentapp.service.WalletService#fundTransfer(java.lang.String, java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException {

		Customer cust1=repo.findOne(sourceMobileNo);
		Customer cust2=repo.findOne(targetMobileNo);
		if(cust1!=null)
		{
			if(cust2!=null)
			{
				if(cust1.getWallet().getBalance().compareTo(amount)>=0)
				{
				cust1.getWallet().setBalance(cust1.getWallet().getBalance().subtract(amount));
				repo.save(cust1);
				cust2.getWallet().setBalance(cust1.getWallet().getBalance().add(amount));
				repo.save(cust2);				
				
				}
				else
				{
					throw new InsufficientBalanceException("insufficient balance");					
				}
			}
			else
			{
				throw new InvalidInputException("Destination mobile number not found");
			}
		}else
		{
			throw new InvalidInputException("Source mobile number not found");
		}
		
		
		return cust1;
	}

	/* (non-Javadoc)
	 * @see com.cg.mypaymentapp.service.WalletService#depositAmount(java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException {

		Customer cust=repo.findOne(mobileNo);
			cust.getWallet().setBalance(cust.getWallet().getBalance().add(amount));
			repo.save(cust);	
			
		return cust;	
	}

	/* (non-Javadoc)
	 * @see com.cg.mypaymentapp.service.WalletService#withdrawAmount(java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException {
		if(amount==null)
			throw new InvalidInputException("Amount cannot be null");
		
		if(mobileNo==null)
			throw new InvalidInputException("SourceMobile mobile number cannot be null");

		Customer cust=repo.findOne(mobileNo);
		if(cust.getMobileNo()==null)
			throw new InvalidInputException("Mobile number not found");
	if(cust.getWallet().getBalance().compareTo(amount)>=0)
	{
	cust.getWallet().setBalance(cust.getWallet().getBalance().subtract(amount));
	repo.save(cust);
	}
	else
	{
		throw new InsufficientBalanceException("Insufficient balance");		
	}
	return cust;
}
	
}