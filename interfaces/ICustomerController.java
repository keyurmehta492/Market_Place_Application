/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

public interface ICustomerController extends java.rmi.Remote{
	
	//customers browses the products
	int custBrowseProd() throws java.rmi.RemoteException;
	
	//customers shopping cart
	int custShoppingCart() throws java.rmi.RemoteException;
	
}
