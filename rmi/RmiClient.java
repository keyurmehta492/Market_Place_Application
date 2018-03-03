/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

import interfaces.IAdminController;
import interfaces.ICustomerController;
import interfaces.IUserController;
import server.Session;

public class RmiClient {

	String userStr = "//tesla.cs.iupui.edu:2010/userController";
	String adminStr = "//tesla.cs.iupui.edu:2010/adminController";
	String custStr = "//tesla.cs.iupui.edu:2010/customerController";
	int userType;
	
	
	IUserController userController;
	IAdminController adminController;
	ICustomerController custController;
	Session session = null;
	
	public RmiClient() {
		try {
			
			//lookup to connect to the server
			userController = (IUserController) Naming.lookup(userStr);
			adminController = (IAdminController) Naming.lookup(adminStr);
			custController = (ICustomerController) Naming.lookup(custStr);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//send the user credentials to authenticate
	public Session sendRequest(String username, String password) {
		try {
			session = userController.userCheck(username, password);
					
		} 
		catch (Exception e) {
			System.out.println("Something went wrong in login connection...");
			e.printStackTrace();
		}
		
		return session;
	}//sendRequest
	
	//send admin commands  to perform the operation related to Admin user
	public Session sendAdminRequest(String command, Session session) {
		
		try {
			switch(command) {
			
			//send request to browse product for admin user
			case "browse":
				session = adminController.adminBrowseProd(session);
				break;
			
			//send request to add product for admin user
			case "add":
				session = adminController.adminAddProd(session);
				break;
			
			//send request to update product for admin user
			case "update":
				session = adminController.adminUpdateProd(session);
				break;
			
			//send request to delete product for admin user
			case "delete":
				session = adminController.adminDeleteProd(session);
				break;
			
			}//switch
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return session;
	} //sendAdminRequest
	
	//send customer commands  to perform the operation related to Customer user
	public Session sendCustomerRequest(String command, Session session) {
		
		try {
			switch(command) {
		
			//send request to browse product for customer user
			case "browse":
				session = custController.custBrowseProd(session);
				break;
		
			//send request to check shopping cart for customer user
			case "shoppingCart":
				session = custController.custShoppingCart(session);
				break;
			
			}// switch
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return session;
	}//sendCustomerRequest
	
} //class RmiClient
