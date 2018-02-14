/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;
import interfaces.*;
import rmi.*;

public class CommandDelete implements ICommand {

	RmiClient rmi;
	
	CommandDelete() {
		 rmi = new RmiClient();
	}
	@Override
	public void execute() {
		//Execute delete command
			rmi.sendAdminRequest("delete");
		
	}

}
