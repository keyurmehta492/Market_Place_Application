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
import server.Session;

public class CustomerController extends UnicastRemoteObject  implements ICustomerController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -235014651067973605L;
	CustomerModel cm;
	public CustomerController() throws RemoteException {
		super();
		cm = new CustomerModel();
	}

	@Override
	//customers browses the products
	public Session custBrowseProd(Session session) throws RemoteException {
		
		session = cm.custBrowse(session);
		return session;
	}

	@Override
	//customers shopping cart
	public Session custShoppingCart(Session session) throws RemoteException {
		
		session = cm.custShopping(session);
		return session;
	}
	
} // class CustomerController
