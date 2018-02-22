/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package view;

import java.util.Scanner;

import clientController.FrontController;



public class User {

	// Ryan: Is this a View? If so it is in violation of the separation of
	// concern lecture. You would need to separate
	// the "framework" from the application functionality.
	
	//FIXED: RMI communication is separated from the view. 
	//		 Now, request from view is forwarded to front controller and then to RMIClient.
	
	FrontController fc;
	private Scanner input;
	
	public User(FrontController fc){
		this.fc = fc;
	}
	

	//to generate user view
	public void userView() {
		
		input = new Scanner(System.in);
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

	} //userView

	public void login() {
		
		String username;
		String password;
		int result = 0;
		input= new Scanner(System.in);
				
		System.out.print("Enter the username: ");
		username = input.nextLine();
		System.out.print("Enter the password: ");
		password = input.nextLine();
		// pass to front controller to verify basic check and authentication
		
		result = fc.signIn(username, password);
		
		if(result == 0) {
			System.out.println("Username or Password are Incorrect!!");
		}
		
	} //login
	
	
	//To register new user
	public static void register() {
			//functionality is not implemented yet
	} //register
	
} //class User