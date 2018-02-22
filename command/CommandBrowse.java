/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import interfaces.ICommand;
import rmi.RmiClient;

public class CommandBrowse implements ICommand {

	RmiClient rmi;
	
	CommandBrowse() {
		 rmi = new RmiClient();
	}
	@Override
	public void execute() {
		//Execute browse command
			rmi.sendAdminRequest("browse");
		
	}

}
