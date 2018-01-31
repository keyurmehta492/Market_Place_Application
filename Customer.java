/* Assignment 1
 *  
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Customer {

	private String username;
	private int opt, result;
	String name = "//tesla.cs.iupui.edu:2010/customerController";
	
	ICustomerController custController ;
	
	Scanner input = new Scanner(System.in);
	
	Customer(String username) {
		//lookup the RMI connection
		this.username= username;
		try {
			this.custController = (ICustomerController) Naming.lookup(this.name);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			
			try {
				result = custController.custBrowseProd();
			} catch (RemoteException e) {
				System.out.println("Something went wrong in customerBrowse Product...");
				e.printStackTrace();
			}
			return 0;
	}
	
	//display customers shopping cart
	public int shoppingCart() {
		
		try {
			
			result = custController.custShoppingCart();
		} 
		catch (Exception e) {
			System.out.println("Something went wrong in customer shopping Cart...");
			e.printStackTrace();
		}
		return 0;
	}

} //class Customer
