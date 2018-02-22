/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package clientController;

import abstractFactory.AbstractFact;
import abstractFactory.ConcreteAdminFact;
import abstractFactory.ConcreteCustFact;

public class Dispatcher {

	AbstractFact fact;
	
	Dispatcher(){
		
	}
	
	public void showView(int result, String username) {
		//Admin credentials
		
				if(result == 1){
					System.out.println("Administrator Login Successful");
										
					AbstractFact fact = new ConcreteAdminFact();
					fact.createView(username);
				}
		//Customer credentials
				else if(result == 2){
					System.out.println("Customer Login Successful");
									
					AbstractFact fact = new ConcreteCustFact();
					fact.createView(username);
				}
	}
	
	
} //class dispatcher

