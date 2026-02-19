package org.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bank.util.DBConnection;

public class AdminDAO {
	private static final String admin_login = "select * from admin_details where Admin_Emailid=? and Admin_Password=?";
	
	public boolean selectAdminDetailsByUsingEmailidAndPassword(String emailid,String password) {
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(admin_login);
			preparedStatement.setString(1, emailid);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
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
}
