/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;
import interfaces.*;
import rmi.*;

public class CommandCShoppingCart implements ICommand{

	RmiClient rmi;
	
	CommandCShoppingCart() {
		 rmi = new RmiClient();
	}
	@Override
	public void execute() {
		//Execute customer shopping cart command
		rmi.sendCustomerRequest("shoppingCart");
	}
}
