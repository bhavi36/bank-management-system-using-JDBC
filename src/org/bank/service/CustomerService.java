package org.bank.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bank.dao.CustomerDAO;
import org.bank.dao.TransactionDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.exception.CustomerInvalidDataException;
import org.bank.exception.InsufficientBalanceException;
import org.bank.exception.InvalidLoginException;
import org.bank.validation.Validation;

public class CustomerService {
	private CustomerDAO customerDAO = new CustomerDAO();
	private TransactionDAO transactionDAO = new TransactionDAO();

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
	public boolean registerCustomer(CustomerDetails customer) {
		
		if (!Validation.isValidMobile(customer.getCustomermobilenumber())) {
		        throw new CustomerInvalidDataException("Mobile must be 10 digits");
		}

		if (!Validation.isValidPin(customer.getPin())) {
		        throw new CustomerInvalidDataException("PIN must be 4 digits");
		}

		if (!Validation.isValidAmount(customer.getAmount())) {
		        throw new CustomerInvalidDataException("Balance cannot be negative");
		}

        long accNo = generateUniqueAccountNumber();
        customer.setAccountnumber(accNo);

        customer.setIfsccode("JSP1234");
        customer.setBranch("JNTU");

        return customerDAO.insertCustomer(customer);
    }
	public CustomerDetails customerLogin(String input,int pin) {

	    
	    CustomerDetails customer = customerDAO.getCustomerByEmailOrMobileAndPin(input, pin);
	    
	    if (customer == null) {
	        throw new InvalidLoginException("Invalid Email/Mobile or PIN");
	    }

//
//	    if (customer != null) {
//
//	        if (customer.getGender().equalsIgnoreCase("Male")) {
//	            System.out.println("Welcome Mr. " + customer.getCustomername());
//	        } else if (customer.getGender().equalsIgnoreCase("Female")) {
//	            System.out.println("Welcome Mrs. " + customer.getCustomername());
//	        } else {
//	            System.out.println("Welcome " + customer.getCustomername());
//	        }
//
//	        return customer;
//	    } else {
//	        return null;
//	    }
	    
	    return customer;
	}
	public double getBalance(long accNo) {
		return customerDAO.getBalance(accNo);
	}
	public boolean deposit(long accNo,double amount) {
		
		if (!Validation.isValidAmount(amount)) {
	        throw new CustomerInvalidDataException("Invalid deposit amount");
	    }
		
		double currentBalance = getBalance(accNo);
		double newBalance = currentBalance+amount;
		if(customerDAO.updateBalance(accNo, newBalance)) {
//			System.out.println("Debited...");
//			System.out.print("Updated Balance is : ");
//			System.out.println(getBalance(accNo));
//			
			logTransaction(accNo, "DEPOSIT", amount, newBalance);
			return true;
		}
		
//		else {
//			System.out.println("Failed...");
//		}
		
		return false;
	}
	public boolean withdraw(long accNo,double amount) {
		
		if (!Validation.isValidAmount(amount)) {
	        throw new CustomerInvalidDataException("Invalid withdraw amount");
	    }
		
		double currentBalance = customerDAO.getBalance(accNo);
		
		if (currentBalance < amount) {
	        throw new InsufficientBalanceException("Insufficient Balance");
	    }
		double newBalance = currentBalance-amount;
		if(customerDAO.updateBalance(accNo, newBalance)) {
//			System.out.println("Withdrawn...");
//			System.out.println("Updated Balance is : ");
//			System.out.println(getBalance(accNo));
			logTransaction(accNo, "WITHDRAW", amount, newBalance);
			return true;
		}
		
		return false;
	}
	
	private void logTransaction(long accNo, String type, double amount, double newBalance) {
		TransactionDetails t = new TransactionDetails();
		
		t.setAccountnumber(accNo);
		t.setTransactiontype(type);
		t.setTransactionamount(amount);
		t.setBalanceamount(newBalance);
		t.setTransactiondate(LocalDate.now());
		t.setTransactiontime(LocalTime.now());
		
		transactionDAO.insertTransactionDetails(t);
			
	}

}

