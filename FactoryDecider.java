/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;

public class FactoryDecider {

	public static AbstractFact getFact(String user) {
		
		if(user == "Admin") {
			return new ConcreteAdminFact();	
		}
		else if(user == "Customer") {
			return new ConcreteCustFact();
		}
		return null;
	}
}
