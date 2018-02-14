/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package view;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import abstractFactory.*;
import command.*;

// Ryan: Is this a View? If so it is in violation of tho separate
// the "framework" from the application functionality.

//FIXED: RMI communication is separated from the view. 
//		 Now, request from view is forwarded to commandInvoker using command pattern and then to RMIClient.

public class Customer {

	private String username;
	private int opt, result;
	
	CommandInvoker customerCommand;
	
	
	Scanner input = new Scanner(System.in);
	
	public Customer(String username) {
		
		this.username= username;
		customerCommand = new CommandInvoker();
		
	}
	
	public int customerView() {
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
			return 0;
	}
	
	//Customer browses the product
	public int browseProduct() {
			
			//customerCommand.sendCCommand("browse");
			
			AbstractFact fact = FactoryDecider.getFact("Customer");
			fact.getBrowseC("Customer", customerCommand);	
			return 0;
	}
	
	//display customers shopping cart
	public int shoppingCart() {
		customerCommand.sendCCommand("shoppingCart");
		return 0;
	}

} //class Customer
