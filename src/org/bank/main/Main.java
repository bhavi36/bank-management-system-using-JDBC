package org.bank.main;

import java.util.List;
import java.util.Scanner;

import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.service.AdminService;
import org.bank.service.CustomerService;
import org.bank.service.TransactionService;

public class Main {
	public static void main(String[] args) {
		

		CustomerDetails customer = new CustomerDetails();
		
		CustomerService customerService = new CustomerService();
		
		TransactionService transactionService = new TransactionService();
		
		AdminService adminService = new AdminService();
		
		
		Scanner scanner = new Scanner(System.in);
		
		String msg = "***----**** 🎉🎉 Welcome to JSP Bank 🎉🎉 ****----***";
		for(int i=0;i<msg.length();i++) {
			char ch=msg.charAt(i);
			System.out.print(ch);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("Enter \n 1. For Customer Registration \n 2. For Customer Login \n 3. Admin Login");
		int request = scanner.nextInt();
		scanner.nextLine();
		switch(request) {
		case 1:{
			System.out.println("Customer Registration");

			System.out.println("Enter Name:");
			customer.setCustomername(scanner.nextLine());

			System.out.println("Enter Mobile:");
			customer.setCustomermobilenumber(scanner.nextLong());

			System.out.println("Enter Aadhar:");
			customer.setAadharnumber(scanner.nextLong());

			System.out.println("Enter PAN:");
			customer.setPannumber(scanner.next());

			System.out.println("Enter Email:");
			customer.setEmailid(scanner.next());

			System.out.println("Enter Address:");
			customer.setAddress(scanner.next());

			System.out.println("Enter Designation:");
			customer.setDesignation(scanner.next());

			System.out.println("Enter PIN:");
			customer.setPin(scanner.nextInt());

			System.out.println("Enter Account Type:");
			customer.setTypeofaccount(scanner.next());

			System.out.println("Enter Initial Amount:");
			customer.setAmount(scanner.nextDouble());

			System.out.println("Enter Gender:");
			customer.setGender(scanner.next());
			
			customerService.registerCustomer(customer);
			
			break;
		}
		case 2:{
			System.out.println("Customer Login");
			
			System.out.println("Enter email or mobile: ");
			String input = scanner.nextLine();
			System.out.println("Enter pin: ");
			int pin = Integer.valueOf(scanner.nextLine());
			customer=customerService.customerLogin(input,pin);
			if(customer!=null) {
				System.out.println("Enter \n 1.For Withdraw \n 2.For Deposit \n 3.For CheckBalance \n 4.For Statement \n 5.For Close Account \n 6.For Change Pin");
				switch(Integer.valueOf(scanner.nextLine())) {
				case 1 :{
					System.out.println("Withdraw...");
					System.out.print("Enter amount to withdraw: ");
					double amount = scanner.nextDouble();
					customerService.withdraw(customer.getAccountnumber(), amount);
					
					break;
				}
				case 2 :{
					System.out.println("____Deposit_____");
					System.out.print("Enter amount to deposit: ");
					double amount = scanner.nextDouble();
					customerService.deposit(customer.getAccountnumber(), amount);
			
					break;
				}
				case 3 :{
					System.out.println("CheckBalance");
					//System.out.println(customer.getAccountnumber());
					//System.out.println(customerService.getBalance(customer.getAccountnumber()));
					System.out.println(customer.getAmount());
					break;
				}
				case 4 :{
					System.out.println("Statement");
					List<TransactionDetails> list = transactionService.getTransactionByAccNo(customer.getAccountnumber());
					for(TransactionDetails transaction:list) {
						System.out.println(transaction);
					}
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
			}else {
				System.out.println("Invalid Login Details");
			}
			
			
			break;
		}
		case 3:{
			System.out.println("Admin Login");
			
			System.out.println("Enter Admin Email id");
			String emailid = scanner.next();
			System.out.println("Enter Admin Password");
			String password = scanner.next();
			
			if(adminService.adminLogin(emailid,password)) {
				System.out.println("\n1. View all customers\n2. Delete customer\n3. Exit");
				int choice = Integer.valueOf(scanner.nextLine());
				
				switch(choice) {
				case 1:{
					System.out.println("Viewing all customers: ");
					List<CustomerDetails> allCustomers = adminService.getAllCustomers();
					
					for(CustomerDetails customerDetails:allCustomers) {
						System.out.println(customerDetails);
					}
				}
				case 2: {
					System.out.println("DeleteCustomerr");
				}
				}
			}
				
			break;
		}
		default:{
			System.out.println("Invalid Request...");
			break;
		}
		}
		
		
	
	}
}
