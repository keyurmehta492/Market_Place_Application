/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package abstractFactory;

import server.Session;

// Ryan: Always include useful comments in every file.
//FIXED: Included the useful comments in all files.

// Ryan: This would not be a good place to use the Abstract Factory pattern -
// here you are really creating only one thing and "ignoring" the other.
//FIXED: Implemented abstract factory by declaring only 1 method in abstract class and implementing it 
//		 on 2 different concrete classes.

public abstract class AbstractFact {

	//Abstract method which will create respective view object.
	public abstract void createView(Session session);
}// AbstractFact
