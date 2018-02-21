/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import interfaces.ICommand;
import rmi.RmiClient;

public class CommandAdd implements ICommand {

	RmiClient rmi;
	
	CommandAdd() {
		 rmi = new RmiClient();
	}
	@Override
	public void execute() {
			//Execute add command
			rmi.sendAdminRequest("add");
		
	}

}
