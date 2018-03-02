/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import server.Session;

public class CommandInvoker {
	
	Session session = null;
	public CommandInvoker(Session session) {
		this.session = session;
	}
	
	//Invoke commands related to the Admin user
	public Session sendACommand(String command) {
		
		switch(command) {
			//invoke the browse command for Admin user
			case "browse":
				CommandBrowse browse = new CommandBrowse(session);
				session = browse.execute();
				break;
			
			//invoke the add command for Admin user
			case "add":
				CommandAdd add = new CommandAdd(session);
				session=add.execute();
				break;
				
			//invoke the update command for Admin user
			case "update":
				CommandUpdate update = new CommandUpdate(session);
				session=update.execute();
				break;
				
			//invoke the delete command for Admin user
			case "delete":
				CommandDelete delete = new CommandDelete(session);
				session=delete.execute();
				break;
		}//switchcase
		
		return session;
	} //sendACommand
	
	//Invoke commands related to the Customer user
	public Session sendCCommand(String command) {
		
		switch(command) {
			//invoke the browse command for Customer user
			case "browse":
				CommandCBrowse browse = new CommandCBrowse(session);
				session = browse.execute();
				break;
			
			//invoke the shopping cart command for Customer user
			case "shoppingCart":
				CommandCShoppingCart shopping = new CommandCShoppingCart(session);
				session = shopping.execute();
				break;
		}//switchcase
		
		return session;
	} //sendCCommand

	
} //commandInvoker
