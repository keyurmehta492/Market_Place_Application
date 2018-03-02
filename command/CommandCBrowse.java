/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import interfaces.ICommand;
import rmi.RmiClient;
import server.Session;

public class CommandCBrowse implements ICommand{

	RmiClient rmi;
	Session session = null;
	
	CommandCBrowse(Session session) {
		 rmi = new RmiClient();
		 this.session = session;
	}
	@Override
	public Session execute() {
		//Execute browse command for Customer user to browse the product
		session = rmi.sendCustomerRequest("browse", session);
		return session;
	} // execute

}// class CommandCBrowse
