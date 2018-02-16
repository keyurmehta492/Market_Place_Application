/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;
import command.*;
import view.*;

// Ryan: Always include useful comments in every file.

public class ConcreteCustFact extends AbstractFact {


	@Override
	public void getBrowseA(String user, CommandInvoker adminCommand) {
		// TODO Auto-generated method stub
		
		// Ryan: What is the point of this method? It seems to not
		// add any functionality - so why not remove it?
		
	}

	@Override
	public void getBrowseC(String user, CommandInvoker custCommand) {
		custCommand.sendCCommand("browse");
		
	}

	

}
