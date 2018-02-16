/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;

import command.*;
import view.*;

// Ryan: Always include useful comments in every file.

// Ryan: This would not be a good place to use the Abstract Factory pattern -
// here you are really creating only one thing and "ignoring" the other.
public abstract class AbstractFact {

	public abstract void getBrowseA(String user, CommandInvoker adminCommand);
	public abstract void getBrowseC(String user, CommandInvoker custCommand);

}
