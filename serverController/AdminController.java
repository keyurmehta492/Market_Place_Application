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

import interfaces.IAdminController;
import model.AdminModel;
import server.Session;
import server.itemList;

public class AdminController extends UnicastRemoteObject  implements IAdminController{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3876315941071232912L;
	AdminModel am;
	List<itemList> items = new ArrayList<itemList>() ;
	
	public AdminController() throws RemoteException {
		super();
		am = new AdminModel();
	}

	//for Admin to browse the product
	@Override
	public List<itemList> adminBrowseProd(Session session) throws RemoteException {
		items = am.adminBrowse(session);
		return items;
	}

	//for Admin to add the product
	@Override
	public List<itemList> adminAddProd(Session session,String info) throws RemoteException {
	
		items = am.adminAdd(session,info);
		return items;
	}

	//for Admin to update the product
	@Override
	public List<itemList> adminUpdateProd(Session session, String info) throws RemoteException {
		items = am.adminUpdate(session);
		return items;
	}

	//for Admin to delete the product
	@Override
	public List<itemList> adminDeleteProd(Session session, String info) throws RemoteException {
		items = am.adminDelete(session);
		return items;
	}

}// class AdminController
