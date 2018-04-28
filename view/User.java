/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package view;

import java.util.Scanner;

import clientController.FrontController;

public class User {

	private String name, username, password, cf_password, address;
	private int result = 0;
	private Scanner input = new Scanner(System.in);

	// Ryan: Is this a View? If so it is in violation of the separation of
	// concern lecture. You would need to separate
	// the "framework" from the application functionality.

	// FIXED: RMI communication is separated from the view.
	// Now, request from view is forwarded to front controller and then to
	// RMIClient.

	private FrontController fc;

	public User(FrontController fc) {
		this.fc = fc;
	}

	// to generate user view
	public void userView() {

		input = new Scanner(System.in);
		String val;

		// User view

		do {
			System.out.println("\n*****Welcome to Market Place Application*****");
			System.out.println("\nEnter the option from below: ");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.println("Please enter your choice: ");

			val = input.nextLine();

			switch (val) {
			case "1":
				login();
				break;

			case "2":
				register();
				break;

			case "3":
				System.exit(0);
				
			default:
				System.out.println("Enter valid option!!");

			}// switch

		} while (!val.equals("3"));

	} // userView

	public void login() {

		System.out.println("Login:-");
		// take user credentials as input from user
		System.out.print("Enter the username: ");
		username = input.nextLine();
		System.out.print("Enter the password: ");
		password = input.nextLine();

		// pass to front controller to verify basic check and authentication
		result = fc.signIn(username, password);

		// if user is not authenticated or credentials are not entered then ask
		// user again
		if (result == 0) {
			System.out.println("Username or Password are Incorrect!!");
		}

	} // login

	// To register new user
	public void register() {

		System.out.println("Register:-");
		// take user information as input from user
		System.out.print("Enter the Name: ");
		name = input.nextLine();
		System.out.print("Enter the username: ");
		username = input.nextLine();
		System.out.print("Enter the password: ");
		password = input.nextLine();
		System.out.print("Please confirm the password: ");
		cf_password = input.nextLine();
		System.out.print("Enter the address: ");
		address = input.nextLine();

		result = fc.signUp(name, username, password, cf_password, address);

		// If all details are not entered
		if (result == 0)
			System.out.println("Please enter all the fields properly!!");

		// if new user can not register as username is already occupied
		else if (result == -1)
			System.out.println("Username already present. Please use another username!!");

		// if new user can not register as password did not match
		else if (result == -2)
			System.out.println("Password did not match. Please enter details again!!");

		// if new user can registered successfully
		else if (result == 1) {
			System.out.println("User: " + name + " is registered successfully!!");
			System.out.println("Please login again to use Market Place Application.");
		}

	} // register

} // class User