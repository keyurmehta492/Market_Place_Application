/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package view;


import java.util.Scanner;

import abstractFactory.AbstractView;
import command.CommandInvoker;
import server.AuthorizationException;
import server.Session;


// Ryan: Is this a View? If so it is in violation of tho separate
// the "framework" from the application functionality.

//FIXED: RMI communication is separated from the view. 
//		 Now, request from view is forwarded to commandInvoker using command pattern and then to RMIClient.

public class Customer extends AbstractView{

	private String username;
	private int opt;
	
	CommandInvoker customerCommand;
	Session session = null;
	
	Scanner input = new Scanner(System.in);
	
	public Customer(Session session) {
		this.session = session;
		this.username= session.getUserName();
				
		customerCommand = new CommandInvoker(session);
		
	}
	
	//Implementing abstract method to generate customer view
	@Override
	public void displayView() {
		//Customer view
		do {
			System.out.println("\n*****Welcome " + username + " to MarketPlace *****");
			System.out.println("\nEnter the option from below: ");
			System.out.println("1. Browse for Products");
			System.out.println("2. Shopping Cart");
			System.out.println("3. Log out");
			System.out.println("Please enter your choice: ");
			
			opt = input.nextInt();
			
				switch(opt) {
					case 1: browseProduct();
							break;
					
					case 2: shoppingCart();
							break;
				}
			
			}while(opt!=3);
		
			System.out.println("Thank you for using MarketPlace Application! Happy Shopping !!");
			
	}
	
	//Customer browses the product
	public int browseProduct() {
		try {
			session = customerCommand.sendCCommand("browse");
		}
		catch (AuthorizationException ex) {
			//catch user define exception in case of user role is not authorized to access this operation
			System.out.println(ex.getMessage());
		}
		return 0;
	}
	
	//display customers shopping cart
	public int shoppingCart() {
		try {
			session = customerCommand.sendCCommand("shoppingCart");
		}
		catch (AuthorizationException ex) {
			//catch user define exception in case of user role is not authorized to access this operation
			System.out.println(ex.getMessage());
		}
		return 0;
	}

} //class Customer
