/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

import java.util.List;

import server.Session;
import server.itemList;

public interface ICustomerController extends java.rmi.Remote{
	
	//User defined annotation is placed to check user has CUSTOMER role or not to access these operations.
	
	//customers browses the products
	@RequiresRole("CUSTOMER")
	List<itemList> custBrowseProd(Session session) throws java.rmi.RemoteException;
	
	//customers shopping cart
	@RequiresRole("CUSTOMER")
	List<itemList> custShoppingCart(Session session, String info) throws java.rmi.RemoteException;
	
	//customers purchase product
	@RequiresRole("CUSTOMER")
	List<itemList> custPurchaseProd(Session session, String info) throws java.rmi.RemoteException;
	
}//interface ICustomerController
