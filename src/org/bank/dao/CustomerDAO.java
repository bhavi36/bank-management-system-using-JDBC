package org.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.CustomerDetails;

public class CustomerDAO {
	
	private static final String insert_customer_details=
	"insert into bank_customer_details(Customer_Name, Customer_Mobile_Number, Customer_Aadhar_Number, Customer_Pan_Number, Customer_Email_Id, Customer_Address, Customer_Designation,IFSC_Code, Branch, Type_Of_Account, Amount, Gender)values(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	private static final String select_all = "select * from bank_customer_details";
	
	private static final String customer_login = "select * from bank_customer_details where (Customer_Email_Id=? or Customer_Mobile_Number=?) and Customer_Pin=?";
	
	private static final String get_By_Using_Account_Number = "select * from  bank_customer_details where Customer_Account_Number=?";
	
	private static final String update_amount = "update bank_customer_details set Amount = ? where Customer_Account_Number=?";
	
	private static final String select_cutomer_by_ac_Pin = "select * from bank_customer_details where Customer_Email_Id is null and Customer_Pin is null";
	
	private static final String update_ac_in="update bank_customer_details set Customer_Account_Number=? , Customer_Pin=? where Customer_Aadhar_Number=?";
	
	public void insertCustomerDetails(CustomerDetails customerDetails) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root", "A+b33B-a");
			PreparedStatement preparedStatement = connection.prepareStatement(insert_customer_details);
			preparedStatement.setString(1, customerDetails.getCustomername());
			preparedStatement.setLong(2, customerDetails.getCustomermobilenumber());
			preparedStatement.setLong(3, customerDetails.getAadharnumber());
			preparedStatement.setString(4, customerDetails.getPannumber());
			preparedStatement.setString(5, customerDetails.getEmailid());
			preparedStatement.setString(6, customerDetails.getAddress());
			preparedStatement.setString(7, customerDetails.getDesignation());
			preparedStatement.setString(8, customerDetails.getIfsccode());
			preparedStatement.setString(9, customerDetails.getBranch());
			preparedStatement.setString(10, customerDetails.getTypeofaccount());
			preparedStatement.setDouble(11, customerDetails.getAmount());
			preparedStatement.setString(12, customerDetails.getGender());
			int result = preparedStatement.executeUpdate();
			if(result!=0) {
				System.out.println("Customer Registration Successful");
			}
			else {
				System.out.println("Server 500 error");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public List<CustomerDetails> getAllCustomerDetails(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root", "A+b33B-a");
			PreparedStatement preparedStatement = connection.prepareStatement(select_all);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<CustomerDetails> listOfCustomerDetails = new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					CustomerDetails customerDetails = new CustomerDetails();
					customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setCustomermobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
					
					listOfCustomerDetails.add(customerDetails);
				}
				return listOfCustomerDetails;
			}
			else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public CustomerDetails getCustomerDetailsByUsingEmailidOrMobileNumberAndPin(String emailidormobilenumber,int pin) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root", "A+b33B-a");
			PreparedStatement preparedStatement = connection.prepareStatement(customer_login);
			preparedStatement.setString(1, emailidormobilenumber);
			preparedStatement.setString(2, emailidormobilenumber);
			preparedStatement.setInt(3, pin);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
//				if(customerDetails.getGender().equalsIgnoreCase("Female")) {
//					System.out.println("Welcome ms:-"+customerDetails.getCustomername());
//				}
				System.out.println("Login Successfull...");
				CustomerDetails customerDetails = new CustomerDetails();
				customerDetails.setGender(resultSet.getString("Gender"));
				customerDetails.setCustomername(resultSet.getString("Customer_Name"));
				return customerDetails;
			}
			else {
				System.out.println("Invalid Details");
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	public CustomerDetails getCustomerDetailsByUsingAccountNumber(long useraccountnumber) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root", "A+b33B-a");
			PreparedStatement preparedStatement = connection.prepareStatement(get_By_Using_Account_Number);
			preparedStatement.setLong(1, useraccountnumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				CustomerDetails customerDetails = new CustomerDetails();
				customerDetails.setAmount(resultSet.getDouble("Amount"));
				return customerDetails;
			}
			else {
				System.out.println("Invalid Details");
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateAmountByUsingAccountNumber(long accountnumber,double balanceamount) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root", "A+b33B-a");
			PreparedStatement preparedStatement = connection.prepareStatement(update_amount);
			preparedStatement.setDouble(1, balanceamount);
			preparedStatement.setLong(2, accountnumber);
			int result = preparedStatement.executeUpdate();
			if(result!=0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CustomerDetails> selectCustomerDetailsByUsingAccountNumberPin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root", "A+b33B-a");
			PreparedStatement preparedStatement = connection.prepareStatement(select_cutomer_by_ac_Pin);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<CustomerDetails> listOfCustomerDetails = new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					CustomerDetails customerDetails = new CustomerDetails();
					customerDetails.setCustomername(resultSet.getString("Customer_Name"));
					customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setCustomermobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
					
					listOfCustomerDetails.add(customerDetails);
				}
				return listOfCustomerDetails;
			}
			else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateCustomerAccountNumberAndPinByUsingAadharNumber(long accountnumber,int pin,long aadharnumber){
		/*
		 * update bank_customer_details set Customer_Account_Number=? , Customer_Pin=? where Customer_Aadhar_Number=?*/
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root", "A+b33B-a");
			PreparedStatement preparedStatement = connection.prepareStatement(update_ac_in);
			preparedStatement.setLong(1,accountnumber);
			preparedStatement.setInt(2, pin);
			preparedStatement.setLong(3, aadharnumber);
			int result = preparedStatement.executeUpdate();
			if(result!=0) {
				return true;
			}
			else {
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
