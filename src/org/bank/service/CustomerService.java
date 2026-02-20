package org.bank.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bank.dao.CustomerDAO;
import org.bank.dao.TransactionDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;

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
	public CustomerDetails customerLogin(String input,int pin) {

	    
	    CustomerDetails customer = customerDAO.getCustomerByEmailOrMobileAndPin(input, pin);

	    if (customer != null) {

	        if (customer.getGender().equalsIgnoreCase("Male")) {
	            System.out.println("Welcome Mr. " + customer.getCustomername());
	        } else if (customer.getGender().equalsIgnoreCase("Female")) {
	            System.out.println("Welcome Mrs. " + customer.getCustomername());
	        } else {
	            System.out.println("Welcome " + customer.getCustomername());
	        }

	        return customer;
	    } else {
	        return null;
	    }
	}
	public double getBalance(long accNo) {
		return customerDAO.getBalance(accNo);
	}
	public void deposit(long accNo,double amount) {
		
		double currentBalance = getBalance(accNo);
		if(currentBalance>0) {
			if(customerDAO.updateBalance(accNo, currentBalance+amount)) {
				System.out.println("Debited...");
				System.out.print("Updated Balance is : ");
				System.out.println(getBalance(accNo));
				
				logTransaction(accNo, "Deposit", amount, getBalance(accNo));
			}else {
				System.out.println("Failed...");
			}
		}
	}
	public void withdraw(long accNo,double amount) {
		
		double currentBalance = customerDAO.getBalance(accNo);
		if(currentBalance>=amount) {
			if(customerDAO.updateBalance(accNo, currentBalance-amount)) {
				System.out.println("Withdrawn...");
				System.out.println("Updated Balance is : ");
				System.out.println(getBalance(accNo));
				logTransaction(accNo, "Withdraw", amount, getBalance(accNo));
			}
			else
				System.out.println("Failed...");
		}else {
			System.out.println("Insufficient Balance...");
		}
	}
	
	private void logTransaction(long accNo, String type, double amount, double newBalance) {
		TransactionDetails t = new TransactionDetails();
		
		t.setAccountnumber(accNo);
		t.setTransactiontype(type);
		t.setTransactionamount(amount);
		t.setBalanceamount(newBalance);
		t.setTransactiondate(LocalDate.now());
		t.setTransactiontime(LocalTime.now());
		
		if(transactionDAO.insertTransactionDetails(t))
			System.out.println("Transaction inserted...");
	}

}

