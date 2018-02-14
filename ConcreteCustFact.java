/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;
import command.*;
import view.*;

public class ConcreteCustFact extends AbstractFact {

	@Override
	public void getBrowseA(String user, CommandInvoker adminCommand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getBrowseC(String user, CommandInvoker custCommand) {
		custCommand.sendCCommand("browse");
		
	}

	

}
