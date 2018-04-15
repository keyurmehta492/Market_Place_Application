/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

import java.rmi.RemoteException;

import server.Session;

public interface IUserController extends java.rmi.Remote {

	// to authenticate the credentials of the user
	Session userCheck(String username, String password) throws java.rmi.RemoteException;

	// to get register new user
	int userRegister(String name, String username, String password, String address) throws RemoteException;

} // interface IUserController
