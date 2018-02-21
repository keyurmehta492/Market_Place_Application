/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package view;

import java.util.Scanner;

import abstractFactory.AbstractView;
import command.CommandInvoker;


// Ryan: Is this a View? If so it is in violation of the so separate
// the "framework" from the application functionality.

//FIXED: RMI communication is separated from the view. 
//Now, request from view is forwarded to commandInvoker using command pattern and then to RMIClient.

public class Administrator extends AbstractView{

	private String username;
	private int opt;
	
	
	CommandInvoker admincommand;
	Scanner input = new Scanner(System.in);

	Administrator() {
		
	}
	
	public Administrator(String username) {
		this.username= username;
		try {
			admincommand = new CommandInvoker();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//implementing abstract method to generate admin view
	@Override
	public void displayView() {
		//Admin View
		do {
			System.out.println("\n*****Welcome " + username + " to MarketPlace *****");
			System.out.println("\nEnter the option from below: ");
			System.out.println("1. Browse for Products");
			System.out.println("2. Add a Products");
			System.out.println("3. Update the Products");
			System.out.println("4. Remove the Products");
			System.out.println("5. Log out");
			System.out.println("Please enter your choice: ");
			
			opt = input.nextInt();
			
				switch(opt) {
					case 1: browseProduct();
							break;
					case 2: addProduct();
							break;
					case 3: updateProduct();
							break;
					case 4: deleteProduct();
							break;
				}
			
			}while(opt!=5);
		
		System.out.println("Thank you for using MarketPlace Application!!");
		
	}
	
	//for Admin to browse the product
	public int browseProduct() {
			
			admincommand.sendACommand("browse");
			
		return 0;
	}
	
	//for Admin to add the product
	public int addProduct() {
		admincommand.sendACommand("add");
		return 0;
	}
	
	//for Admin to update the product
	public int updateProduct() {
		admincommand.sendACommand("update");
		return 0;
	}
	
	//for Admin to delete the product
	public int deleteProduct() {
		admincommand.sendACommand("delete");
		return 0;
	}

} // class Administrator
