/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package serverController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IUserController;
import model.UserModel;
import server.Session;

// Ryan: We should use a Controller to interact with the "framework."
//FIXED: Controller is being called from framework (RMI)  

public class UserController extends UnicastRemoteObject  implements IUserController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5574948114609459375L;
	UserModel lg;
	Session session = null;
	
	public UserController() throws RemoteException {
		super();
		lg = new UserModel();
	}

	String username, password;
	int result;

	
	//method to check user credentials and assign the user role
	@Override
	public synchronized Session userCheck(String username, String password) throws RemoteException {
				
		session = lg.checkAuthentication(username, password);
		
		return session;
	} //checkuser

	//to register new user
	@Override
	public synchronized int userRegister() throws RemoteException {
		
		result = lg.register();
		
		return 0;
	} //registerUser
	
} //class MarketPlaceController