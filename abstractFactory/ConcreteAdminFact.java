/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;

import view.Administrator;


// Ryan: Always include useful comments in every file.
//FIXED: Included the useful comments in all files.

public class ConcreteAdminFact extends AbstractFact{

	Administrator admin;

	//to create an object of administrator view
	@Override
	public void createView(String username) {
		
		admin = new Administrator(username);
		admin.displayView();
		
	}

	
}//ConcreteAdminFact
