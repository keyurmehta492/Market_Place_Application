/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;

import command.*;
import view.*;

public abstract class AbstractFact {

	public abstract void getBrowseA(String user, CommandInvoker adminCommand);
	public abstract void getBrowseC(String user, CommandInvoker custCommand);

}
