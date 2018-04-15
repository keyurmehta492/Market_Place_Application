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

// Ryan: Is this a View? If so it is in violation of tho separate
// the "framework" from the application functionality.

//FIXED: RMI communication is separated from the view. 
//		 Now, request from view is forwarded to commandInvoker using command pattern and then to RMIClient.

public class Customer extends AbstractView {

	private String username;
	private int opt;
	private String opt2;
	int pid, pquantity;

	private CommandInvoker customerCommand;
	private Session session = null;
	private List<itemList> items;

	private Scanner input = new Scanner(System.in);

	public Customer(Session session) {
		this.session = session;
		this.username = session.getUserName();

		customerCommand = new CommandInvoker(session);

	}

	// Implementing abstract method to generate customer view
	@Override
	public void displayView() {
		// Customer view
		do {
			System.out.println("\n*****Welcome " + username + " to MarketPlace *****");
			System.out.println("\nEnter the option from below: ");
			System.out.println("1. Browse for Products");
			System.out.println("2. Add product to Shopping cart");
			System.out.println("3. View Shopping Cart and Purchase product(s)");
			System.out.println("4. Log out");
			System.out.println("Please enter your choice: ");

			opt = input.nextInt();

			switch (opt) {
			case 1:
				browseProduct();
				break;

			case 2:
				shoppingCart();
				break;

			case 3:
				viewShoppingCart();
				break;
			}

		} while (opt != 4);

		System.out.println("Thank you for using MarketPlace Application! Happy Shopping !!");

	}// displayView

	// Customer browses the product
	public int browseProduct() {
		try {
			items = customerCommand.sendCCommand("browse", "0");

			System.out.println("***********The Product List***********");
			System.out.println("ItemID\tName\t\tDescription\t\tType\t\tPrice\tQuantity available");
			System.out.println("=================================================================================");

			// Display available product details with proper format
			for (itemList value : items) {
				if (value.getItemID() != 0 && value.getItemQuantity() != 0) {
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

	// Add product to the shopping cart
	public int shoppingCart() {
		try {
			System.out.println("***********Add Product to Shopping Cart***********");

			// get the details from user to add product to the shopping cart
			System.out.println("Enter product ID: ");
			pid = input.nextInt();
			System.out.println("Enter quantity of product: ");
			pquantity = input.nextInt();

			items = customerCommand.sendCCommand("shoppingCart", pid + "," + pquantity);

			// if product quantity is greater than the available quantity
			if (items.get(0).getMessage().equals("-2")) {
				System.out.println("Quantity ordered for product id: " + pid
						+ " is greater than the available Quantity!\n" + "Kindly recheck the quantity!!");
			}

			// product is NOT added to the shopping cart
			else if (items.get(0).getMessage().equals("0"))
				System.out.println("Product NOT added to the customer's shopping cart!!");

			// product is added to the shopping cart
			else if (items.get(0).getMessage().equals("1"))
				System.out.println("Product Added to the Shopping cart!!");

			// invalid product id is entered by the user
			else if (items.get(0).getMessage().equals("-1"))
				System.out.println("Invalid product ID!! Kindly check the product ID again.");

			// If same product is already present in the shopping cart then
			// update the quantity
			else if (items.get(0).getMessage().equals("2")) {
				System.out.println(
						"Product's quantity is updated as product is already present in the customer's shopping cart!!");
			}

		} catch (AuthorizationException ex) {
			// catch user define exception in case of user role is not
			// authorized to access this operation
			System.out.println(ex.getMessage());
		}
		return 0;
	}// shoppingCart

	// To view customers shopping cart
	public int viewShoppingCart() {
		try {
			items = customerCommand.sendCCommand("viewShoppingCart", "0");

			System.out.println("***********The Product List***********");
			System.out.println("ProductID\tName\t\tQuantity Ordered\tAmount");
			System.out.println("===============================================================");

			// Display available product details with proper format
			for (itemList value : items) {
				if (value.getItemID() != 0) {
					System.out.printf("%-6d\t\t%-15s\t%-3d\t\t\t%-6.2f\n", value.getItemID(), value.getItemName(),
							value.getItemQuantity(), value.getItemPrize());
				}
			}
			System.out.println("===============================================================");

			// Give purchase option only when cart is not empty
			if (items.size() > 1) {

				System.out.println("\nKindly confirm, Do you want to purchase all prodcuts in shopping cart (Y/N)?");
				opt2 = input.next();

				// if customer gives confirmation to purchase the product
				if (opt2.equalsIgnoreCase("Y")) {
					purchaseCart();
				}

				// customer declines to purchase the product
				else
					System.out.println("Customer decline to Purchase the products.");
			}
		} catch (AuthorizationException ex) {
			// catch user define exception in case of user role is not
			// authorized to access this operation
			System.out.println(ex.getMessage());
		}

		return 0;
	}// viewShoppingCart

	// user wants to purchase the product added in the shopping cart
	public void purchaseCart() {

		items = customerCommand.sendCCommand("purchaseProduct", "0");

		// Display order detail to the user when order is placed.
		System.out.println("\n\n***********Order Detail***********");
		System.out.println(items.get(0).getMessage());
		System.out.println("===========================================================");
		for (int counter = 1; counter < items.size(); counter++) {
			System.out.println(items.get(counter).getMessage());
		}

	} // purchaseCart

} // class Customer
