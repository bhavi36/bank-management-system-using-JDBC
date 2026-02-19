package org.bank.util;


import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;
import org.bank.service.CustomerService;

public class TestConnection {
	public static void main(String[] args) {
		CustomerDetails customer = new CustomerDetails();
		CustomerService service = new CustomerService();
		customer.setCustomername("Bhavani");
		customer.setCustomermobilenumber(9876543220L);
		customer.setAadharnumber(123456789012L);
		customer.setPannumber("ABCDE1234F");
		customer.setEmailid("test@gmail.com");
		customer.setAddress("Warangal");
		customer.setDesignation("Student");
//		customer.setAccountnumber(1001L);
		long accountNumber = service.generateUniqueAccountNumber();
		customer.setAccountnumber(accountNumber);

		customer.setPin(1234);
		customer.setIfsccode("JSP1234");
		customer.setBranch("JNTU");
		customer.setTypeofaccount("Savings");
		customer.setAmount(5000);
		customer.setGender("Female");

		CustomerDAO dao = new CustomerDAO();

		boolean result = dao.insertCustomer(customer);

		System.out.println(result ? "Inserted" : "Failed");

    }
}
