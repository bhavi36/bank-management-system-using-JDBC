package org.bank.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails {
//	Transaction_Id, 
//	Transaction_Type, 
//	Transaction_Date, 
//	Transaction_Time, 
//	Transaction_Amount, 
//	Balance, 
//	Account_Number
	
	private int transactionid;
	private String transactiontype;
	private LocalDate transactiondate;
	private LocalTime transactiontime;
	private double transactionamount;
	private double balanceamount;
	private long accountnumber;
	public TransactionDetails() {
		super();
	}
	public TransactionDetails(int transactionid, String transactiontype, LocalDate transactiondate,
			LocalTime transactiontime, double transactionamount, double balanceamount, long accountnumber) {
		super();
		this.transactionid = transactionid;
		this.transactiontype = transactiontype;
		this.transactiondate = transactiondate;
		this.transactiontime = transactiontime;
		this.transactionamount = transactionamount;
		this.balanceamount = balanceamount;
		this.accountnumber = accountnumber;
	}
	public int getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public LocalDate getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(LocalDate transactiondate) {
		this.transactiondate = transactiondate;
	}
	public LocalTime getTransactiontime() {
		return transactiontime;
	}
	public void setTransactiontime(LocalTime transactiontime) {
		this.transactiontime = transactiontime;
	}
	public double getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(double transactionamount) {
		this.transactionamount = transactionamount;
	}
	public double getBalanceamount() {
		return balanceamount;
	}
	public void setBalanceamount(double balanceamount) {
		this.balanceamount = balanceamount;
	}
	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	
	
	
}
