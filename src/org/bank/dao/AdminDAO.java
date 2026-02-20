package org.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.CustomerDetails;
import org.bank.util.DBConnection;

public class AdminDAO {
	private static final String admin_login = "select * from admin_details where Admin_Emailid=? and Admin_Password=?";
	
	public boolean selectAdminDetailsByUsingEmailidAndPassword(String emailid,String password) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(admin_login);
			ps.setString(1, emailid);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CustomerDetails> getAllCustomers(){
		String query = "SELECT * from bank_customer_details";
		
		List<CustomerDetails> list = new ArrayList<>();
		
		try {
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				CustomerDetails customer = new CustomerDetails(rs.getInt(1),rs.getString(2),rs.getLong(3),rs.getLong(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getLong(9),rs.getInt(10),
						rs.getString(11),rs.getString(12),rs.getString(13),rs.getDouble(14),rs.getString(15));
				list.add(customer);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean closeAccount(long accNo) {
		String query = "DELETE * FROM bank_customer_details where Customer_Account_Number=?";
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setLong(1, accNo);
			
			int result = ps.executeUpdate();
			return result>0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
