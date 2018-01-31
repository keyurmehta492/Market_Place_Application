/* Assignment 1
 *  
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Administrator {

	private String username;
	private int opt,result;
	String name = "//tesla.cs.iupui.edu:2010/adminController";
	
	IAdminController adminController ;
	
	Scanner input = new Scanner(System.in);

	Administrator(String username) {
		//RMI lookup
		this.username= username;
		try {
			this.adminController = (IAdminController) Naming.lookup(this.name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int adminView() {
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
		return 0;
	}
	
	//for Admin to browse the product
	public int browseProduct() {
		try {
			result = adminController.adminBrowseProd();
		} catch (RemoteException e) {
			System.out.println("Something went wrong in admin Browse Product...");
			e.printStackTrace();
		}
		return 0;
	}
	
	//for Admin to add the product
	public int addProduct() {
		try {
			result = adminController.adminAddProd();
		} catch (RemoteException e) {
			System.out.println("Something went wrong in admin add Product...");
			e.printStackTrace();
		}
		return 0;
	}
	
	//for Admin to update the product
	public int updateProduct() {
		try {
			result = adminController.adminUpdateProd();
		} catch (RemoteException e) {
			System.out.println("Something went wrong in admin Update Product...");
			e.printStackTrace();
		}
		return 0;
	}
	
	//for Admin to delete the product
	public int deleteProduct() {
		try {
			result = adminController.adminDeleteProd();
		} catch (RemoteException e) {
			System.out.println("Something went wrong in admin Delete Product...");
			e.printStackTrace();
		}
		return 0;
	}

} // class Administrator
