/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import java.util.ArrayList;
import java.util.List;

import server.Session;
import server.itemList;

public class CommandInvoker {
	
	Session session = null;
	List<itemList> items = new ArrayList<itemList>() ;
	String[] purchase;
	
	public CommandInvoker(Session session) {
		this.session = session;
	}
	
	//Invoke commands related to the Admin user
	public List<itemList> sendACommand(String command, String info) {
		
		switch(command) {
			//invoke the browse command for Admin user
			case "browse":
				CommandBrowse browse = new CommandBrowse(session);
				items = browse.execute(info);
				break;
			
			//invoke the add command for Admin user
			case "add":
				CommandAdd add = new CommandAdd(session);
				items=add.execute(info);
				break;
				
			//invoke the update command for Admin user
			case "update":
				CommandUpdate update = new CommandUpdate(session);
				items=update.execute(info);
				break;
				
			//invoke the delete command for Admin user
			case "delete":
				CommandDelete delete = new CommandDelete(session);
				items=delete.execute(info);
				break;
		}//switchcase
		
		return items;
	} //sendACommand
	
	//Invoke commands related to the Customer user
	public List<itemList> sendCCommand(String command, String info) {
		
		switch(command) {
			//invoke the browse command for Customer user
			case "browse":
				CommandCBrowse browse = new CommandCBrowse(session);
				items = browse.execute(info);
				break;
			
			//invoke the shopping cart command for Customer user
			case "shoppingCart":
											
				CommandCShoppingCart shopping = new CommandCShoppingCart(session);
				items = shopping.execute(info);
				
				break;
			
			//invoke the purchase command for customer user
			case "purchaseProduct":
				
				CommandCPurchaseProd  purchaseProd= new CommandCPurchaseProd(session);
				items = purchaseProd.execute(info);
				
				break;
		}//switchcase
		
		return items;
	} //sendCCommand

	
} //commandInvoker
