package org.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bank.dto.CustomerDetails;
import org.bank.util.DBConnection;

public class CustomerDAO {
	public boolean isAccountNumberExists(long accountNumber) {
	    String query = "SELECT Customer_Account_Number FROM bank_customer_details WHERE Customer_Account_Number = ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setLong(1, accountNumber);

	        ResultSet rs = ps.executeQuery();

	        return rs.next(); // if exists return true

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	public boolean insertCustomer(CustomerDetails customer) {

        String query =
        "insert into bank_customer_details(Customer_Name, Customer_Mobile_Number, Customer_Aadhar_Number, Customer_Pan_Number, Customer_Email_Id, Customer_Address, Customer_Designation,Customer_Account_Number,Customer_Pin,IFSC_Code, Branch, Type_Of_Account, Amount, Gender)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
        	Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            		
            ps.setString(1, customer.getCustomername());
            ps.setLong(2, customer.getCustomermobilenumber());
            ps.setLong(3, customer.getAadharnumber());
            ps.setString(4, customer.getPannumber());
            ps.setString(5, customer.getEmailid());
            ps.setString(6, customer.getAddress());
            ps.setString(7, customer.getDesignation());
            ps.setLong(8, customer.getAccountnumber());
            ps.setInt(9, customer.getPin());
            ps.setString(10, customer.getIfsccode());
            ps.setString(11, customer.getBranch());
            ps.setString(12, customer.getTypeofaccount());
            ps.setDouble(13, customer.getAmount());
            ps.setString(14, customer.getGender());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
	
	public CustomerDetails getCustomerByEmailOrMobileAndPin(String input, int pin) {

	    String query = "SELECT * FROM bank_customer_details WHERE (Customer_Email_Id = ? OR Customer_Mobile_Number = ?) AND Customer_Pin = ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setString(1, input);
	        ps.setString(2, input);
	        ps.setInt(3, pin);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            CustomerDetails customer = new CustomerDetails();

	            customer.setCustomerid(rs.getInt(1));
	            customer.setCustomername(rs.getString(2));
	            customer.setCustomermobilenumber(rs.getLong(3));
	            customer.setAadharnumber(rs.getLong(4));
	            customer.setPannumber(rs.getString(5));
	            customer.setEmailid(rs.getString(6));
	            customer.setAddress(rs.getString(7));
	            customer.setDesignation(rs.getString(8));
	            customer.setAccountnumber(rs.getLong(9));
	            customer.setPin(rs.getInt(10));
	            customer.setIfsccode(rs.getString(11));
	            customer.setBranch(rs.getString(12));
	            customer.setTypeofaccount(rs.getString(13));
	            customer.setAmount(rs.getDouble(14));
	            customer.setGender(rs.getString(15));

	            return customer;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}

}
/* "INSERT INTO bank_customer_details "
                + "(customername, customermobilenumber, aadharnumber, pannumber, emailid, address, designation, accountnumber, pin, ifsccode, branch, typeofaccount, amount, gender) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";*/
