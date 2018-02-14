/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package clientController;
import view.*;

public class Dispatcher {

	Administrator admin;
	Customer cust;
	
	Dispatcher(){
		
	}
	
	public void showView(int result, String username) {
		//Admin credentials
		
				if(result == 1){
					System.out.println("Administrator Login Successful");
					admin = new Administrator(username);
					
					admin.adminView();
				}
		//Customer credentials
				else if(result == 2){
					System.out.println("Customer Login Successful");
					
					cust = new Customer(username);
					cust.customerView();
				}
	}
	
	
} //class dispatcher

