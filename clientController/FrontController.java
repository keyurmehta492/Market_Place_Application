/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package clientController;

import rmi.RmiClient;
import server.Session;

// Ryan: Are you really using everything in this package?
//FIXED: Now only importing the required java classes from package and not all the classes from package.

public class FrontController {

	String username, password;
		
	Session session = null;
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
			session = rmi.sendRequest(username,password);
			
			//show customized view only if authenticated
			if(session.getIsAuth() == 1)
				display.showView(session);
			
			//Or else navigate to the user view 
			else {
					return session.getIsAuth();
			}
				
		}
		return 1;
		
	} //signIn
	
	
} //class FrontController
