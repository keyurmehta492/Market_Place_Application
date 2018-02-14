/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package clientController;

import rmi.*;

public class FrontController {

	String username, password;
		int userType = -1;
	
	Dispatcher display;
	RmiClient rmi;
	
	public FrontController() {
		try {
			rmi = new RmiClient();
			display = new Dispatcher();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int signIn(String username, String password) {
		
		// Check basic validation and send to server for authentication			
		if(username.isEmpty() || password.isEmpty()) {
			return 0;
		}
		else {
			userType = rmi.sendRequest(username,password);
			
			display.showView(userType,username);
		}
	
		return userType;
	} //signIn
	
	
} //class FrontController
