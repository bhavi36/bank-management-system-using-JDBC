package org.bank.service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.bank.dao.AdminDAO;
import org.bank.dto.CustomerDetails;
 	 	
public class AdminService {
	AdminDAO adminDAO = new AdminDAO();
	CustomerService customerService = new CustomerService();
	Scanner scanner = new Scanner(System.in);
	
	public void adminLogin() {
		
		System.out.println("Enter Admin Email id");
		String emailid = scanner.next();
		System.out.println("Enter Admin Password");
		String password = scanner.next();
		if(adminDAO.selectAdminDetailsByUsingEmailidAndPassword(emailid, password)) {
			System.out.println("Login Successfull...");
			adminOperations();
			
		}
		else 
			System.out.println("Invalid Details...");
	}
	
	private void adminOperations() {
		System.out.println("Enter \n 1. For ACCEPT or Reject Account Request...");
		System.out.println("2. For ACCEPT or Reject Account Closing Request...");
		System.out.println("Enter option ");
		int option = scanner.nextInt();
		switch(option) {
		case 1:{
			System.out.println("Accept or reject account request.");
			List<CustomerDetails> list = customerService.getAllCustomerDetailsByUsingAccountNumberAndPin();
			int count = 1;
			for(CustomerDetails customerDetails:list) {
				System.out.println("S.No :"+ count++);
				System.out.println("Customer Name : "+customerDetails.getCustomername());
				System.out.println("Customer Emailid: "+customerDetails.getEmailid());
				System.out.println("Customer Mobile Number : "+customerDetails.getCustomermobilenumber());
				
			}
			System.out.println("Select SlNo to accept or reject the request");
			int adminselection = scanner.nextInt()-1;
			CustomerDetails customerDetails = list.get(adminselection);
			System.out.println(customerDetails);
			System.out.println("Enter \n 1.To accept \n 2. To reject");
			switch(scanner.nextInt()) {
			case 1:{
				acceptAccountRequest(customerDetails);
			}
			case 2:{
				System.out.println("Rejected");
			}
			}
			break;
		}
		case 2:{
			System.out.println("Accept or reject account closing request.");
			break;
		}
		default:{
			System.out.println("Invalid Option.");
		}
		}
	}
	
	public void acceptAccountRequest(CustomerDetails customerDetails) {
		Random random = new Random();
		long accountnumber = random.nextInt(10000000);
		if(accountnumber < 1000000) {
			accountnumber += 1000000;
		}
		int pin = random.nextInt(1000);
		if(pin < 1000) {
			pin +=1000;
		}
		
		if(customerService.updateAccountNumberAndPinNumberByUsingAadharNumber(accountnumber, pin, customerDetails.getAadharnumber())) {
			System.out.println("Customer Name : "+customerDetails.getCustomername());
			System.out.println("Account Number: "+accountnumber);
			System.out.println("PIN : "+pin);
		}
		else {
			System.out.println("Server 500 ERROR...");
		}
			
		
	}
}
