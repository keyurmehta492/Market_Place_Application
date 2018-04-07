/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;

import server.Session;
import view.Administrator;


// Ryan: Always include useful comments in every file.
//FIXED: Included the useful comments in all files.

public class ConcreteAdminFact extends AbstractFact{
	// Ryan: Should this have a scope associated with it?
	Administrator admin;

	//Create an object of administrator view and display the admin view
	@Override
	public void createView(Session session) {
		
		admin = new Administrator(session);
		admin.displayView();
		
	}//createView

	
}// class ConcreteAdminFact
