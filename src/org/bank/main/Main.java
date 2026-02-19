package org.bank.main;

import java.util.Scanner;

import org.bank.dto.CustomerDetails;
import org.bank.service.CustomerService;

public class Main {
	public static void main(String[] args) {
		

		CustomerDetails customer = new CustomerDetails();
		
		CustomerService customerService = new CustomerService();
		
		
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
			customer.setCustomername(scanner.next());

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
			if(customerService.customerLogin(input,pin)) {
				System.out.println("Enter \n 1.For Credit \n 2.For Debit \n 3.For CheckBalance \n 4.For Statement \n 5.For Close Account \n 6.For Change Pin");
				switch(Integer.valueOf(scanner.nextLine())) {
				case 1 :{
					System.out.println("Credit");
					break;
				}
				case 2 :{
					System.out.println("Debit");
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
			}else {
				System.out.println("Invalid Login Details");
			}
			
			
			break;
		}
		case 3:{
			System.out.println("Admin Login");
			break;
		}
		default:{
			System.out.println("Invalid Request...");
			break;
		}
		}
		
		
	
	}
}
