/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

public interface ICustomerController extends java.rmi.Remote{
	
	int custBrowseProd() throws java.rmi.RemoteException;
	int custShoppingCart() throws java.rmi.RemoteException;
	
}
