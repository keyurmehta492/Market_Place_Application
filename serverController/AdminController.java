/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package serverController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import interfaces.IAdminController;
import model.AdminModel;
import server.Session;
import server.itemList;

public class AdminController extends UnicastRemoteObject implements IAdminController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3876315941071232912L;
	private AdminModel am;
	private List<itemList> items;

	public AdminController() throws RemoteException {
		super();
		am = new AdminModel();
	}

	// for Admin to browse the product
	@Override
	public List<itemList> adminBrowseProd(Session session) throws RemoteException {
		items = am.adminBrowse(session);
		return items;
	}

	// for Admin to add the product
	@Override
	public List<itemList> adminAddProd(Session session, String info) throws RemoteException {

		items = am.adminAdd(session, info);
		return items;
	}

	// for Admin to update the product
	@Override
	public List<itemList> adminUpdateProd(Session session, String info) throws RemoteException {
		items = am.adminUpdate(session, info);
		return items;
	}

	// for Admin to delete the product
	@Override
	public List<itemList> adminDeleteProd(Session session, String info) throws RemoteException {
		items = am.adminDelete(session, info);
		return items;
	}

	// for Admin to add new adminstrator
	@Override
	public List<itemList> adminAddAdmin(Session session, String info) throws RemoteException {

		items = am.adminAddAdmin(session, info);
		return items;
	}

	@Override
	public List<itemList> adminAddCustomer(Session session, String info) throws RemoteException {
		items = am.adminAddCustomer(session, info);
		return items;
	}

	@Override
	public List<itemList> adminRemoveCustomer(Session session, String info) throws RemoteException {
		items = am.adminRemoveCustomer(session, info);
		return items;
	}

}// class AdminController
