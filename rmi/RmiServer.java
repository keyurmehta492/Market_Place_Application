/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package rmi;

import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.RemoteException;

import server.AuthorizationInvocationHandler;
import interfaces.IAdminController;
import interfaces.ICustomerController;
import interfaces.IUserController;
import serverController.AdminController;
import serverController.CustomerController;
import serverController.UserController;;

public class RmiServer {

	String user_name = "//tesla.cs.iupui.edu:2010/userController";
	String cust_name = "//tesla.cs.iupui.edu:2010/customerController";
	String admin_name = "//tesla.cs.iupui.edu:2010/adminController";

	IUserController UserController;
	ICustomerController CustController;
	IAdminController adminController;

	Class<IUserController> user = IUserController.class;
	Class<ICustomerController> customer = ICustomerController.class;
	Class<IAdminController> admin = IAdminController.class;
	
	public RmiServer() {
		try {
			//Proxy pattern to check the user related operation
			UserController = (IUserController) Proxy.newProxyInstance(user.getClassLoader(),
	                new Class<?>[] {user},
	                new AuthorizationInvocationHandler(new UserController()));
			
			//Proxy pattern to check the role of the user is related to customer operations or not
			CustController = (ICustomerController) Proxy.newProxyInstance(customer.getClassLoader(),
	                new Class<?>[] {customer},
	                new AuthorizationInvocationHandler(new CustomerController()));
			
			//Proxy pattern to check the role of the user is related to admin operations or not
			adminController = (IAdminController) Proxy.newProxyInstance(admin.getClassLoader(),
	                new Class<?>[] {admin},
	                new AuthorizationInvocationHandler(new AdminController()));
		
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		
	}//RmiServer
	
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
