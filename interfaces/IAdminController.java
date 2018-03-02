/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

import server.Session;

public interface IAdminController extends java.rmi.Remote{

	//User defined annotation is placed to check user has ADMIN role or not to access these operations.
	
	//for Admin to browse the product
	@RequiresRole("ADMIN")
	Session adminBrowseProd(Session session) throws java.rmi.RemoteException;
	
	//for Admin to add the product
	@RequiresRole("ADMIN")
	Session adminAddProd(Session session) throws java.rmi.RemoteException;
	
	//for Admin to update the product
	@RequiresRole("ADMIN")
	Session adminUpdateProd(Session session) throws java.rmi.RemoteException;

	//for Admin to delete the product
	@RequiresRole("ADMIN")
	Session adminDeleteProd(Session session) throws java.rmi.RemoteException;

} //interface IAdminController
