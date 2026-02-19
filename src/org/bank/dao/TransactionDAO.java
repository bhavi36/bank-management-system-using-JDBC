package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import org.bank.dto.TransactionDetails;
import org.bank.util.DBConnection;

public class TransactionDAO {
	private static final String insert_transaction_details = "insert into transaction_details(Transaction_Type, Transaction_Date, Transaction_Time, Transaction_Amount, Balance, Account_Number) values (?,?,?,?,?,?)";
	
	
	public boolean insertTransactionDetails(TransactionDetails transactionDetails) {
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insert_transaction_details);
			preparedStatement.setString(1, transactionDetails.getTransactiontype());
			preparedStatement.setDate(2, Date.valueOf(transactionDetails.getTransactiondate()));
			preparedStatement.setTime(3, Time.valueOf(transactionDetails.getTransactiontime()));
			preparedStatement.setDouble(4, transactionDetails.getTransactionamount());
			preparedStatement.setDouble(5, transactionDetails.getBalanceamount());
			preparedStatement.setLong(6, transactionDetails.getAccountnumber());
			
			int result = preparedStatement.executeUpdate();
			if(result!=0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
