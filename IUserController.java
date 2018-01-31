/* Assignment 1
 *  
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

public interface IUserController extends java.rmi.Remote {

	int userCheck(String username, String password) throws java.rmi.RemoteException;
	int userRegister() throws java.rmi.RemoteException;
	
}
