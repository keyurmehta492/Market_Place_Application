/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;

import interfaces.*;

public class RmiClient {

	String userStr = "//tesla.cs.iupui.edu:2010/userController";
	String adminStr = "//tesla.cs.iupui.edu:2010/adminController";
	String custStr = "//tesla.cs.iupui.edu:2010/customerController";
	int userType;
	
	
	IUserController userController;
	IAdminController adminController;
	ICustomerController custController;
	
	public RmiClient() {
		try {
			userController = (IUserController) Naming.lookup(userStr);
			adminController = (IAdminController) Naming.lookup(adminStr);
			custController = (ICustomerController) Naming.lookup(custStr);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public int sendRequest(String username, String password) {
		try {
			userType = userController.userCheck(username, password);
					
		} 
		catch (Exception e) {
			System.out.println("Something went wrong in login connection...");
			e.printStackTrace();
		}
		return userType;
	}
	
	public int sendAdminRequest(String command) {
		int result = 0;
		try {
			switch(command) {
			case "browse":
				result = adminController.adminBrowseProd();
				break;
			case "add":
					result = adminController.adminAddProd();
				break;
			case "update":
				result = adminController.adminUpdateProd();
				break;
			case "delete":
				result = adminController.adminDeleteProd();
				break;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} //sendAdminRequest
	
	public int sendCustomerRequest(String command) {
		
		int result = 0;
		try {
			switch(command) {
			case "browse":
				result = custController.custBrowseProd();
				break;
			case "shoppingCart":
					result = custController.custShoppingCart();
				break;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
} //class RmiClient
