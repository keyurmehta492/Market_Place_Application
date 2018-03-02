/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package clientController;

import abstractFactory.AbstractFact;
import abstractFactory.ConcreteAdminFact;
import abstractFactory.ConcreteCustFact;
import server.Session;

public class Dispatcher {

	AbstractFact fact;
	
	Dispatcher(){
		
	}
	
	public void showView(Session session) {
		//Admin credentials
		
				if(session.getUserRole().equals("ADMIN")){
					System.out.println("Administrator Login Successful");
					
					//Create concreate class object to display the admin view
					AbstractFact fact = new ConcreteAdminFact();
					fact.createView(session);
				}
				
		//Customer credentials
				else if(session.getUserRole().equals("CUSTOMER")){
					System.out.println("Customer Login Successful");
					
					//Create concreate class object to display the customer view
					AbstractFact fact = new ConcreteCustFact();
					fact.createView(session);
				}
			
	}//showview
	
	
} //class dispatcher

