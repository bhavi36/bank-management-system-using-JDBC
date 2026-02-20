package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.TransactionDetails;
import org.bank.util.DBConnection;

public class TransactionDAO {
	private static final String insert_transaction_details = "insert into transaction_details(Transaction_Type, Transaction_Date, Transaction_Time, Transaction_Amount, Balance, Account_Number) values (?,?,?,?,?,?)";
	
	
	public boolean insertTransactionDetails(TransactionDetails transactionDetails) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(insert_transaction_details);
			ps.setString(1, transactionDetails.getTransactiontype());
			ps.setDate(2, Date.valueOf(transactionDetails.getTransactiondate()));
			ps.setTime(3, Time.valueOf(transactionDetails.getTransactiontime()));
			ps.setDouble(4, transactionDetails.getTransactionamount());
			ps.setDouble(5, transactionDetails.getBalanceamount());
			ps.setLong(6, transactionDetails.getAccountnumber());
			
			int result = ps.executeUpdate();
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
	
	public List<TransactionDetails> getTransactionsByAccNo(long accNo){
		List<TransactionDetails> list = new ArrayList<TransactionDetails>();
		String query = "SELECT * from transaction_details WHERE Account_Number=?";
		
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setLong(1, accNo);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				TransactionDetails t = new TransactionDetails();
				t.setTransactionid(rs.getInt(1));
				t.setTransactiontype(rs.getString(2));
				t.setTransactiondate(rs.getDate(3).toLocalDate());
				t.setTransactiontime(rs.getTime(4).toLocalTime());
				t.setTransactionamount(rs.getLong(5));
				t.setBalanceamount(rs.getDouble(6));
				t.setAccountnumber(rs.getLong(7));
				
				list.add(t);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
