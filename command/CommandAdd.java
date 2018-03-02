/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import interfaces.ICommand;
import rmi.RmiClient;
import server.Session;

public class CommandAdd implements ICommand {

	RmiClient rmi;
	Session session = null;
	
	CommandAdd(Session session) {
		 rmi = new RmiClient();
		 this.session = session;
	}
	@Override
	public Session execute() {
			//Execute add command for Admin user to add the product
			session = rmi.sendAdminRequest("add", session);
			return session;
	}// execute

} // class CommandAdd
