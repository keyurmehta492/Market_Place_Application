/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package serverController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.ICustomerController;
import model.CustomerModel;

public class CustomerController extends UnicastRemoteObject  implements ICustomerController{

	CustomerModel cm;
	public CustomerController() throws RemoteException {
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
