package org.bank.service;

import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;

public class CustomerService {
	CustomerDAO customerDAO = new CustomerDAO();

	public long generateAccountNumber() {
	    long min = 1000000000L;  // 10 digits
	    long max = 9999999999L;

	    return (long) (Math.random() * (max - min + 1)) + min;
	}
	public long generateUniqueAccountNumber() {
	    long accountNumber;

	    do {
	        accountNumber = generateAccountNumber();
	    } while (customerDAO.isAccountNumberExists(accountNumber));

	    return accountNumber;
	}
	public void registerCustomer(CustomerDetails customer) {

        long accNo = generateUniqueAccountNumber();
        customer.setAccountnumber(accNo);

        customer.setIfsccode("JSP1234");
        customer.setBranch("JNTU");

        if(customerDAO.insertCustomer(customer)) {
        	System.out.println("Registered Successfullyy..");
        }else {
        	System.out.println("Not Registered...");
        }
    }
	public boolean customerLogin(String input,int pin) {

	    
	    CustomerDetails customer = customerDAO.getCustomerByEmailOrMobileAndPin(input, pin);

	    if (customer != null) {

	        if (customer.getGender().equalsIgnoreCase("Male")) {
	            System.out.println("Welcome Mr. " + customer.getCustomername());
	        } else if (customer.getGender().equalsIgnoreCase("Female")) {
	            System.out.println("Welcome Mrs. " + customer.getCustomername());
	        } else {
	            System.out.println("Welcome " + customer.getCustomername());
	        }

	        return true;
	    } else {
	        return false;
	    }
	}
	
	

}

