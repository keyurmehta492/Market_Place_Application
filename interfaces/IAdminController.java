/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

public interface IAdminController extends java.rmi.Remote{

	//for Admin to browse the product
	int adminBrowseProd() throws java.rmi.RemoteException;
	
	//for Admin to add the product
	int adminAddProd() throws java.rmi.RemoteException;
	
	//for Admin to update the product
	int adminUpdateProd() throws java.rmi.RemoteException;

	//for Admin to delete the product
	int adminDeleteProd() throws java.rmi.RemoteException;
}
