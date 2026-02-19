package org.bank.main;

import java.util.Scanner;

import org.bank.service.AdminService;
import org.bank.service.CustomerService;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CustomerService customerService = new CustomerService();
		AdminService adminService = new AdminService();
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
		switch(request) {
		case 1:{
			System.out.println("Customer Registration");
			
			customerService.customerRegistration();
			break;
		}
		case 2:{
			System.out.println("Customer Login");
			customerService.customerLogin();
			break;
		}
		case 3:{
			System.out.println("Admin Login");
			adminService.adminLogin();
			break;
		}
		default:{
			System.out.println("Invalid Request...");
			break;
		}
		}
	}
}
