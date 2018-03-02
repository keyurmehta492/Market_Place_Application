/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

import server.Session;

public interface ICustomerController extends java.rmi.Remote{
	
	//User defined annotation is placed to check user has CUSTOMER role or not to access these operations.
	
	//customers browses the products
	@RequiresRole("CUSTOMER")
	Session custBrowseProd(Session session) throws java.rmi.RemoteException;
	
	//customers shopping cart
	@RequiresRole("CUSTOMER")
	Session custShoppingCart(Session session) throws java.rmi.RemoteException;
	
}//interface ICustomerController
