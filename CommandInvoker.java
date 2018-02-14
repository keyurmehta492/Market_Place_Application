/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

public class CommandInvoker {
	
	
	public void sendACommand(String command) {
		
		switch(command) {
			case "browse":
				CommandBrowse browse = new CommandBrowse();
				browse.execute();
				break;
			case "add":
				CommandAdd add = new CommandAdd();
				add.execute();
				break;
			case "update":
				CommandUpdate update = new CommandUpdate();
				update.execute();
				break;
			case "delete":
				CommandDelete delete = new CommandDelete();
				delete.execute();
				break;
		}
	} //sendACommand
	
	public void sendCCommand(String command) {
		
		switch(command) {
			case "browse":
				CommandCBrowse browse = new CommandCBrowse();
				browse.execute();
				break;
			case "shoppingCart":
				CommandCShoppingCart shopping = new CommandCShoppingCart();
				shopping.execute();
				break;
		}
	} //sendCCommand

	
} //commandInvoker
