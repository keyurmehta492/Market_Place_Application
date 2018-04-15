/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package client;

import clientController.FrontController;
import view.User;

public class Client {

	// Entry point in Market place application
	public static void main(String[] args) {

		System.out.println("Market Place application is starting...");

		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());

		FrontController fc = new FrontController();

		// calling user view
		User user = new User(fc);
		user.userView();

	} // main

} // class Client
