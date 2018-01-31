/* Assignment 1
 * 
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

import java.rmi.Naming;
import java.util.Scanner;

public class User {

	
	
	public static void main(String[] args) {
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		Scanner input = new Scanner(System.in);
		int val;

		//User view 
		do {
			System.out.println("\n*****Welcome to Market Place Application*****");
			System.out.println("\nEnter the option from below: ");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.println("Please enter your choice: ");
			
			val = input.nextInt();
			
				switch(val) {
					case 1: login();
							break;
					
					case 2: register();
							break;
				}
			
			}while(val!=3);

	} //main

	public static void login() {
		
		Scanner input = new Scanner(System.in);
		String username, password;
		int result = 0;
		String name;
		
		System.out.print("Enter the username: ");
		username = input.nextLine();
		System.out.print("Enter the password: ");
		password = input.nextLine();
		
		try {
			name = "//tesla.cs.iupui.edu:2010/userController";
			//RMI lookup
			IUserController marketController = (IUserController) Naming.lookup(name);
			result = marketController.userCheck(username, password);
		} 
		catch (Exception e) {
			System.out.println("Something went wrong...");
			e.printStackTrace();
		} 
		
		//Admin credentials
		if(result == 1){
			System.out.println("Administrator Login Successful");
			
			Administrator admin = new Administrator(username);
			admin.adminView();
		}
		//Customer credentials
		else if(result == 2){
			System.out.println("Customer Login Successful");
			
			Customer cust = new Customer(username);
			cust.customerView();
		}
		else 
			System.out.println("Invalid Credential!!");
	} //login
	
	//To register new user
	public static void register() {
		String name;
		int result;
		
		try {
			name = "//tesla.cs.iupui.edu:2010/userController";
			//RMI lookup 
			IUserController marketController = (IUserController) Naming.lookup(name);
			result = marketController.userRegister();
		} 
		catch (Exception e) {
			System.out.println("Something went wrong...");
			e.printStackTrace();
		}
	} //register
	
} //class