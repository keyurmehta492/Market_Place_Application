/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package view;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import abstractFactory.AbstractView;
import command.CommandInvoker;
import server.AuthorizationException;
import server.Session;
import server.itemList;


// Ryan: Is this a View? If so it is in violation of tho separate
// the "framework" from the application functionality.

//FIXED: RMI communication is separated from the view. 
//		 Now, request from view is forwarded to commandInvoker using command pattern and then to RMIClient.

public class Customer extends AbstractView{

	private String username;
	private int opt;
	String opt2;
	int pid, pquantity;
	
	CommandInvoker customerCommand;
	Session session = null;
	List<itemList> items = new ArrayList<itemList>() ;
	
	
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
			System.out.println("2. Add product to Shopping cart");
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
			items = customerCommand.sendCCommand("browse", "0");
			
			System.out.println("***********The Product List***********");
			System.out.println("ItemID\tName\t\tDescription\t\tType\t\tPrize\tQuantity available");
			System.out.println("=================================================================================");
			
			for (itemList value : items) {
				 if(value.getItemID() != 0 && value.getItemQuantity() != 0) {
					 System.out.printf("%-6d\t%-15s\t%-20s\t%-15s\t%-6.2f\t%-3d\n"
							 ,value.getItemID(),value.getItemName(),value.getItemDesc()
							 ,value.getItemType(),value.getItemPrize(),value.getItemQuantity()); 
				 }  
		    }
			System.out.println("================================================================================="); 
		}
		catch (AuthorizationException ex) {
			//catch user define exception in case of user role is not authorized to access this operation
			System.out.println(ex.getMessage());
		}
					
		return 0;
	}
	
	//Add product to the shopping cart
	public int shoppingCart() {
		try {
			System.out.println("***********Add Product to Shopping Cart***********");
			System.out.println("Enter product ID: ");
			pid = input.nextInt();
			System.out.println("Enter quantity of product: ");
			pquantity = input.nextInt();
			
			items = customerCommand.sendCCommand("shoppingCart",pid+","+pquantity);
			if(items.get(0).getMessage().equals("0"))
				System.out.println("Quantity ordered for product id: " + pid + " is greater than the available Quantity!\n"
						+ "Kindly recheck the quantity!!");
			else if(items.get(0).getMessage().equals("1")) {
				System.out.println("Product Added to the Shopping cart!!");
				
				System.out.println("\n\nDo you want to purchase the added product (Y/N) ?");
				opt2 = input.next();
				if (opt2.equalsIgnoreCase("Y"))
					purchaseCart(pid,pquantity);
				else
					System.out.println("Product removed from cart! Returning to main menu.");
			}
				
			
			
		}
		catch (AuthorizationException ex) {
			//catch user define exception in case of user role is not authorized to access this operation
			System.out.println(ex.getMessage());
		}
		return 0;
	}//shoppingCart

	public void purchaseCart(int pid, int pquantity) {
		
		items = customerCommand.sendCCommand("purchaseProduct",pid+","+pquantity);
		if(items.get(0).getMessage().equals("0"))
			System.out.println("Quantity ordered for product id: " + pid + " is greater than the available Quantity!\n"
					+ "Kindly recheck the quantity!!");
		else if(items.get(0).getMessage().equals("1")) 
			System.out.println("\nOrder Placed!! Product will be delivered to your door step!!");
		
	} //purchaseCart
	
} //class Customer
