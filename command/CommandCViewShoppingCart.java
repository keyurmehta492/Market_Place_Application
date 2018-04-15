/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import java.util.List;

import interfaces.ICommand;
import rmi.RmiClient;
import server.Session;
import server.itemList;

public class CommandCViewShoppingCart implements ICommand {
	private RmiClient rmi;
	private Session session = null;
	private List<itemList> items;

	CommandCViewShoppingCart(Session session) {
		rmi = new RmiClient();
		this.session = session;
	}

	@Override
	public List<itemList> execute(String info) {
		// Execute shopping cart command for Customer user to add a product to
		// shopping cart
		items = rmi.sendCustomerRequest("viewShoppingCart", session, info);
		
		return items;
	}// execute
	
}// class CommandCViewShoppingCart
