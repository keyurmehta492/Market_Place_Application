/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

public interface IUserController extends java.rmi.Remote {

	//to check the type of the user
	int userCheck(String username, String password) throws java.rmi.RemoteException;
	
	//to register new user
	int userRegister() throws java.rmi.RemoteException;
	
}
