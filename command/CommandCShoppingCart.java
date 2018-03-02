/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import interfaces.ICommand;
import rmi.RmiClient;
import server.Session;

public class CommandCShoppingCart implements ICommand{

	RmiClient rmi;
	Session session = null;
	
	CommandCShoppingCart(Session session) {
		 rmi = new RmiClient();
		 this.session = session;
	}
	@Override
	public Session execute() {
		//Execute shoppingcart command for Customer user to purchase the product
		session = rmi.sendCustomerRequest("shoppingCart", session);
		return session;
	}//execute

} // class CommandCShoppingCart
