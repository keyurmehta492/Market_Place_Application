/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package command;

import java.util.List;

import server.Session;
import server.itemList;

public class CommandInvoker {

	private Session session = null;
	private List<itemList> items;
	private String[] details = new String[6];

	public CommandInvoker(Session session) {
		this.session = session;
	}

	// Invoke commands related to the Admin user
	public List<itemList> sendACommand(String command, String info) {

		switch (command) {
		// invoke the browse products command for Admin user
		case "browse":
			CommandBrowse browse = new CommandBrowse(session);
			items = browse.execute(info);
			break;

		// invoke the add product command for Admin user
		case "add":
			details = info.split(",");
			if (details[0].isEmpty() || details[1].isEmpty() || details[2].isEmpty() || details[3].isEmpty() ||
					details[4].isEmpty() || details[5].isEmpty())
				items.add(0, new itemList("-2"));
			else {
				CommandAdd add = new CommandAdd(session);
				items = add.execute(info);
			}
			break;

		// invoke the update product command for Admin user
		case "update":
			CommandUpdate update = new CommandUpdate(session);
			items = update.execute(info);
			break;

		// invoke the delete product command for Admin user
		case "delete":
			CommandDelete delete = new CommandDelete(session);
			items = delete.execute(info);
			break;

		// invoke the addadmin command for Admin user
		case "addadmin":
			details = info.split(",");
			if (details[0].isEmpty() || details[1].isEmpty() || details[2].isEmpty() || details[3].isEmpty())
				items.add(0, new itemList("-2"));
			else {
				CommandAddAdmin addadmin = new CommandAddAdmin(session);
				items = addadmin.execute(info);
			}
			break;

		// invoke the add customer command for Admin user
		case "addcustomer":
			details = info.split(",");
			if (details[0].isEmpty() || details[1].isEmpty() || details[2].isEmpty() || details[3].isEmpty())
				items.add(0, new itemList("-2"));
			else {
				CommandAddCustomer addcustomer = new CommandAddCustomer(session);
				items = addcustomer.execute(info);
			}
			break;

		// invoke the remove customer command for Admin user
		case "removecustomer":
			CommandRemoveCustomer removecustomer = new CommandRemoveCustomer(session);
			items = removecustomer.execute(info);
			break;
		}// switchcase

		return items;
	} // sendACommand

	// Invoke commands related to the Customer user
	public List<itemList> sendCCommand(String command, String info) {

		switch (command) {
		// invoke the browse prodcuts command for Customer user
		case "browse":
			CommandCBrowse browse = new CommandCBrowse(session);
			items = browse.execute(info);
			break;

		// invoke the shopping cart command for Customer user
		case "shoppingCart":
			CommandCShoppingCart shopping = new CommandCShoppingCart(session);
			items = shopping.execute(info);
			break;

		// invoke the shopping cart command for Customer user
		case "viewShoppingCart":
			CommandCViewShoppingCart viewShopping = new CommandCViewShoppingCart(session);
			items = viewShopping.execute(info);
			break;

		// invoke the purchase command for customer user
		case "purchaseProduct":
			CommandCPurchaseProd purchaseProd = new CommandCPurchaseProd(session);
			items = purchaseProd.execute(info);
			break;
			
		}// switchcase

		return items;
	} // sendCCommand

} // class commandInvoker
