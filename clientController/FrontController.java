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

	// Ryan: Should these have scope associated with them?
	// FIXED: Added the scope for member variables and instance variables.
	private int result;

	private Session session = null;
	private Dispatcher display;
	private RmiClient rmi;

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
		if (username.isEmpty() || password.isEmpty()) {
			return 0;
		} else {
			session = rmi.sendRequest(username, password);

			// show customized view only if authenticated
			if (session.getIsAuth() == 1)
				display.showView(session);

			// Or else navigate to the user view
			else {
				return session.getIsAuth();
			}
		}

		return 1;

	} // signIn

	public int signUp(String name, String username, String password, String cf_password, String address) {
		// Check basic validation and send to server for authentication
		if (name.isEmpty() || username.isEmpty() || password.isEmpty() || address.isEmpty()) {
			return 0;
		}

		// check if password confirmed or not
		else if (!password.equals(cf_password)) {
			return -2;
		}

		// send request to server to get customer registered
		else {
			result = rmi.sendSignUpRequest(name, username, password, address);
		}

		return result;

	}// signUp

} // class FrontController
