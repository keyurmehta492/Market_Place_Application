/* Assignment 1
 *  
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MarketPlaceServer extends UnicastRemoteObject {

	private String name;
	
	public MarketPlaceServer(String name) throws RemoteException {
		this.name = name;
	}

	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
			
		try {
			
			String user_name = "//tesla.cs.iupui.edu:2010/userController";
			String cust_name = "//tesla.cs.iupui.edu:2010/customerController";
			String admin_name = "//tesla.cs.iupui.edu:2010/adminController";
			
			System.out.println("Market Place Server is starting...");
			UserController UserController = new UserController();
			CustomerController CustController = new CustomerController();
			AdminController adminController = new AdminController();

			// Binding to the RMI Service.
			System.out.println("MarketPlaceServer: binding it to name: " + user_name);
			Naming.rebind(user_name, UserController);
			
			System.out.println("MarketPlaceServer: binding it to name: " + cust_name);
			Naming.rebind(cust_name, CustController);
			
			System.out.println("MarketPlaceServer: binding it to name: " + admin_name);
			Naming.rebind(admin_name, adminController);
			
			System.out.println("Binding Done!! Welcome to MarketPlace Server ******");
		} 
		
		catch (Exception ex) {
			ex.printStackTrace();
		}	
	
	} //main

	
	
} //class MarketPlaceSever