/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

import server.Session;

public interface IUserController extends java.rmi.Remote {

	//to authenticate the credentials of the user
	Session userCheck(String username, String password) throws java.rmi.RemoteException;
	
	//to get register new user
	int userRegister() throws java.rmi.RemoteException;
	
} // interface IUserController
