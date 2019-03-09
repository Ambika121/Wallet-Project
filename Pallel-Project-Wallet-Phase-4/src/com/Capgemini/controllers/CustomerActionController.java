package com.Capgemini.controllers;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Capgemini.beans.Customer;
import com.Capgemini.exception.InsufficientBalanceException;
import com.Capgemini.exception.InvalidInputException;
import com.Capgemini.service.WalletService;

@Controller
public class CustomerActionController {
	@Autowired	
	WalletService ser;
	
	@RequestMapping(value="/registerCustomer")
	public ModelAndView registerCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result )
	{
		if(result.hasErrors())return new ModelAndView("signin");
			customer=ser.createAccount(customer);			
			return new ModelAndView("welcomepage","customer",customer);
	}
	@RequestMapping(value="/viewwallet")
	public ModelAndView viewCustomer(@RequestParam("mobileNo") String mobileNo )
	{
		Customer customer =	ser.showBalance(mobileNo);
		return new ModelAndView("viewwallet","customer",customer);
	}
	@RequestMapping(value="/deposit")
	public String getdeposit()
	{
		return "deposit";
	}
	
	@RequestMapping(value="/depositmoney", method = RequestMethod.GET)
	public ModelAndView deposit(HttpServletRequest request)
	{
		Customer customer = null;
		String mob=request.getParameter("mobileNo");
		String bal=request.getParameter("balance");
		
		try {
			customer=ser.depositAmount(mob, new BigDecimal(bal));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("depositsucess","customer",customer);		
	}
@RequestMapping(value="/withdraw")
	public String getwithdraw()
	{
		return "withdraw";		
	}
	
	@RequestMapping(value="/withdrawmoney", method = RequestMethod.GET)
	public ModelAndView draw(HttpServletRequest request)
	{
		Customer customer = null;
		String mob=request.getParameter("mobileNo");
		String bal=request.getParameter("balance");
		
		
			try {
				customer=ser.withdrawAmount(mob, new BigDecimal(bal));
			} catch (InvalidInputException | InsufficientBalanceException e) {
				e.printStackTrace();
			}
		
		
		return new ModelAndView("withdrawsucess","customer",customer);		
	}
	
	@RequestMapping(value="/fundtransfer")
	public String Funds()
	{
		return "fundtransfer";
	}
	
	@RequestMapping(value="/fundtransfered", method = RequestMethod.GET)
	public ModelAndView fundTransfer(HttpServletRequest request)
	{
		Customer customer = null;
		String source=request.getParameter("sourceMobileNo");
		String target=request.getParameter("destinationMobileNo");
		String amount=request.getParameter("balance");
		
		try {
			customer=ser.fundTransfer(source, target, new BigDecimal(amount));
		} catch (InvalidInputException | InsufficientBalanceException e) {
			
			e.printStackTrace();
		}
		return new ModelAndView("fundstransfered","customer",customer);		
	}
	
}
