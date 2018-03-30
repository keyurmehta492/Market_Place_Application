/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package serverController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICustomerController;
import model.CustomerModel;
import server.Session;
import server.itemList;

public class CustomerController extends UnicastRemoteObject  implements ICustomerController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -235014651067973605L;
	CustomerModel cm;
	List<itemList> items = new ArrayList<itemList>() ;
	
	public CustomerController() throws RemoteException {
		super();
		cm = new CustomerModel();
	}

	@Override
	//customers browses the products
	public List<itemList> custBrowseProd(Session session) throws RemoteException {
	
			items = cm.custBrowse(session);
	
		return items;
	}

	@Override
	//customers shopping cart
	public List<itemList> custShoppingCart(Session session, String info) throws RemoteException {
		
		items = cm.custShopping(session, info);
		return items;
	}

	//Customer purchase product 
	@Override
	public List<itemList> custPurchaseProd(Session session, String info) throws RemoteException {
		items = cm.custPurchase(session,info);
		return items;
	}
	
} // class CustomerController
