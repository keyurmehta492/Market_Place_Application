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

public class AdminController extends UnicastRemoteObject  implements IAdminController{

	AdminModel am;
	
	public AdminController() throws RemoteException {
		super();
		am = new AdminModel();
	}

	//for Admin to browse the product
	@Override
	public int adminBrowseProd() throws RemoteException {
		am.adminBrowse();
		return 0;
	}

	//for Admin to add the product
	@Override
	public int adminAddProd() throws RemoteException {
		am.adminAdd();
		return 0;
	}

	//for Admin to update the product
	@Override
	public int adminUpdateProd() throws RemoteException {
		am.adminUpdate();
		return 0;
	}

	//for Admin to delete the product
	@Override
	public int adminDeleteProd() throws RemoteException {
		am.adminDelete();
		return 0;
	}

}// class AdminController
