/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;
import command.*;
import view.*;

public class ConcreteAdminFact extends AbstractFact{

	@Override
	public void getBrowseA(String user, CommandInvoker adminCommand) {
		adminCommand.sendACommand("browse");
		
	}

	@Override
	public void getBrowseC(String user, CommandInvoker custCommand) {
		// TODO Auto-generated method stub
		
	}

	
}
