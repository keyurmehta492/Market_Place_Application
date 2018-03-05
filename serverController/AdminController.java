/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package serverController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IAdminController;
import model.AdminModel;
import server.Session;

public class AdminController extends UnicastRemoteObject  implements IAdminController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3876315941071232912L;
	AdminModel am;
	
	public AdminController() throws RemoteException {
		super();
		am = new AdminModel();
	}

	//for Admin to browse the product
	@Override
	public Session adminBrowseProd(Session session) throws RemoteException {
		session = am.adminBrowse(session);
		return session;
	}

	//for Admin to add the product
	@Override
	public Session adminAddProd(Session session) throws RemoteException {
	
		session = am.adminAdd(session);
		return session;
	}

	//for Admin to update the product
	@Override
	public Session adminUpdateProd(Session session) throws RemoteException {
		session = am.adminUpdate(session);
		return session;
	}

	//for Admin to delete the product
	@Override
	public Session adminDeleteProd(Session session) throws RemoteException {
		session = am.adminDelete(session);
		return session;
	}

}// class AdminController
