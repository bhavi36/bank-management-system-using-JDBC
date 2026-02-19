package org.bank.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.exception.CustomerInvalidDataException;

public class CustomerService {
	CustomerDAO customerDAO = new CustomerDAO();
	CustomerDetails customerDetails = new CustomerDetails();
	TransactionService transactionService = new TransactionService();
	Scanner scanner = new Scanner(System.in);
	public void customerRegistration() {
		List<CustomerDetails> allCustomerDetails = customerDAO.getAllCustomerDetails();
		
		System.out.println("Enter Customer Name");
		String name = scanner.next();
		for(int i=0;i<=name.length()-1;i++) {
			char ch = name.charAt(i);
			if(ch>='0' && ch<='9')
				throw new CustomerInvalidDataException("Name containing digits");
		}
		System.out.println("Enter Customer Email id");
		String emailId = scanner.next();
		try {
			if(emailId.contains("@gmail.com")) {
				long emailIdCount = allCustomerDetails.stream().filter((customer->customer.getEmailid().equals(emailId))).count();
				if(emailIdCount!=0) {
					throw new CustomerInvalidDataException("Email Already existed");
				}
				else {
					customerDetails.setEmailid(emailId);
				}
			}
			else {
				throw new CustomerInvalidDataException("Invalid Email");
			}
			
		} catch (CustomerInvalidDataException c) {
			// TODO Auto-generated catch block
			
			System.out.println(c.getExceptionMsg());
			System.out.println("Re-enter emailid");
		}
		System.out.println("Enter Customer Mobile Number");
		long mobileNumber = scanner.nextLong();
		if(mobileNumber>=6000000000l && mobileNumber<=9999999999l) {
			
		}
		else {
			throw new CustomerInvalidDataException("Invalid mobile number");
		}
		System.out.println("Enter Customer Aadhar Number");
		long aadharNumber = scanner.nextLong();
		String longAsString = Long.toString(aadharNumber); // or Long.toString(myLong);
		int numberOfDigits = longAsString.length();
		if(numberOfDigits==12) {
			
		}
		else {
			throw new CustomerInvalidDataException("Invalid Count of Aadhar number");
		}
			
		System.out.println("Number of digits: " + numberOfDigits);
		System.out.println("Enter Customer PAN Number");
		String panNumber = scanner.next();
		//for(int i=0;i<=panNumber.length()-1;i++) {
		//char ch=panNumber.charAt(i);
		//if(i>=0 && i<=4 && i==9) {
//			if(ch>='A' && ch<='Z') {
//				
//			}
//			else {
//				throw new CustomerInvalidDataException("Invalid Pan number");
//			}
		//}
		//else if(i>=5 && i<=8) {
//			if(Character.isDigit(ch)) {
//				
//			}
//			else {
//				throw new CustomerInvalidDataException("Invalid Pan number");
//			}
		//}
		//
		//}

		System.out.println("Enter Customer Address");
		String address = scanner.next();
		System.out.println("Enter Customer Designation");
		String designation = scanner.next();
		System.out.println("Enter Customer Gender");
		String gender = scanner.next();
		if(gender.equalsIgnoreCase("Male")||gender.equalsIgnoreCase("Female")||gender.equalsIgnoreCase("Others")) {
			
		}
		else {
			throw new CustomerInvalidDataException("Invalid gender");
		}
		System.out.println("Enter Type of account");
		System.out.println("Enter \n 1 for Savingss \n 2 for Businness");
		String typeOfAccount = "";
		int choice = scanner.nextInt();
		switch(choice) {
		case 1 :{
			typeOfAccount = "Savings";
			break;
		}
		case 2 :{
			typeOfAccount = "Business";
			break;
		}
		
		}
		
		System.out.println("Enter type of amount");
		double amount = scanner.nextDouble();
		
		
		customerDetails.setCustomername(name);
		customerDetails.setEmailid(emailId);
		customerDetails.setCustomermobilenumber(mobileNumber);
		customerDetails.setAadharnumber(aadharNumber);
		customerDetails.setPannumber(panNumber);
		customerDetails.setAddress(address);
		customerDetails.setDesignation(designation);
		customerDetails.setGender(gender);
		customerDetails.setTypeofaccount(typeOfAccount);
		customerDetails.setAmount(amount);
		
		customerDAO.insertCustomerDetails(customerDetails);
	}
	public void customerLogin() {
		System.out.println("Enter Customer Emailid or MobileNumber");
		String emailidormobilenumber = scanner.next();
		System.out.println("Enter Customer PIN");
		int pin = scanner.nextInt();
		System.out.println("Enter Account number");
		long account = scanner.nextLong();
		CustomerDetails login = customerDAO.getCustomerDetailsByUsingEmailidOrMobileNumberAndPin(emailidormobilenumber, pin);
		if(login!=null) {
			if(login.getGender().equalsIgnoreCase("Male")) {
				System.out.println("welcome mr:- "+login.getCustomername());
//				System.out.println("Enter Account Number: ");
//				long account = scanner.nextLong();
				customerOperations(account);
			}
			if(login.getGender().equalsIgnoreCase("Female")) {
				System.out.println("Welcome mrs:- "+login.getCustomername());
				customerOperations(account);
			}
			
		}
		else {
			System.out.println("Invalid Details");
		}
		

		
	}
	
