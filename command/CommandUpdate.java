/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;
import interfaces.*;
import rmi.*;

public class CommandUpdate implements ICommand {

	RmiClient rmi;
	
	CommandUpdate() {
		 rmi = new RmiClient();
	}
	@Override
	public void execute() {
		//Execute update command
			rmi.sendAdminRequest("update");
		
	}

}
