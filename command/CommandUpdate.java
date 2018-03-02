/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import interfaces.ICommand;
import rmi.RmiClient;
import server.Session;

public class CommandUpdate implements ICommand {

	RmiClient rmi;
	Session session = null;
	
	CommandUpdate(Session session) {
		 rmi = new RmiClient();
		 this.session = session;
	}
	@Override
	public Session execute() {
		//Execute update command for Admin user to update the product information
			session = rmi.sendAdminRequest("update",session);
			return session;
	}// execute

} // class CommandUpdate
