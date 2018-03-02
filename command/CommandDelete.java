/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import interfaces.ICommand;
import rmi.RmiClient;
import server.Session;

public class CommandDelete implements ICommand {

	RmiClient rmi;
	Session session = null;
	
	CommandDelete(Session session) {
		 rmi = new RmiClient();
		 this.session = session;
	}
	@Override
	public Session execute() {
		//Execute delete command for Admin user to delete the product
			session = rmi.sendAdminRequest("delete",session);
			return session;
	}//execute

}//class CommandDelete
