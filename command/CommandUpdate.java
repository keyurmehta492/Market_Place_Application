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

public class CommandUpdate implements ICommand {

	RmiClient rmi;
	Session session = null;
	List<itemList> items = new ArrayList<itemList>() ;
	
	CommandUpdate(Session session) {
		 rmi = new RmiClient();
		 this.session = session;
	}
	@Override
	public List<itemList> execute(String info) {
		//Execute update command for Admin user to update the product information
			items = rmi.sendAdminRequest("update",session,info);
			return items;
	}// execute

} // class CommandUpdate