	public void customerOperations(long account_number) {
		System.out.println("Enter \n 1.For Credit \n 2.For Debit \n 3.For CheckBalance \n 4.For Statement \n 5.For Close Account \n 6.For Change Pin");
		switch(scanner.nextInt()) {
		case 1 :{
			System.out.println("Credit");
			break;
		}
		case 2 :{
			System.out.println("Debit");
			debit(account_number);
			break;
		}
		case 3 :{
			System.out.println("CheckBalance");
			break;
		}
		case 4 :{
			System.out.println("Statement");
			break;
		}
		case 5 :{
			System.out.println(" Close Account");
			break;
		}
		case 6 :{
			System.out.println("Change Pin");
			break;
		}
		default:{
			System.out.println("Invalid choice...");
		}
		}
	}
	
	public void debit(long databaseaccountnumber) {
		System.out.println("Enter Customer Account Number");
		long useraccountnumber = scanner.nextLong();
		CustomerDetails details = customerDAO.getCustomerDetailsByUsingAccountNumber(useraccountnumber);
		if(details!=null) {
			System.out.println("Enter the amount to be debited");
			double useramount = scanner.nextDouble();
			double databaseamount = details.getAmount();
			if(useramount < databaseamount) {
				double balanceamount = databaseamount - useramount;
				if(customerDAO.updateAmountByUsingAccountNumber(useraccountnumber, balanceamount)) {
					TransactionDetails transactionDetails = new TransactionDetails();
					transactionDetails.setTransactiontype("DEBIT");
					transactionDetails.setTransactiondate(LocalDate.now());
					transactionDetails.setTransactiontime(LocalTime.now());
					transactionDetails.setTransactionamount(useramount);
					transactionDetails.setBalanceamount(balanceamount);
					transactionDetails.setAccountnumber(useraccountnumber);
					transactionService.insertTransactionDetails(transactionDetails);
					System.out.println(useramount + "Rupees Debited successfully");
				}
				else {
					System.out.println("Server 500");
				}
			}
			else {
				System.out.println("In-Sufficient Balance..");
			}
		}
		
		else {
			System.out.println("Invalid Account Number...");
		}
	}
	
	public List<CustomerDetails> getAllCustomerDetailsByUsingAccountNumberAndPin() {
		List<CustomerDetails> list = customerDAO.selectCustomerDetailsByUsingAccountNumberPin();
		if(!list.isEmpty()) {
			return list;
		}
		else {
			return null;
		}
	}
	
	public boolean updateAccountNumberAndPinNumberByUsingAadharNumber(long accountnumber,int pin,long aadharnumber) {
		return customerDAO.updateCustomerAccountNumberAndPinByUsingAadharNumber(accountnumber, pin, aadharnumber);
	}
}

