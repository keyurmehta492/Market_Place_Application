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


// Ryan: Is this a View? If so it is in violation of the so separate
// the "framework" from the application functionality.

//FIXED: RMI communication is separated from the view. 
//Now, request from view is forwarded to commandInvoker using command pattern and then to RMIClient.

public class Administrator extends AbstractView{

	private String username;
	private int opt;
	Session session = null;
	
	List<itemList> items = new ArrayList<itemList>();
	CommandInvoker admincommand;
	Scanner input = new Scanner(System.in);
	int pid,pQuantity;
	String pName, pDes, pType,opt2,info;
	Double pPrize;
	
	Administrator() {
		
	}
	
	public Administrator(Session session) {
		this.username= session.getUserName();
		this.session = session;
		
		try {
			admincommand = new CommandInvoker(session);
			
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
				}//switch
			
			}while(opt!=5);
		
		System.out.println("Thank you for using MarketPlace Application!!");
		
	}
	
	//for Admin to browse the product
	public int browseProduct() {
		try {
			items = admincommand.sendACommand("browse","0");
			
			System.out.println("***********The Product List***********");
			System.out.println("ItemID\tName\t\tDescription\t\tType\t\tPrize\tQuantity available");
			System.out.println("=================================================================================");
			
			for (itemList value : items) {
				 if(value.getItemID() != 0) {
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
	
	//for Admin to add the product
	public int addProduct() {
		try {
			System.out.println("***********Add Product to Product List***********");
			System.out.println("Enter product ID: ");
			pid = input.nextInt();
			input.nextLine();
			System.out.println("Enter name of the product: ");
			pName = input.nextLine();
			System.out.println("Enter Description of the product: ");
			pDes = input.nextLine();
			System.out.println("Enter Type of the product: ");
			pType = input.nextLine();
			System.out.println("Enter quantity of the product: ");
			pQuantity = input.nextInt();
			input.nextLine();
			System.out.println("Enter prize of the product: ");
			pPrize = input.nextDouble();
			
			System.out.println("Details of the new product:");
			System.out.printf("%-6d\t%-15s\t%-20s\t%-15s\t%-6.2f\t%-3d\n"
					 ,pid,pName,pDes,pType,pPrize,pQuantity);
			System.out.println("Kindly confirm, all information is correct and ADD product to the productList (Y/N)?");
			opt2 = input.next();
			if (opt2.equalsIgnoreCase("Y")) {
				info = pid +","+pName+","+pDes+","+pType+","+pQuantity+","+pPrize;
				items = admincommand.sendACommand("add",info);		
				if(items.get(0).getMessage().equals("1"))
					System.out.println("New Product is added to the Product List!!");
				else if(items.get(0).getMessage().equals("0"))
					System.out.println("New Product can NOT be added to the Product List as same Product ID is present in the list!!");
			}
	
			else
				System.out.println("New product is not added to the productList.");
			
			
		}
		catch (AuthorizationException ex) {
			//catch user define exception in case of user role is not authorized to access this operation
			System.out.println(ex.getMessage());
		}
		
		return 0;
	}
	
	//for Admin to update the product
	public int updateProduct() {
		try {
			items = admincommand.sendACommand("update","0");
		}
		catch (AuthorizationException ex) {
			//catch user define exception in case of user role is not authorized to access this operation
			System.out.println(ex.getMessage());
		}
		return 0;
	}
	
	//for Admin to delete the product
	public int deleteProduct() {
		try {
			items = admincommand.sendACommand("delete","0");
		}
		catch (AuthorizationException ex) {
			//catch user define exception in case of user role is not authorized to access this operation
			System.out.println(ex.getMessage());
		}
		
		return 0;
	}

} // class Administrator
