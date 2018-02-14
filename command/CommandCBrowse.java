/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;
import interfaces.*;
import rmi.*;

public class CommandCBrowse implements ICommand{

RmiClient rmi;
	
	CommandCBrowse() {
		 rmi = new RmiClient();
	}
	@Override
	public void execute() {
		//Execute customer browse command
		rmi.sendCustomerRequest("browse");
	}

}
