/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

import serverController.*;

public class RmiServer {

	String user_name = "//tesla.cs.iupui.edu:2010/userController";
	String cust_name = "//tesla.cs.iupui.edu:2010/customerController";
	String admin_name = "//tesla.cs.iupui.edu:2010/adminController";

	UserController UserController;
	CustomerController CustController;
	AdminController adminController;

	public RmiServer() {
		try {
			UserController = new UserController();
			CustController = new CustomerController();
			adminController = new AdminController();
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		
	}
	
	public void serverBind() {
		try {
				

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

	} //serverBind
	
} // class RmiServer
