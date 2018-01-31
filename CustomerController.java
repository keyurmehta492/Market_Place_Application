/* Assignment 1
 *  
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerController extends UnicastRemoteObject  implements ICustomerController{

	CustomerModel cm;
	protected CustomerController() throws RemoteException {
		super();
		cm = new CustomerModel();
	}

	@Override
	//customers browses the products
	public int custBrowseProd() throws RemoteException {
		
		cm.custBrowse();
		return 0;
	}

	@Override
	//customers shopping cart
	public int custShoppingCart() throws RemoteException {
		
		cm.custShopping();
		return 0;
	}
	
} // class CustomerController
