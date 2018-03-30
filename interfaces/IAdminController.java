/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

import java.util.List;

import server.Session;
import server.itemList;

public interface IAdminController extends java.rmi.Remote{

	//User defined annotation is placed to check user has ADMIN role or not to access these operations.
	
	//for Admin to browse the product
	@RequiresRole("ADMIN")
	List<itemList> adminBrowseProd(Session session) throws java.rmi.RemoteException;
	
	//for Admin to add the product
	@RequiresRole("ADMIN")
	List<itemList> adminAddProd(Session session,String info) throws java.rmi.RemoteException;
	
	//for Admin to update the product
	@RequiresRole("ADMIN")
	List<itemList> adminUpdateProd(Session session,String info) throws java.rmi.RemoteException;

	//for Admin to delete the product
	@RequiresRole("ADMIN")
	List<itemList> adminDeleteProd(Session session,String info) throws java.rmi.RemoteException;

} //interface IAdminController
