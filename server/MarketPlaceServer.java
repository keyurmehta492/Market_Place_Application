/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.*;

public class MarketPlaceServer extends UnicastRemoteObject {

	protected MarketPlaceServer() throws RemoteException {
		
	}

	public static void main(String[] args) {
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
			
		System.out.println("Market Place Server is starting...");
		//initialte RMI service
		RmiServer rmi = new RmiServer();
		rmi.serverBind();
			
	} //main

	
	
} //class MarketPlaceSever