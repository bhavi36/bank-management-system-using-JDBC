package org.bank.service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.bank.dao.AdminDAO;
import org.bank.dto.AdminDetails;
import org.bank.dto.CustomerDetails;
 	 	
public class AdminService {
	AdminDAO adminDAO = new AdminDAO();
	CustomerService customerService = new CustomerService();
	Scanner scanner = new Scanner(System.in);
	
	public boolean adminLogin(String emailid,String password) {
		
		
		if(adminDAO.selectAdminDetailsByUsingEmailidAndPassword(emailid, password)) {
			System.out.println("Login Successfull...");
			return true;
		}
		
		System.out.println("Invalid Details...");
		return false;
		
	}
	
	public List<CustomerDetails> getAllCustomers(){
		return adminDAO.getAllCustomers();
	}
	
	public boolean closeAccount(long accNo) {
		return adminDAO.closeAccount(accNo);
	}
}
