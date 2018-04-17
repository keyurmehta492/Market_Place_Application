/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package view;

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

public class Administrator extends AbstractView {

	private String name, username, password, address;
	private int opt, opt3;
	private Session session = null;

	private List<itemList> items;
	private CommandInvoker admincommand;
	private Scanner input = new Scanner(System.in);
	private int pid, pQuantity;
	private String pName, pDes, pType, opt2, info, new_detail, user_name;
	private Double pPrize;

	Administrator() {

	}

	public Administrator(Session session) {
		this.username = session.getUserName();
		this.session = session;

		try {
			admincommand = new CommandInvoker(session);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// implementing abstract method to generate admin view
	@Override
	public void displayView() {
		// Admin View
		do {
			System.out.println("\n*****Welcome " + username + " to MarketPlace *****");
			System.out.println("\nEnter the option from below: ");
			System.out.println("1. Browse for Products");
			System.out.println("2. Add a Product");
			System.out.println("3. Update the Product");
			System.out.println("4. Delete a Product");
			System.out.println("5. Add Administrator");
			System.out.println("6. Add new customer");
			System.out.println("7. Remove a customer");
			System.out.println("8. Log out");
			System.out.println("Please enter your choice: ");

			opt = input.nextInt();
			input.nextLine();
			switch (opt) {
			case 1:
				browseProduct();
				break;
			case 2:
				addProduct();
				break;
			case 3:
				updateProduct();
				break;
			case 4:
				deleteProduct();
				break;
			case 5:
				addAdmin();
				break;
			case 6:
				addCustomer();
				break;
			case 7:
				removeCustomer();
				break;
			}// switch

		} while (opt != 8);

		System.out.println("Thank you for using MarketPlace Application!!");

	}// displayView

	// for Admin to browse the product
	public int browseProduct() {
		try {
			items = admincommand.sendACommand("browse", "0");

			System.out.println("***********The Product List***********");
			System.out.println("ItemID\tName\t\tDescription\t\tType\t\tPrice\tQuantity available");
			System.out.println("=================================================================================");

			// Display all the product details with proper format
			for (itemList value : items) {
				if (value.getItemID() != 0) {
					System.out.printf("%-6d\t%-15s\t%-20s\t%-15s\t%-6.2f\t%-3d\n", value.getItemID(),
							value.getItemName(), value.getItemDesc(), value.getItemType(), value.getItemPrize(),
							value.getItemQuantity());
				}
			}
			System.out.println("=================================================================================");
		} catch (AuthorizationException ex) {
			// catch user define exception in case of user role is not
			// authorized to access this operation
			System.out.println(ex.getMessage());
		}
		return 0;
	}// browseProduct

	// for Admin to add the product
	public int addProduct() {
		try {

			// get the details from admin about new product
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
			System.out.println("Enter price of the product: ");
			pPrize = input.nextDouble();
			System.out.println("Enter quantity of the product: ");
			pQuantity = input.nextInt();
			input.nextLine();

			System.out.println("Details of the new product:");
			System.out.printf("%-6d\t%-15s\t%-20s\t%-15s\t%-6.2f\t%-3d\n", pid, pName, pDes, pType, pPrize, pQuantity);

			// get confirmation from admin to add the product to the product
			// list
			System.out.println("Kindly confirm, all information is correct and ADD product to the productList (Y/N)?");
			opt2 = input.next();

			// if admin gives confirmation to add the product
			if (opt2.equalsIgnoreCase("Y")) {
				info = pid + "," + pName + "," + pDes + "," + pType + "," + pPrize + "," + pQuantity;
				items = admincommand.sendACommand("add", info);

				// if all the product details are not filled properly
				if (items.get(0).getMessage().equals("-2"))
					System.out.println("Please enter all the fields properly!!");

				// if product added to the list
				else if (items.get(0).getMessage().equals("1"))
					System.out.println("New Product is added to the Product List!!");

				// if product can not be added to the list as product id is
				// already present
				else if (items.get(0).getMessage().equals("-1"))
					System.out.println(
							"New Product can NOT be added to the Product List as same Product ID is present in the list!!");

				// if product is not added to the list
				else if (items.get(0).getMessage().equals("0"))
					System.out.println("New Product can NOT be added to the Product List!!");
			}

			// if admin decline to add the product
			else
				System.out.println("Admin decline to add New product to the productList.");

		} catch (AuthorizationException ex) {
			// catch user define exception in case of user role is not
			// authorized to access this operation
			System.out.println(ex.getMessage());
		}

		return 0;
	}// addProduct

	// for Admin to update the product
	public int updateProduct() {

		System.out.println("***********Update Product Details from Product List***********");
		// Get the product id which needs to be updated
		System.out.print("Enter product ID: ");
		pid = input.nextInt();
		input.nextLine();

		// Get what detail of the product needs to be updated
		System.out.println("\nEnter which detail of the product: " + pid + " wants to update:");
		System.out.println("1. Description \t 2. Price \t 3. Quanitity");
		opt3 = input.nextInt();
		input.nextLine();

		// check if valid option is selected
		if (opt3 >= 1 && opt3 <= 3) {
			// get new description
			if (opt3 == 1) {
				System.out.print("Enter new description of the product: ");
				new_detail = input.nextLine();
			}

			// get new prize
			else if (opt3 == 2) {
				System.out.print("Enter new price of the product: ");
				new_detail = input.nextLine();
			}

			// get new quantity
			else if (opt3 == 3) {
				System.out.print("Enter new quantity of the product: ");
				new_detail = input.nextLine();
			}

			info = pid + "," + opt3 + "," + new_detail;

			// get confirmation from admin to update the product to the product
			// list
			System.out.print("Update ");
			if (opt3 == 1)
				System.out.print("description");
			else if (opt3 == 2)
				System.out.print("prize");
			else if (opt3 == 3)
				System.out.print("quantity");
			System.out.print(" of the product id: " + pid + " to " + new_detail);

			System.out.println(
					"\n\nKindly confirm, all information is correct and Update the mentioned product detail (Y/N)?");
			opt2 = input.next();

			// if admin gives confirmation to update the product details
			if (opt2.equalsIgnoreCase("Y")) {
				try {
					items = admincommand.sendACommand("update", info);

					// if product is updated in the list
					if (items.get(0).getMessage().equals("1"))
						System.out.println("Product detail is updated in the Product List!!");

					// if product is not updated in the list as product id is
					// not present in the list
					else if (items.get(0).getMessage().equals("-1"))
						System.out.println(
								"Product details can NOT be updated as Product ID is NOT present in the list!!");

					// if product is not updated in the list
					else if (items.get(0).getMessage().equals("0"))
						System.out.println("Product detail can NOT be updated!!");

				} catch (AuthorizationException ex) {
					// catch user define exception in case of user role is not
					// authorized to access this operation
					System.out.println(ex.getMessage());
				}
			}
			// if admin decline to add the product
			else
				System.out.println("Admin decline to update the product of the productList.");

		} else
			System.out.println("Please enter valid option!!");
		return 0;
	}// updateProduct

	// for Admin to delete the product
	public int deleteProduct() {
		System.out.println("***********Delete Product from Product List***********");
		
		//Get the id of the product which needs to be deleted
		System.out.println("Enter product ID: ");
		pid = input.nextInt();
		input.nextLine();

		// get confirmation from admin to delete the product to the product list
		System.out.println("Kindly confirm, Do you want to permanently delete the product id: " + pid
				+ " from product list (Y/N)?");
		opt2 = input.next();

		//if admin gives confirmation to delete the product
		if (opt2.equalsIgnoreCase("Y")) {
			try {
				info = pid + "";
				items = admincommand.sendACommand("delete", info);

				// if product is deleted to the list
				if (items.get(0).getMessage().equals("1"))
					System.out.println("Product is deleted from the Product List!!");

				// if product is not deleted from the list as product id is not present 
				else if (items.get(0).getMessage().equals("-1"))
					System.out.println(
							"Product can NOT be deleted from the Product List as same Product ID is NOT present in the list!!");

				// if product is not deleted from the list 
				else if (items.get(0).getMessage().equals("0"))
					System.out.println("Product can NOT be deleted from the Product List!!");

			} catch (AuthorizationException ex) {
				// catch user define exception in case of user role is not
				// authorized to access this operation
				System.out.println(ex.getMessage());
			}
		}
		
		// if admin decline to add the product
		else
			System.out.println("Admin decline to remove the Product from the productList.");
		
		return 0;
	}// deleteProduct

	//Add new administrator for the application
	public int addAdmin() {
		
		System.out.println("***********Add New Admin***********");
		// take user information as input from user
		System.out.print("Enter the Name: ");
		name = input.nextLine();
		System.out.print("Enter the username: ");
		user_name = input.nextLine();
		System.out.print("Enter the password: ");
		password = input.nextLine();
		System.out.print("Enter the address: ");
		address = input.nextLine();

		info = name + "," + user_name + "," + password + "," + address;
		try {
			items = admincommand.sendACommand("addadmin", info);
			
			// if all the user details are not filled properly
			if (items.get(0).getMessage().equals("-2")) 
				System.out.println("Please enter all the fields properly!!");
			
			// if new admin can not be added as username is already present
			else if (items.get(0).getMessage().equals("-1")) 
				System.out.println("Username already present. Please use another username!!");
			
			// if new admin is added in user list
			else if (items.get(0).getMessage().equals("1")) {
				System.out.println("Admin: " + name + " is registered/added successfully!!");
				System.out.println("Please share the credentials with admin to login.");
			} 
			
			// if new admin can not be added 
			else if (items.get(0).getMessage().equals("0")) 
				System.out.println("New Admin can not be added. Please try again!!");
			

		} catch (AuthorizationException ex) {
			// catch user define exception in case of user role is not
			// authorized to access this operation
			System.out.println(ex.getMessage());
		}
		
		return 0;
	} // addAdmin

	//TO add new customer in the user list
	public int addCustomer() {
		System.out.println("***********Add New Customer***********");
		// take user information as input from user
		System.out.print("Enter the Name: ");
		name = input.nextLine();
		System.out.print("Enter the username: ");
		user_name = input.nextLine();
		System.out.print("Enter the password: ");
		password = input.nextLine();
		System.out.print("Enter the address: ");
		address = input.nextLine();

		info = name + "," + user_name + "," + password + "," + address;
		try {
			items = admincommand.sendACommand("addcustomer", info);
			
			// if all the user details are not filled properly
			if (items.get(0).getMessage().equals("-2")) 
				System.out.println("Please enter all the fields properly!!");
			
			// if new customer can not be added as username is already present
			else if (items.get(0).getMessage().equals("-1")) 
				System.out.println("Username already present. Please use another username!!");
			
			// if new customer is added in user list
			else if (items.get(0).getMessage().equals("1")) {
				System.out.println("Customer: " + name + " is registered/added successfully!!");
				System.out.println("Please share the credentials with customer to login.");
			} 
			
			// if new customer can not be added in user list
			else if (items.get(0).getMessage().equals("0")) 
				System.out.println("New customer can not be added. Please try again!!");
			

		} catch (AuthorizationException ex) {
			// catch user define exception in case of user role is not
			// authorized to access this operation
			System.out.println(ex.getMessage());
		}
		
		return 0;
	}// addCustomer

	//TO remove  customer from the user list
	public int removeCustomer() {
		System.out.println("***********Delete Customer from User List***********");
		
		//get the customer username
		System.out.println("Enter username of the customer: ");
		user_name = input.nextLine();

		// get confirmation from admin to delete the product to the product list
		System.out.println("Kindly confirm, Do you want to permanently delete the customer user: " + user_name
				+ " from user list (Y/N)?");
		opt2 = input.next();

		//if admin gives confirmation to remove the customer
		if (opt2.equalsIgnoreCase("Y")) {
			try {

				items = admincommand.sendACommand("removecustomer", user_name);

				// if customer is deleted from the list
				if (items.get(0).getMessage().equals("1"))
					System.out.println("Customer user is deleted from the User List!!");

				// if customer can not be removed from the list as username is not present in the list
				else if (items.get(0).getMessage().equals("-1"))
					System.out.println(
							"Customer user can NOT be deleted from the User List as username is NOT present in the list!!");
				
				// if customer can not be removed from the list
				else if (items.get(0).getMessage().equals("0"))
					System.out.println("Customer user can NOT be deleted from the User List!!");

			} catch (AuthorizationException ex) {
				// catch user define exception in case of user role is not
				// authorized to access this operation
				System.out.println(ex.getMessage());
			}
		}
		
		// if admin decline to add the product
		else
			System.out.println("Admin decline to remove the Customer user from the user List.");
		
		return 0;
	}// removeCustomer

} // class Administrator
