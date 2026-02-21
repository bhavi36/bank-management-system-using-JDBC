package org.bank.main;

import java.util.List;
import java.util.Scanner;

import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.exception.CustomerInvalidDataException;
import org.bank.exception.InsufficientBalanceException;
import org.bank.service.AdminService;
import org.bank.service.CustomerService;
import org.bank.service.TransactionService;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        CustomerService customerService = new CustomerService();
        TransactionService transactionService = new TransactionService();
        AdminService adminService = new AdminService();

        String msg = "***----**** 🎉🎉 Welcome to JSP Bank 🎉🎉 ****----***";
        for (int i = 0; i < msg.length(); i++) {
            System.out.print(msg.charAt(i));
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Error displaying message");
            }
        }
        System.out.println();

        while (true) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Customer Registration");
            System.out.println("2. Customer Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int request = scanner.nextInt();
            scanner.nextLine();

            switch(request) {

            case 1: {
                try {
                    CustomerDetails customer = new CustomerDetails();

                    System.out.println("Customer Registration");

                    System.out.print("Enter Name: ");
                    customer.setCustomername(scanner.nextLine());

                    System.out.print("Enter Mobile: ");
                    customer.setCustomermobilenumber(scanner.nextLong());

                    System.out.print("Enter Aadhar: ");
                    customer.setAadharnumber(scanner.nextLong());

                    System.out.print("Enter PAN: ");
                    customer.setPannumber(scanner.next());

                    System.out.print("Enter Email: ");
                    customer.setEmailid(scanner.next());

                    System.out.print("Enter Address: ");
                    customer.setAddress(scanner.next());

                    System.out.print("Enter Designation: ");
                    customer.setDesignation(scanner.next());

                    System.out.print("Enter PIN: ");
                    customer.setPin(scanner.nextInt());

                    System.out.print("Enter Account Type: ");
                    customer.setTypeofaccount(scanner.next());

                    System.out.print("Enter Initial Amount: ");
                    customer.setAmount(scanner.nextDouble());

                    System.out.print("Enter Gender: ");
                    customer.setGender(scanner.next());

                    if (customerService.registerCustomer(customer)) {
                        System.out.println("Registered Successfully");
                    } else {
                        System.out.println("Registration Failed");
                    }

                } catch (CustomerInvalidDataException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Something went wrong");
                }
                break;
            }

            case 2: {
                try {
                    System.out.println("Customer Login");

                    System.out.print("Enter email or mobile: ");
                    String input = scanner.nextLine();

                    System.out.print("Enter pin: ");
                    int pin = Integer.parseInt(scanner.nextLine());

                    CustomerDetails customer = customerService.customerLogin(input, pin);

                    if (customer != null) {

                        System.out.println("Login Successful");
                        System.out.println("Welcome " + customer.getCustomername());

                        while (true) {

                            System.out.println("\n--- CUSTOMER MENU ---");
                            System.out.println("1. Withdraw");
                            System.out.println("2. Deposit");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Statement");
                            System.out.println("5. Logout");

                            System.out.print("Enter choice: ");
                            int choice = Integer.parseInt(scanner.nextLine());

                            switch (choice) {

                            case 1: {
                                try {
                                    System.out.print("Enter amount to withdraw: ");
                                    double amount = Double.parseDouble(scanner.nextLine());

                                    customerService.withdraw(customer.getAccountnumber(), amount);
                                    System.out.println("Withdrawal Successful");

                                } catch (InsufficientBalanceException e) {
                                    System.out.println(e.getMessage());
                                } catch (CustomerInvalidDataException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Withdrawal Failed");
                                }
                                break;
                            }

                            case 2: {
                                try {
                                    System.out.print("Enter amount to deposit: ");
                                    double amount = Double.parseDouble(scanner.nextLine());

                                    customerService.deposit(customer.getAccountnumber(), amount);
                                    System.out.println("Deposit Successful");

                                } catch (CustomerInvalidDataException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Deposit Failed");
                                }
                                break;
                            }

                            case 3: {
                                double balance = customerService.getBalance(customer.getAccountnumber());
                                System.out.println("Current Balance: " + balance);
                                break;
                            }

                            case 4: {
                                List<TransactionDetails> list = transactionService
                                        .getTransactionByAccNo(customer.getAccountnumber());

                                if (list.isEmpty()) {
                                    System.out.println("No transactions found");
                                } else {
                                    for (TransactionDetails t : list) {
                                        System.out.println(t);
                                    }
                                }
                                break;
                            }

                            case 5: {
                                System.out.println("Logged out successfully");
                                break;
                            }

                            default:
                                System.out.println("Invalid choice");
                            }
                            
                            if(choice==5) {
                            	break;
                            }

                        }

                    } else {
                        System.out.println("Invalid Login Details");
                    }

                } catch (CustomerInvalidDataException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Login Failed");
                }
                break;
            }

            case 3: {
                try {
                    System.out.println("Admin Login");

                    System.out.print("Enter Admin Email: ");
                    String emailid = scanner.next();

                    System.out.print("Enter Admin Password: ");
                    String password = scanner.next();

                    if (adminService.adminLogin(emailid, password)) {

                        while (true) {
                            System.out.println("\n--- ADMIN MENU ---");
                            System.out.println("1. View All Customers");
                            System.out.println("2. Delete Customer");
                            System.out.println("3. Logout");

                            System.out.print("Enter choice: ");
                            int choice = scanner.nextInt();

                            switch (choice) {

                            case 1: {
                                List<CustomerDetails> allCustomers = adminService.getAllCustomers();

                                for (CustomerDetails c : allCustomers) {
                                    System.out.println(c);
                                }
                                break;
                            }

                            case 2: {
                                System.out.print("Enter Account Number to delete: ");
                                long accNo = scanner.nextLong();

                                if (adminService.closeAccount(accNo)) {
                                    System.out.println("Customer Deleted");
                                } else {
                                    System.out.println("Delete Failed");
                                }
                                break;
                            }

                            case 3: {
                                System.out.println("Admin Logout");
                                break;
                            }

                            default:
                                System.out.println("Invalid choice");
                            }
                            if(choice==3) {
                            	break;
                            }
                            
                        }

                    } else {
                        System.out.println("Invalid Admin Credentials");
                    }

                } catch (Exception e) {
                    System.out.println("Admin Login Failed");
                }
                break;
            }

            case 4: {
                System.out.println("Thank you for using JSP Bank");
                scanner.close();
                System.exit(0);
            }

            default:
                System.out.println("Invalid Request");
            }
        }
		
		
	}
}
