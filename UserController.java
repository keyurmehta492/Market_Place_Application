/* Assignment 1
 *  
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserController extends UnicastRemoteObject  implements IUserController{

	UserModel lg;
	
	public UserController() throws RemoteException {
		super();
		lg = new UserModel();
	}

	String username, password;
	int result;

	@Override
	//method to check user credentials
	public synchronized int userCheck(String username, String password) throws RemoteException {
				
		this.result = lg.check(username, password);
		
		return this.result;
	} //checkuser

	@Override
	//to register new user
	public synchronized int userRegister() throws RemoteException {
		
		result = lg.register();
		
		return 0;
	} //registerUser
	
} //class MarketPlaceController