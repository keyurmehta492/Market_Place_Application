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

public class CommandAddAdmin implements ICommand {

	private RmiClient rmi;
	private Session session = null;
	private List<itemList> items;

	CommandAddAdmin(Session session) {
		rmi = new RmiClient();
		this.session = session;
	}

	@Override
	public List<itemList> execute(String info) {
		// Execute addadmin command for Admin user to add new Administrator
		items = rmi.sendAdminRequest("addadmin", session, info);

		return items;
	}// execute

}// class commandAddAdmin
