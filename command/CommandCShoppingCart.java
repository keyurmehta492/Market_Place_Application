/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import java.util.ArrayList;
import java.util.List;

import interfaces.ICommand;
import rmi.RmiClient;
import server.Session;
import server.itemList;

public class CommandCShoppingCart implements ICommand{

	RmiClient rmi;
	Session session = null;
	List<itemList> items = new ArrayList<itemList>() ;
	
	CommandCShoppingCart(Session session) {
		 rmi = new RmiClient();
		 this.session = session;
	}
	@Override
	public List<itemList> execute(String info) {
		//Execute shopping cart command for Customer user to add a product to shopping cart
		items = rmi.sendCustomerRequest("shoppingCart", session,info);
		return items;
	}//execute

} // class CommandCShoppingCart
