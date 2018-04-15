/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;

import server.Session;
import view.Customer;

// Ryan: Always include useful comments in every file.
//FIXED: Included the useful comments in all files.

public class ConcreteCustFact extends AbstractFact {

	// Ryan: Should this have a scope associated with it?
	// FIXED: Added the scope for member variables and instance variables.
	private Customer cust;

	// Create an object of customer view and display the customer view
	@Override
	public void createView(Session session) {
		cust = new Customer(session);
		cust.displayView();

	}// createView

} // class ConcreteCustFact
