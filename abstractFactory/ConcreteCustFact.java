/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;

import view.Customer;

// Ryan: Always include useful comments in every file.
//FIXED: Included the useful comments in all files.

public class ConcreteCustFact extends AbstractFact {

	Customer cust;

	//to create an object of Customer view
	@Override
	public void createView(String username) {
		cust = new Customer(username);
		cust.displayView();
		
	}

} //ConcreteCustFact
