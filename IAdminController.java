/* Assignment 1
 *  
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

public interface IAdminController extends java.rmi.Remote{

	int adminBrowseProd() throws java.rmi.RemoteException;
	int adminAddProd() throws java.rmi.RemoteException;
	int adminUpdateProd() throws java.rmi.RemoteException;
	int adminDeleteProd() throws java.rmi.RemoteException;
}
