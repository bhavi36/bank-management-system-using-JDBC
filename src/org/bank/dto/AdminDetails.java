package org.bank.dto;

public class AdminDetails {
	private int adminid;
	private String adminemailid;
	private String adminpassword;
	public AdminDetails() {
		super();
	}
	public AdminDetails(int adminid, String adminemailid, String adminpassword) {
		super();
		this.adminid = adminid;
		this.adminemailid = adminemailid;
		this.adminpassword = adminpassword;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public String getAdminemailid() {
		return adminemailid;
	}
	public void setAdminemailid(String adminemailid) {
		this.adminemailid = adminemailid;
	}
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	@Override
	public String toString() {
		return "AdminDetails [adminid=" + adminid + ", adminemailid=" + adminemailid + ", adminpassword="
				+ adminpassword + "]";
	}
	
	
}
