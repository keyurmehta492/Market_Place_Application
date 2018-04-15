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

public class CommandAddCustomer implements ICommand {

	private RmiClient rmi;
	private Session session = null;
	private List<itemList> items;

	CommandAddCustomer(Session session) {
		rmi = new RmiClient();
		this.session = session;
	}

	@Override
	public List<itemList> execute(String info) {
		// Execute addcustomer command for Admin user to add new Customer
		items = rmi.sendAdminRequest("addcustomer", session, info);

		return items;
	}// execute

}// class CommandAddCustomer
